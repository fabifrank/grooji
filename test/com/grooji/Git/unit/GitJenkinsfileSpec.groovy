import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import com.lesfurets.jenkins.unit.BasePipelineTest;
import com.lesfurets.jenkins.unit.global.lib.LibraryConfiguration;
import com.lesfurets.jenkins.unit.global.lib.LocalSource;
import static com.lesfurets.jenkins.unit.global.lib.LibraryConfiguration.library;
import static com.lesfurets.jenkins.unit.global.lib.LocalSource.localSource;


class GitJenkinsfileSpec extends BasePipelineTest {

    @Override
    @Before
    public void setUp() throws Exception {
	super.setUp();
	helper.registerAllowedMethod("sshagent", [Map.class, Closure], { "ok" });

        def library = library().name('grooji')
        .defaultVersion("master")
        .allowOverride(true)
        .implicit(false)
        .targetPath('./')
        .retriever(localSource('./'))
        .build()
        helper.registerSharedLibrary(library)

    }

    @Test
    public void should_execute_without_errors() throws Exception {
	def script = loadScript('test/com/grooji/Git/unit/Jenkinsfile')
	script.execute()

        def fnNames = ['setGitUser', 'addGitTag', 'pushGitTags', 'sshagent', 'sh', 'getGitUrl'];

	def fnCalls = [];
	helper.callStack.findAll{ call ->
          fnNames.any{call.methodName==it}
	}.every{ call ->
	    fnCalls.push(call.toString().trim());
	}
        org.junit.Assert.assertEquals('Jenkinsfile.setGitUser(test_user, test@mail)', fnCalls[0]);
        org.junit.Assert.assertEquals('setGitUser.sh(git config --global user.email "test@mail")', fnCalls[1]);
        org.junit.Assert.assertEquals('setGitUser.sh(git config --global user.name "test_user")', fnCalls[2]);
	org.junit.Assert.assertEquals('Jenkinsfile.addGitTag(1.0.0, dev, credentialsId)', fnCalls[3]);
        org.junit.Assert.assertEquals('addGitTag.sh(git tag -a 1.0.0 -m "Jenkins Release to dev")', fnCalls[4]);
	org.junit.Assert.assertEquals('addGitTag.pushGitTags(credentialsId)', fnCalls[5]);
	org.junit.Assert.assertEquals('pushGitTags.sshagent({credentials=[credentialsId]}, groovy.lang.Closure)', fnCalls[6]);

	assertJobStatusSuccess();
    }
}

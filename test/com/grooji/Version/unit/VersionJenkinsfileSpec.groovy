import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import com.lesfurets.jenkins.unit.BasePipelineTest;
import com.lesfurets.jenkins.unit.global.lib.LibraryConfiguration;
import com.lesfurets.jenkins.unit.global.lib.LocalSource;
import static com.lesfurets.jenkins.unit.global.lib.LibraryConfiguration.library;
import static com.lesfurets.jenkins.unit.global.lib.LocalSource.localSource;


class VersionJenkinsfileSpec extends BasePipelineTest {

    @Override
    @Before
    public void setUp() throws Exception {
	super.setUp();
        // the sh method is only called to return the commit hush so we stub it here
	helper.registerAllowedMethod("sh", [Map.class], { "COMMITHASH1234" });
        helper.registerAllowedMethod("assertVariable", [Object], { "ok" });
        binding.setVariable('env', [
          BUILD_NUMBER: 51,
          BRANCH_NAME: 'test_branch'
        ])

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
	def script = loadScript('test/com/grooji/Version/unit/Jenkinsfile')
	script.execute()

        def fnNames = ["assertVariable", "sh"];

	def fnCalls = [];
	helper.callStack.findAll{ call ->
  	    fnNames.any{
              call.methodName.trim() == it
            }
        }.every{ call ->
	    fnCalls.push(call.toString().trim());
	}
        org.junit.Assert.assertEquals('getShortCommitHash.sh({returnStdout=true, script=git rev-parse --short HEAD})', fnCalls[0]);
	org.junit.Assert.assertEquals('Jenkinsfile.assertVariable(51/COMMITHASH1234/test_branch)', fnCalls[1]);
        org.junit.Assert.assertEquals('getCommitHash.sh({returnStdout=true, script=git rev-parse HEAD})', fnCalls[2]);
	org.junit.Assert.assertEquals('Jenkinsfile.assertVariable(COMMITHASH1234)', fnCalls[3]);

	assertJobStatusSuccess();
    }
}

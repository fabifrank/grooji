import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import com.lesfurets.jenkins.unit.BasePipelineTest;
import com.lesfurets.jenkins.unit.global.lib.LibraryConfiguration;
import com.lesfurets.jenkins.unit.global.lib.LocalSource;
import static com.lesfurets.jenkins.unit.global.lib.LibraryConfiguration.library;
import static com.lesfurets.jenkins.unit.global.lib.LocalSource.localSource;


class PipelineJenkinsSpec extends BasePipelineTest {

    @Override
    @Before
    public void setUp() throws Exception {
	super.setUp();
	helper.registerAllowedMethod("setRecentStep", [Map.class], { "ok" });

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
	def script = loadScript('test/com/grooji/Pipeline/unit/Jenkinsfile')
	script.execute()

	def fnCalls = [];
	helper.callStack.findAll{ call ->
	    call.methodName == "setRecentStep"
	}.every{ call ->
	    fnCalls.push(call.toString().trim());
	}
	org.junit.Assert.assertEquals('Jenkinsfile.setRecentStep(currently doing this)', fnCalls[0]);

	assertJobStatusSuccess();
    }
}

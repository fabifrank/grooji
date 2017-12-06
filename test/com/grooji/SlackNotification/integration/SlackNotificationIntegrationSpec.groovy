import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import com.lesfurets.jenkins.unit.BasePipelineTest;
import com.lesfurets.jenkins.unit.global.lib.LibraryConfiguration;
import com.lesfurets.jenkins.unit.global.lib.LocalSource;
import static com.lesfurets.jenkins.unit.global.lib.LibraryConfiguration.library;
import static com.lesfurets.jenkins.unit.global.lib.LocalSource.localSource;


class SlackNotificationIntegrationSpec extends BasePipelineTest {

    @Override
    @Before
    public void setUp() throws Exception {
	super.setUp();
	helper.registerAllowedMethod("slackSend", [Map.class], { "ok" });

        /*
        def source = LocalSource.localSource('./');
        def libDesc = new LibraryConfiguration();
        libDesc.name = 'grooji';
        libDesc.retriever = source;
        libDesc.defaultVersion = 'master';
        libDesc.allowOverride = true;
        libDesc.implicit = false;
        libDesc.build();
        def lib = helper.registerSharedLibrary(libDesc)
        */

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
	def script = loadScript('test/com/grooji/SlackNotification/integration/Jenkinsfile')
	script.execute()

	def fnCalls = [];
	helper.callStack.findAll{ call ->
	    call.methodName == "slackSend"
	}.every{ call ->
	    fnCalls.push(call.toString());
	}
	org.junit.Assert.assertEquals('pushSlackNotification.slackSend({color=color, message=[action] appName\nEnv: targetEnv\nBuild: build\nStage: recentStage\nlog})', fnCalls[0].trim());

	org.junit.Assert.assertEquals('pushSlackNotification.slackSend({color=color, message=[action] appName\nEnv: targetEnv\nBuild: build\nlog})', fnCalls[1].trim());

	org.junit.Assert.assertEquals('pushSlackNotification.slackSend({color=color, message=[action] appName\nEnv: targetEnv\nBuild: build})', fnCalls[2].trim());

	org.junit.Assert.assertEquals('pushSlackNotification.slackSend({color=color, message=[action] appName\nEnv: targetEnv})', fnCalls[3].trim());

	org.junit.Assert.assertEquals('pushSlackNotification.slackSend({color=color, message=[action] appName})', fnCalls[4].trim());

        org.junit.Assert.assertEquals('pushSlackNotification.slackSend({color=neutral, message=[Build Start] appName})', fnCalls[5].trim());

	assertJobStatusSuccess();
    }
}

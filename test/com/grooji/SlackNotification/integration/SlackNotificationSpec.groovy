import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import com.lesfurets.jenkins.unit.BasePipelineTest;

class SlackNotificationSpec extends BasePipelineTest {

    @Override
    @Before
    public void setUp() throws Exception {
	super.setUp();
	helper.registerAllowedMethod("test", [], { "ok" });
	helper.registerAllowedMethod("slackSend", [Map.class], { "ok" });
    }

    @Test
    public void should_execute_without_errors() throws Exception {
	def script = loadScript('test/com/grooji/SlackNotification/integration/Jenkinsfile')
	script.execute()
	//printCallStack()

	def fnCall;
	helper.callStack.findAll { call ->
	    call.methodName == "slackSend"
	}.any { call ->
	    fnCall = call.toString();
	}
	org.junit.Assert.assertEquals('Jenkinsfile.slackSend({color=color, message=[action] appName\nEnv: targetEnv\nBuild: build\nStage: recentStage\nlog})', fnCall.trim());
	assertJobStatusSuccess();
    }
}

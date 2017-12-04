import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import com.lesfurets.jenkins.unit.BasePipelineTest;

class SlackNotificationIntegrationSpec extends BasePipelineTest {

    @Override
    @Before
    public void setUp() throws Exception {
	super.setUp();
	helper.registerAllowedMethod("slackSend", [Map.class], { "ok" });
    }

    @Test
    public void should_execute_without_errors() throws Exception {
	def script = loadScript('test/com/grooji/SlackNotification/integration/Jenkinsfile')
	script.execute()
	//printCallStack()

	def fnCalls = [];
	helper.callStack.findAll{ call ->
	    call.methodName == "slackSend"
	}.every{ call ->
	    fnCalls.push(call.toString());
	}
	org.junit.Assert.assertEquals('Jenkinsfile.slackSend({color=color, message=[action] appName\nEnv: targetEnv\nBuild: build\nStage: recentStage\nlog})', fnCalls[0].trim());

	org.junit.Assert.assertEquals('Jenkinsfile.slackSend({color=color, message=[action] appName\nEnv: targetEnv\nBuild: build\nlog})', fnCalls[1].trim());

	org.junit.Assert.assertEquals('Jenkinsfile.slackSend({color=color, message=[action] appName\nEnv: targetEnv\nBuild: build})', fnCalls[2].trim());

	org.junit.Assert.assertEquals('Jenkinsfile.slackSend({color=color, message=[action] appName\nEnv: targetEnv})', fnCalls[3].trim());

	org.junit.Assert.assertEquals('Jenkinsfile.slackSend({color=color, message=[action] appName})', fnCalls[4].trim());

        org.junit.Assert.assertEquals('Jenkinsfile.slackSend({color=neutral, message=[Build Start] appName})', fnCalls[5].trim());

	assertJobStatusSuccess();
    }
}

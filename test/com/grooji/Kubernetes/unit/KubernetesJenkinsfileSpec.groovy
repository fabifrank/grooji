import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import com.lesfurets.jenkins.unit.BasePipelineTest;
import com.lesfurets.jenkins.unit.global.lib.LibraryConfiguration;
import com.lesfurets.jenkins.unit.global.lib.LocalSource;
import static com.lesfurets.jenkins.unit.global.lib.LibraryConfiguration.library;
import static com.lesfurets.jenkins.unit.global.lib.LocalSource.localSource;


class KubernetesJenkinsfileSpec extends BasePipelineTest {

    @Override
    @Before
    public void setUp() throws Exception {
	super.setUp();
	helper.registerAllowedMethod('sshagent', [Map.class, Closure], { 'ok' });
        helper.registerAllowedMethod('sh', [Map.class], { ' return with whitespace ' });

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
	def script = loadScript('test/com/grooji/Kubernetes/unit/Jenkinsfile')
	script.execute()

        def fnNames = ['getRunningPodStatus', 'getRecentPodStatus', 'getPodsByNamespace', 'sh', 'getPodNameByRegex'];

	def fnCalls = [];
	helper.callStack.findAll{ call ->
          fnNames.any{call.methodName==it}
	}.every{ call ->
	    fnCalls.push(call.toString().trim());
	}
        org.junit.Assert.assertEquals('Jenkinsfile.getPodsByNamespace(dev)', fnCalls[0]);
        org.junit.Assert.assertEquals('getPodsByNamespace.sh({returnStdout=true, script=kubectl get pods -n dev})', fnCalls[1]);
        org.junit.Assert.assertEquals('Jenkinsfile.getRecentPodStatus(pod-begins-with..., dev)', fnCalls[2]);
        org.junit.Assert.assertEquals('getRecentPodStatus.sh({returnStdout=true, script=kubectl describe po pod-begins-with... -n dev | grep -Eo \'Status:.*\' | cut -d: -f2 | tr -d \'[:space:]\'})', fnCalls[3]);
        org.junit.Assert.assertEquals('Jenkinsfile.getRunningPodStatus(pod-begins-with..., dev)', fnCalls[4]);
        org.junit.Assert.assertEquals('getRunningPodStatus.getPodsByNamespace(dev)', fnCalls[5]);
        org.junit.Assert.assertEquals('getPodsByNamespace.sh({returnStdout=true, script=kubectl get pods -n dev})', fnCalls[6]);
        org.junit.Assert.assertEquals('getRunningPodStatus.sh({returnStdout=true, script=echo "${pods}"  | grep ${podPrefix} | awk \'{ print ${3} }\'})', fnCalls[7]);
        org.junit.Assert.assertEquals('Jenkinsfile.getPodNameByRegex(pod-begins-with..., dev)', fnCalls[8]);
        org.junit.Assert.assertEquals('getPodNameByRegex.sh({returnStdout=true, script=kubectl get pods -n dev | awk \'/pod-begins-with.../ {print 1; exit}\'})', fnCalls[9]);

	assertJobStatusSuccess();
    }
}

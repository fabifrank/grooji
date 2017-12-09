import com.grooji.Kubernetes;
import spock.lang.*;
import groovy.mock.*;

class KubernetesClassSpec extends Specification {
  def 'Constants have correct values'() {
    expect:
      com.grooji.Kubernetes.STATUS_PENDING == 'Pending';
      com.grooji.Kubernetes.STATUS_RUNNING == 'Running';
      com.grooji.Kubernetes.STATUS_SUCCEEDED == 'Succeeded';
      com.grooji.Kubernetes.STATUS_FAILED == 'Failed';
      com.grooji.Kubernetes.STATUS_UNKNOWN == 'Unknown';
  }
}

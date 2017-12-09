/**
 * Get pods in a kubernetes namespace
 */
def call(String namespace) {
  def pods = sh(returnStdout: true, script: "kubectl get pods -n ${namespace}");
  return pods;
}

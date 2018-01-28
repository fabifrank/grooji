/**
 * Get pod name in a kubernetes namespace by regex
 */
def call(String regex, String namespace) {
  def pod = sh(returnStdout: true, script: "kubectl get pods -n ${namespace} | awk '/${regex}/ {print \$1; exit}'");
  return pod;
}

/**
 * Get the kubernetes status of a running pod
 */
def call(String podPrefix, String namespace) {
  def pods = getPodsByNamespace(namespace);
  def status = sh(returnStdout: true, script: 'echo "${pods}"  | grep ${podPrefix} | awk \'{ print ${3} }\'')
  return status
}

/**
 * Get the status of a recent pod
 */
def call(String podPrefix, String namespace) {
  def podStatus = sh(returnStdout: true, script: "kubectl describe po ${podPrefix} -n ${namespace} | grep -Eo 'Status:.*' | cut -d: -f2 | tr -d '[:space:]'")
  podStatus = podStatus.trim();
  return podStatus
}

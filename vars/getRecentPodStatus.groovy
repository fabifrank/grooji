/**
 * Get the status of a recent pod
 */
def call(String podPrefix, String namespace) {
  sh("kubectl describe po ${podPrefix} -n ${namespace}  | grep -Eo 'Status' | cut -d: -f2 | tr -d '[:space:]' > .pod-status")
  def podStatus = readFile('.pod-status').trim()
  return podStatus
}

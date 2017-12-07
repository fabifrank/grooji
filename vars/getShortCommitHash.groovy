/**
 * Get the short git hash of the current commit
 */
def call() {
  def commit = sh(returnStdout: true, script: 'git rev-parse --short HEAD');
  return commit;
}

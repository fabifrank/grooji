/**
 * Get the short git hash of the current commit
 */
def call() {
  def commit = sh(returnStdout: true, script: 'git rev-parse --short HEAD');
  commit = commit.replaceAll('\n', '');
  return commit;
}

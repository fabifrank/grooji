/**
 * Get the normal commit hash of the current commit
 */
def call() {
  def commit = sh(returnStdout: true, script: 'git rev-parse HEAD');
  commit = commit.replaceAll('\n', '');
  return commit;
}

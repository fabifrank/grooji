/**
 *  Build a version not based on standard software versioning but instead use git information and jenkins build number.
 */
def call() {
  return env.BUILD_NUMBER + '/' + getShortCommitHash() + '/' + env.BRANCH_NAME
}

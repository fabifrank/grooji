/**
 * Get the git url of the remote repository
 */
def call() {
  return scm.getUserRemoteConfigs()[0].getUrl()
}

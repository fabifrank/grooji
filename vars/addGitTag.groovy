/**
 * Add a git tag to the current commit
 */
def call(String version, String targetEnv, String credentialsId) {
  sh('git tag -a ' + version + ' -m "' + 'Jenkins Release to ' + targetEnv + '"')
  pushGitTags(credentialsId)
}

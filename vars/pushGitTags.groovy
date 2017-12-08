/**
 * Push the git tag to the remote server
 */
def call(String credentialsId) {
  sshagent(credentials: [credentialsId]) {
    sh 'git push ' + getGitUrl() + ' --tags'
  }
}

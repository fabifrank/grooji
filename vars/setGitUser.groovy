/**
 * Set the git user name and email address
 */
def call(String name, String email) {
  sh('git config --global user.email "' + email + '"')
  sh('git config --global user.name "' + name + '"')
}

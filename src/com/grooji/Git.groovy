package com.grooji;

def static getCredentialsId(appName) {
  return appName + '-jenkins-git'
}

def getUrl() {
  return scm.getUserRemoteConfigs()[0].getUrl()
}

def setUser() {
  sh 'git config --global user.email "jenkins@grooji.io"'
  sh 'git config --global user.name "Jenkins"'
}

def tag(version, targetEnv, credentialsId) {
  setUser()
  sh 'git tag -a ' + version + ' -m "' + 'Jenkins Release to ' + targetEnv + '"'
  pushTags(credentialsId)
}

def pushTags(credentialsId) {
  sshagent(credentials: [credentialsId]) { 
    sh 'git push ' + getUrl() + ' --tags'
  }
}

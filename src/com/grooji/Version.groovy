package com.grooji;

def getCommitHash() {
  sh 'git rev-parse HEAD > commit'
  def commit = readFile('commit').trim()
  return commit
}

def static shrinkCommitHash(hash) {
  return hash[-8..-1]
}

def getShortCommitHash() {
  sh 'git rev-parse --short HEAD > commit'
  def commit = readFile('commit').trim()
  return commit
}

def getBranchName() {
  return env.BRANCH_NAME
}

def buildDevVersion() {
  return env.BUILD_NUMBER + '/' + getShortCommitHash() + '/' + getBranchName()
}

def setJenkinsBuildName(name) {
  currentBuild.displayName = name
}

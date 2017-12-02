package com.grooji;

def buildMessage(action, appName, targetEnv, build, log, recentStage) {
  def msg = appName
  if (targetEnv.length() > 0) {
    msg += '\nEnv: ' + targetEnv
  }
  if (build.length() > 0) {
    msg += '\nBuild: ' + build
  }
  if (recentStage.length() > 0) {
    msg += '\nStage: ' + recentStage
  }
  if (log.length() > 0) {
    log = '\n' + log
  }

  return '[' + action + '] ' + msg + log
}

def notifyBuildStart(appName, targetEnv) {
  slackSend(color: 'neutral', message: buildMessage('Build Start', appName, targetEnv, '', '', ''))
}

def notifyBuildSuccess(appName, targetEnv, build) {
  slackSend(color: 'good', message: buildMessage('Build SUCCESS', appName, targetEnv, build, '', ''))
}

def notifyBuildError(appName, targetEnv, build, log, recentStage) {
  slackSend(color: 'danger', message: buildMessage('Build ERROR', appName, targetEnv, build, log, recentStage))
}

def notifyApplySuccess(appName, targetEnv, build, log) {
  slackSend(color: 'good', message: buildMessage('Apply SUCCESS', appName, targetEnv, build, log, ''))
}

def notifyDeployStatus(appName, targetEnv, build, status) {
  slackSend(color: 'neutral', message: buildMessage('Deploy Status', appName, targetEnv, build, status, ''))
}

def notifyDeploySuccess(appName, targetEnv, build, log) {
  slackSend(color: 'good', message: buildMessage('Deploy SUCCESS', appName, targetEnv, build, log, ''))
}

def notifyTestError(appName, targetEnv, build, log) {
  slackSend(color: 'danger', message: buildMessage('Test ERROR', appName, targetEnv, build, log, ''))
}

def notifyTestSuccess(appName, targetEnv, build, log) {
  slackSend(color: 'good', message: buildMessage('Test SUCCESS', appName, targetEnv, build, log, ''))
}

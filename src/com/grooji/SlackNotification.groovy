package com.grooji;

class SlackNotification {
  public static buildMessage(action, appName, targetEnv, build, log, recentStage) {
    String msg = appName;
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

  public static notifyBuildStart(appName, targetEnv) {
    slackSend(color: 'neutral', message: buildMessage('Build Start', appName, targetEnv, '', '', ''))
  }

  public static notifyBuildSuccess(appName, targetEnv, build) {
    slackSend(color: 'good', message: buildMessage('Build SUCCESS', appName, targetEnv, build, '', ''))
  }

  public static notifyBuildError(appName, targetEnv, build, log, recentStage) {
    slackSend(color: 'danger', message: buildMessage('Build ERROR', appName, targetEnv, build, log, recentStage))
  }

  public static notifyApplySuccess(appName, targetEnv, build, log) {
    slackSend(color: 'good', message: buildMessage('Apply SUCCESS', appName, targetEnv, build, log, ''))
  }

  public static notifyDeployStatus(appName, targetEnv, build, status) {
    slackSend(color: 'neutral', message: buildMessage('Deploy Status', appName, targetEnv, build, status, ''))
  }

  public static notifyDeploySuccess(appName, targetEnv, build, log) {
    slackSend(color: 'good', message: buildMessage('Deploy SUCCESS', appName, targetEnv, build, log, ''))
  }

  public static notifyTestError(appName, targetEnv, build, log) {
    slackSend(color: 'danger', message: buildMessage('Test ERROR', appName, targetEnv, build, log, ''))
  }

  public static notifyTestSuccess(appName, targetEnv, build, log) {
    slackSend(color: 'good', message: buildMessage('Test SUCCESS', appName, targetEnv, build, log, ''))
  }
}

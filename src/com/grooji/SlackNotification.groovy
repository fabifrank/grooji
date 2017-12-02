package com.grooji;

class SlackNotification {
  public static String BUILD_START = 'Build Start';
  public static String BUILD_SUCCESS = 'Build SUCCESS';
  public static String BUILD_ERROR = 'Build ERROR';
  public static String APPLY_START = 'Apply Start'; // e.g. Kubernets Apply
  public static String APPLY_SUCCESS = 'Apply SUCCESS';
  public static String APPLY_ERROR = 'Apply ERROR';
  public static String DEPLOY_STATUS = 'Deploy Status';
  public static String DEPLOY_SUCCESS = 'Deploy SUCCESS';
  public static String DEPLOY_ERROR = 'Deploy ERROR';
  public static String TEST_SUCCESS = 'Test SUCCESS';
  public static String TEST_ERROR = 'Test ERROR';

  public static buildMessage(String action, String appName, String targetEnv, String build, String log, String recentStage) {
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

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

  public static String COLOR_SUCCESS = 'good'; // more easy to remember and lookup with auto completion
  public static String COLOR_INFO = 'neutral';
  public static String COLOR_ERROR = 'danger';

  /**
   * Build a slack notification based on parameters
   */
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

  /**
   * Send a message to slack
   */
  public static send(String color, String action, String appName, String targetEnv, String build, String log, String recentStage) {
    slackSend(color: color, message: buildMessage(action, appName, targetEnv, build, log, recentStage));
  }
}

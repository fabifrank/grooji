package com.grooji;

/**
 * We use a class in this script to define constants.
 */
class SlackNotification {
  public static final String BUILD_START = 'Build Start';
  public static final String BUILD_SUCCESS = 'Build SUCCESS';
  public static final String BUILD_ERROR = 'Build ERROR';
  public static final String APPLY_START = 'Apply Start'; // e.g. Kubernets Apply
  public static final String APPLY_SUCCESS = 'Apply SUCCESS';
  public static final String APPLY_ERROR = 'Apply ERROR';
  public static final String DEPLOY_STATUS = 'Deploy Status';
  public static final String DEPLOY_SUCCESS = 'Deploy SUCCESS';
  public static final String DEPLOY_ERROR = 'Deploy ERROR';
  public static final String TEST_SUCCESS = 'Test SUCCESS';
  public static final String TEST_ERROR = 'Test ERROR';

  public static String COLOR_SUCCESS = 'good'; // more easy to remember and lookup with auto completion
  public static String COLOR_INFO = 'neutral';
  public static String COLOR_ERROR = 'danger';

  /**
   * Build a slack notification based on parameters
   */
  public static String buildMessage(String action, String appName, String targetEnv, String build, String log, String recentStage) {
    String msg = '[' + action + '] ' + appName;
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
      msg += '\n' + log
    }
    return msg
  }
}

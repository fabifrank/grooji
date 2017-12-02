package com.grooji;

import com.grooji.Time;
import groovy.time.*;
import spock.lang.*;

class SlackNotificationSpec extends Specification {
  def 'Constants have correct values'() {
    expect:
      com.grooji.SlackNotification.BUILD_START == 'Build Start';
      com.grooji.SlackNotification.BUILD_SUCCESS == 'Build SUCCESS';
      com.grooji.SlackNotification.BUILD_ERROR == 'Build ERROR';
      com.grooji.SlackNotification.APPLY_START == 'Apply Start';
      com.grooji.SlackNotification.APPLY_SUCCESS == 'Apply SUCCESS';
      com.grooji.SlackNotification.APPLY_ERROR == 'Apply ERROR';
      com.grooji.SlackNotification.DEPLOY_STATUS == 'Deploy Status';
      com.grooji.SlackNotification.DEPLOY_SUCCESS == 'Deploy SUCCESS';
      com.grooji.SlackNotification.DEPLOY_ERROR == 'Deploy ERROR';
      com.grooji.SlackNotification.TEST_SUCCESS == 'Test SUCCESS';
      com.grooji.SlackNotification.TEST_ERROR == 'Test ERROR';
  }

  def 'Build slack messages'() {
    given:
      def action = 'Push artifact to server';
      def appName = 'Some Cool App';
      def targetEnv = 'integration';
      def build = '#23';
      def log = 'Deployment successful...';
      def recentStage = 'Deploy Step';

    when:
      def message = com.grooji.SlackNotification.buildMessage(action, appName, targetEnv, build, log, recentStage);

    then:
      message == '[Push artifact to server] Some Cool App\nEnv: integration\nBuild: #23\nStage: Deploy Step\nDeployment successful...';
  }
}



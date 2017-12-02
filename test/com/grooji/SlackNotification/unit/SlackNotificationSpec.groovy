package com.grooji;

import com.grooji.Time;
import groovy.time.*;
import spock.lang.*;

class SlackNotificationSpec extends Specification {
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



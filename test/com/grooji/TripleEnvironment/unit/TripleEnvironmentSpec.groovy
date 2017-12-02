package com.grooji;

import com.grooji.TripleEnvironment;
import groovy.time.*;
import spock.lang.*;

class TripleEnvironmentSpec extends Specification {
  def "Determine the stage to branches"() {
    given:
      def featureBranch = 'feature/XXX-100';
      def stagingBranch = 'staging';
      def productionBranch = 'production';

    when:
      def integration = com.grooji.TripleEnvironment.getStage(featureBranch);
      def staging = com.grooji.TripleEnvironment.getStage(stagingBranch);
      def production = com.grooji.TripleEnvironment.getStage(productionBranch);

    then:
      integration == 'integration';
      staging == 'staging';
      production == 'production';
  }
}



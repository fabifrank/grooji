package com.grooji;

import com.grooji.Security;
import groovy.time.*;
import spock.lang.*;

class SecuritySpec extends Specification {
  def "Remove credentials from connection string"() {
    given:
      def connection = 'https://hello:world@awesome-host/some-path';

    when:
      def result = com.grooji.Security.clearConnectionString(connection);

    then:
      result == 'https://xxx@awesome-host/some-path';
  }
}



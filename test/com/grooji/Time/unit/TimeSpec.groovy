package com.grooji;

import com.grooji.Time;
import groovy.time.*;
import spock.lang.*;

class TimeSpec extends Specification {
  def "Getting minutes as a string to duration works"() {
    given:
      def start = Date.parse("yyy-MM-dd'T'HH:mm:ss.SSSZ","2017-12-02T22:15:00.000+01:00".replace("+01:00","+0100"));
      def end = Date.parse("yyy-MM-dd'T'HH:mm:ss.SSSZ","2017-12-02T22:20:30.000+01:00".replace("+01:00","+0100"));
      TimeDuration duration = TimeCategory.minus(end, start);

    when:
      def minutes = com.grooji.Time.getMinutesStringForDuration(duration);

    then:
      minutes == "5.5";
  }
}



package com.grooji;

class Time {
  public static getMinutesStringForDuration(duration) {
    duration = duration.toMilliseconds()
    def d = duration / 1000 / 60;
    return d.toString()
  }
}

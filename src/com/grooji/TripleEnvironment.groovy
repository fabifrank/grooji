package com.grooji;

class TripleEnvironment {
  /**
   * When passing a git branch name return the stage to integrate the artifact into
   */
  public static getStage(String branchName) {
    switch (branchName) {
      case "staging":
        return "staging";
        break;
      case "production":
        return "production";
        break;
    }
    return 'integration';
  }
}

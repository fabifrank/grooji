package com.grooji;

def get() {
  switch (env.BRANCH_NAME) {
    case "staging":
      return "staging";
      break;
    case "production":
      return "production";
      break;
  }
  return 'dev';
}

package com.grooji;

/**
 * Given a connection string like 'https://<user>:<pass>@<host>...' this function removes the credentials from the string
 */
def static clearConnectionString(text) {
  return text.replaceFirst(/\/\/.*\:(.*)@/, "//xxx@")
}

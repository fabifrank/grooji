package com.grooji;

def static removePasswordFromConnectionString(text) {
  return text.replaceFirst(/\/\/.*\:(.*)@/, "XXX")
}

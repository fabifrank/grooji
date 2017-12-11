/**
 * This method updates the recent step on the main pipeline to be accessed from different methods within the pipeline
 * Some examples:
 * - mock functions to track execution of pipeline
 * - log execution to slack etc..
 */
def call(String name) {
  recentStep = name;
}

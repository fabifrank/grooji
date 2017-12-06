import com.grooji.SlackNotification;
// we use a file within vars folder here to access the step closure of the pipeline where the slackSend has been defined via
// the SlackNotification Plugin.
def call(String color, String action, String appName, String targetEnv = '', String build = '', String log = '', String recentStage = '') {
  final message = SlackNotification.buildMessage(action, appName, targetEnv, build, log, recentStage);
  slackSend(color: color, message: message)
}

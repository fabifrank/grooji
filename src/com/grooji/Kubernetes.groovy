package com.grooji;

public class Kubernetes {
  public static final STATUS_SUCCEEDED = "Succeeded";

  public static getAllPodsByNamespace(String namespace) {
    def pods = sh(returnStdout: true, script: "kubectl get pods -n ${namespace}");
    return pods;
  }

  public static getRunningPodStatus(String podPrefix, String namespace) {
    def pods = Kubernetes.getAllPodsByNamespace(namespace);
    def status = sh(returnStdout: true, script: 'echo "${pods}"  | grep ${podPrefix} | awk \'{ print ${3} }\'')
    return status
  }

  public static getRecentPodStatus(String podPrefix, String namespace) {
    sh("kubectl describe po ${podPrefix} -n ${namespace}  | grep -Eo 'Status' | cut -d: -f2 | tr -d '[:space:]' > .pod-status")
    def podStatus = readFile('.pod-status').trim()
    return podStatus
  }
}

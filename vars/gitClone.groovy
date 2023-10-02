def call(String giturl, String branch = "dev") {
    retry(5) {
        try {
            timeout(time: 1, unit: "MINUTES") {
                checkout([$class: "GitSCM",
                    branches: [[name: "*/${branch}"]],
                    extensions: [[$class: 'SubmoduleOption', parentCredentials: true, recursive: true], [$class: 'CleanBeforeCheckout', deleteUntrackedNestedRepositories: true]],
                    submoduleCfg: [],
                    userRemoteConfigs: [[credentialsId: 'github', url: giturl]]
                ])
            }
            sh "git submodule update --remote"
        }catch (Exception e) {
            sleep(time:5,unit:"SECONDS")
            error("Something went wrong during git clone: " + e)
        }
    }
}
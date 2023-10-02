@NonCPS
def call(moduleString) {
    def moduleMap = [:]
    moduleString.eachLine { line ->
        def matcher = line =~ /(\S+)\s*=\s*"([^"]*)"/
        if (matcher) {
            moduleMap[matcher.group(1).trim()] = matcher.group(2).trim()
        }
    }
    return moduleMap
}
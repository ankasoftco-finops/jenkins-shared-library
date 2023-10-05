@NonCPS
def call(moduleString) {
    def moduleMap = [:]
    moduleString.eachLine { line ->
        def matcher = line =~ /(\S+)\s*=\s*(".*?"|\S+)/
        if (matcher) {
            moduleMap[matcher.group(1).trim()] = matcher.group(2).trim()
        }
    }
    return moduleMap
}
// NonCPS is required for this function to work properly
// /(\S+)\s*=\s*"([^"]*)"/
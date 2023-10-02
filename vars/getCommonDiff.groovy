def call(currentConfig, newConfig) {
    def currentMap = parseTerraformModule(currentConfig)
    def newMap = parseTerraformModule(newConfig)
    newMap.each { key, value ->
        if (value != null && value != "") {
            currentMap[key] = value
        }
        echo "key: ${key}, value: ${value}"
    }
    def updatedModule = formatTerraformModule(currentMap)

    return updatedModule
}

@NonCPS
def parseTerraformModule(moduleString) {
    def moduleMap = [:]
    moduleString.eachLine { line ->
        def matcher = line =~ /(\S+)\s*=\s*"([^"]*)"/
        if (matcher) {
            echo "matcher: ${matcher}"
            echo "matcher: ${matcher.group(1).trim()}"
            moduleMap[matcher.group(1).trim()] = matcher.group(2).trim()
        }
    }
    return moduleMap
}

def formatTerraformModule(moduleMap) {
    def formattedModule = "module \"${moduleMap['module']}\" {\n"
    moduleMap.each { key, value ->
        if (key != 'module') {
            formattedModule += "    ${key} = \"${value}\"\n"
        }
    }
    formattedModule += "}\n# end ${moduleMap['module']}"
    return formattedModule
}

def call(currentConfig, newConfig) {
    def currentMap = parseTerraformModule(currentConfig)
    def newMap = parseTerraformModule(newConfig)
    newMap.each { key, value ->
        if (value != null && value != "") {
            currentMap[key] = value
        }
        echo "key: ${key}, value: ${value}"
    }
    def moduleName = inputText =~ /module\s+"([^"]+)"/
    def updatedModule = formatTerraformModule(currentMap, moduleName[0][1])

    return updatedModule
}

@NonCPS
def parseTerraformModule(moduleString) {
    def moduleMap = [:]
    moduleString.eachLine { line ->
        def matcher = line =~ /(\S+)\s*=\s*"([^"]*)"/
        if (matcher) {
            moduleMap[matcher.group(1).trim()] = matcher.group(2).trim()
        }
    }
    return moduleMap
}

def formatTerraformModule(moduleMap,moduleName) {
    def formattedModule = "module \"${moduleName}\" {\n"
    moduleMap.each { key, value ->
        if (key != 'module') {
            formattedModule += "    ${key} = \"${value}\"\n"
        }
    }
    formattedModule += "}\n# end ${moduleName}"
    return formattedModule
}

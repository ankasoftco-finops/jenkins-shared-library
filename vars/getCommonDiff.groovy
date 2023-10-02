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

def parseTerraformModule(moduleString) {
    def moduleMap = [:]
    moduleString.eachLine { line ->
        if (line =~ /(\S+)\s*=\s*"([^"]*)"/) {
            moduleMap[line[1].trim()] = line[2].trim()
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

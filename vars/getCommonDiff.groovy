def call(currentConfig, newConfig) {
    def currentMap = parseTerraformModule(currentConfig)
    def newMap = parseTerraformModule(newConfig)
    newMap.each { key, value ->
        if (value != null && value != "" && !value.contains("\$")) {
            currentMap[key] = value
        }
    }
    def moduleName = currentConfig =~ /module\s+"([^"]+)"/
    def updatedModule = formatTerraformModule(currentMap, moduleName[0][1])

    return updatedModule
}

def formatTerraformModule(moduleMap,moduleName) {
    def formattedModule = "module \"${moduleName}\" {\n"
    moduleMap.each { key, value ->
        if (key != 'module') {
            if (value.isNumber()) {
                formattedModule += "    ${key} = ${value}\n"
            } else {
                formattedModule += "    ${key} = \"${value}\"\n"
            }
        }
    }
    formattedModule += "}\n# end ${moduleName}"
    return formattedModule
}

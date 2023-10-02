def getCommonDiff(current, new) {
    def currentMap = parseTerraformModule(current)
    def newMap = parseTerraformModule(new)
    newMap.each { key, value ->
        if (value != null && value != "") {
            currentMap[key] = value
        }
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

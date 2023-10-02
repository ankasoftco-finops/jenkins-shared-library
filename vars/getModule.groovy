def call(String fileName,String moduleName) {
    def bas =  "module "+module_name+" {"
    def son = "# end "+moduleName
    def file = readFile(fileName)
    def modBasIndeks = file.indexOf(bas)
    def modSonIndeks = file.indexOf(son)
    def currentConfig = ""
    
    if (modBasIndeks != -1 && modSonIndeks != -1) {
        currentConfig = file.substring(modBasIndeks, modSonIndeks + endModule.length()) 
    }
    return currentConfig
}
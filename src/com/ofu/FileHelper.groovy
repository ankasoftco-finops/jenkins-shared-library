package com.ofu

class FileHelper {

    String readFileWPath(String main) {
        def file = readFile(main).text
        return file
    }

    def substringString(File file,String bas, String son) {
        def modBasIndeks = file.indexOf(bas)
        def modSonIndeks = file.indexOf(son)
        
        if (modBasIndeks != -1 && modSonIndeks != -1) {
            currentConfig = file.substring(modBasIndeks, modSonIndeks + endModule.length()) 
        }
        return currentConfig
    }

}
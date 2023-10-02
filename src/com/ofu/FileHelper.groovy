class FileHelper {

    String readFile(String main) {
        def file = new File(main)
        def fileContents = file.text
        return fileContents
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
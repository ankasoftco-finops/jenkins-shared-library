import com.ofu.FileHelper

String call(String main, String newConfig, String moduleName) {
    def fileHelper = new FileHelper()
    def currentConfig = fileHelper.substringString(readFile(main), "module "+module_name+" }","# end "+moduleName)
    return currentConfig
}

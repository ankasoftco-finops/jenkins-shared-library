import com.ofu.FileHelper

String call(String main, String newConfig, String moduleName) {
    def fileHelper = new FileHelper()
    def currentConfig = fileHelper.readFile(main)
    return currentConfig
}

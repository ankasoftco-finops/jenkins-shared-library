import com.ofu.FileHelper

String call(File currentConfig, File newConfig, String moduleName) {
    def fileHelper = new FileHelper()
    return fileHelper.substringString("module "+ moduleName+ " {", "# end" + moduleName )
}

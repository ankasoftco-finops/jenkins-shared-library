import com.ofu.FileHelper

String call(String main, String newConfig, String moduleName) {
    return (new FileHelper()).readFile(main)
}

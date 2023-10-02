import com.ofu.FileHelper

def call(String main, String newConfig) {
    return FileHelper.substringString(main, newConfig)
}

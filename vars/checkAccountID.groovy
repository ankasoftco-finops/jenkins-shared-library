void call(String accountId, String accountName){
    accountIdRegex = '^[0-9]+$'
    if (!(accountId =~ accountIdRegex)){
        def message = "account id '${accountId}' does not match regex for account ids"
        echo message
        error message
    }
}
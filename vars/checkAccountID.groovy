void call(String accountId){
    accountIdRegex = '^[0-9]+$'
    if (!(accountId =~ accountIdRegex)){
        def message = "account id '${accountId}' does not match regex for account ids"
        echo message
        error message
    }else{
        echo "account id '${accountId}' matches regex for account ids"
    }
}
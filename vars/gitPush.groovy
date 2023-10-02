def call(String message) {    
    sh """
        git checkout dev
        git branch
        git add .
        git diff-index --quiet HEAD || \
        git \
            -c user.name=\"omerulusoy41\" \
            -c user.email=\"ulusoyomerfaruk29@gmail.com\" \
            commit -m "${message}"
        git push -u origin dev
    """
}
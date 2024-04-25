package com.ecommercwebsite.aioscrm.network

enum class HttpErrorCode(val code: Int) {
    /**
     * error in connecting to repository (Server or Database)
     */
    NO_CONNECTION(1003),

    /**
     * error in getting value (Json Error, Server Error, etc)
     */
    BAD_RESPONSE(500),

    /**
     * Time out  error
     */
    TIMEOUT(1003),

    /**
     * no data available in repository
     */
    EMPTY_RESPONSE(422),

    /**
     * an unexpected error
     */
    NOT_DEFINED(500),

    /**
     * bad credential
     */
    UNAUTHORIZED(401),

    /**
     * new version
     */
    NEW_VERSION_FOUND(222),


    /**
     * server under maintenance
     */
    SERVER_UNDER_MAINTENANCE(433),

    /**
     * Job Cancel
     */
    JOB_CANCEL(1980)
}
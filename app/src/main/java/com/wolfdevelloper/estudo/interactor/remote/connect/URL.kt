package com.wolfdevelloper.estudo.interactor.remote.connect

class URL {

    companion object {
        private val URL_SERVICE = "https://bank-app-test.herokuapp.com"
        private val URL_NAMESERVICE = "/api/"
        val WEB_SERVICE = URL_SERVICE + URL_NAMESERVICE
        val REQUEST_METHOD_GET = "GET"
    }
}
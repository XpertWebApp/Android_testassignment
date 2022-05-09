package com.app.peyza.base

import java.io.IOException

class ApiException(message: String) : IOException(message)
class NoInternetException(message: String) : IOException(message)
class UnAuthorizedAdiException(message: String) : IOException(message)
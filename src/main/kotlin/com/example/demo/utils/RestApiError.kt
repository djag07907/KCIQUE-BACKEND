package com.example.demo.utils

import org.springframework.http.HttpStatus

class RestApiError(val httpStatus: HttpStatus, val errorMessage:String, val errorDetails:String) {
}
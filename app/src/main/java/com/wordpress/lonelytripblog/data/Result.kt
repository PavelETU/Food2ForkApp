package com.wordpress.lonelytripblog.data


sealed class Result<out R> {
    data class Error(val message: String): Result<Nothing>()
    data class Success<out R>(val result: R): Result<R>()
}
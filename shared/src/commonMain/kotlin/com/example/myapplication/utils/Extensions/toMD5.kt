package com.example.myapplication.utils.Extensions

import okio.ByteString
import okio.ByteString.Companion.encodeUtf8

fun String.toMD5(): String {
    return this.encodeUtf8().md5().hex()
}

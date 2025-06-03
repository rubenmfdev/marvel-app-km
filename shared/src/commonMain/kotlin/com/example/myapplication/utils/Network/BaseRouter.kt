package com.example.myapplication.utils.Network

import io.ktor.http.HttpMethod

interface BaseRouter {
    val baseUrl: String
    val path: String
    val method: HttpMethod
    val headers: Map<String, String>
    val queryParams: Map<String, String>?
    val body: Any?
    val authType: AuthType

    val fullUrl: String
        get() = "$baseUrl/$path".replace("//", "/")

    val finalQueryParams: Map<String, String>
        get() {
            val actualParams = queryParams
            val authParams = authType.getParams()
            return if (actualParams != null) {
                authParams + actualParams
            } else {
                authParams
            }
        }

}

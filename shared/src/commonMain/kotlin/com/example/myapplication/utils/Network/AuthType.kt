package com.example.myapplication.utils.Network

import com.example.myapplication.utils.Extensions.toMD5
import kotlinx.datetime.Clock

enum class AuthType {
    NONE, BASIC, OAUTH2, MARVEL;

    fun getParams(): Map<String, String> {
        return when (this) {
            NONE, BASIC, OAUTH2 -> emptyMap()
            MARVEL -> {
                val timestamp = Clock.System.now().toEpochMilliseconds().toString()
                mapOf(
                    "apikey" to "ADD_API_KEY",
                    "ts" to timestamp,
                    "hash" to getHash(timestamp)
                )
            }
        }
    }

    private fun getHash(timestamp: String): String {
        val privateKey = "ADD_API_KEY"
        val publicKey = "ADD_API_KEY"
        return (timestamp + privateKey + publicKey).toMD5()
    }
}

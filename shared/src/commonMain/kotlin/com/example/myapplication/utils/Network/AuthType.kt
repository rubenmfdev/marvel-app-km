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
                    "apikey" to "66a2342235b5a50f11854a107817b93f",
                    "ts" to timestamp,
                    "hash" to getHash(timestamp)
                )
            }
        }
    }

    private fun getHash(timestamp: String): String {
        val privateKey = "8e626a06a59f36334eacd37b873e2345b4562bd7"
        val publicKey = "66a2342235b5a50f11854a107817b93f"
        return (timestamp + privateKey + publicKey).toMD5()
    }
}

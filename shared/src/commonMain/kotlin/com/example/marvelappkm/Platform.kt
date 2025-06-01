package com.example.marvelappkm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
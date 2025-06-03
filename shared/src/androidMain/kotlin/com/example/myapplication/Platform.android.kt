package com.example.myapplication

class AndroidPlatform : Platform {
    override val name: String = "Android Ruben ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()
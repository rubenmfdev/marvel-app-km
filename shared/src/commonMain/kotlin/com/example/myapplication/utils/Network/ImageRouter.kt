package com.example.myapplication.utils.Network

data class ImageRouter(
    val urlString: String
) : BaseRouter {
    override val baseUrl: String = "" // Not used in this case
    override val path: String = urlString
    override val method = io.ktor.http.HttpMethod.Get
    override val headers: Map<String, String> = emptyMap()
    override val body: Any? = null
    override val queryParams: Map<String, String>? = null
    override val authType: AuthType = AuthType.NONE
}

package br.com.stefanini.imgur.data.interceptores

import br.com.stefanini.imgur.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

internal class AuthInterceptor : Interceptor {

    companion object {
        const val HEADER_CLIENT_ID: String = "Client-ID"
        const val HEADER_AUTHORIZATION: String = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        val request: Request = chain.request().newBuilder().apply {
            addHeader(HEADER_AUTHORIZATION, "$HEADER_CLIENT_ID ${BuildConfig.API_CLIENT_ID}")
        }.build()

        return chain.proceed(request)
    }
}
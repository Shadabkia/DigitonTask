package com.digitoon.task.data.network

import com.digitoon.task.utils.APPLICATION_JSON_HEADER_KEY
import com.digitoon.task.utils.AUTHORIZATION_HEADER_KEY
import com.digitoon.task.utils.CONTENT_TYPE_HEADER_KEY
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class HeaderInterceptor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        requestBuilder.addHeader(
            CONTENT_TYPE_HEADER_KEY, APPLICATION_JSON_HEADER_KEY
        )

        "token".let {
            requestBuilder.addHeader(
                AUTHORIZATION_HEADER_KEY, "Bearer $it"
            )
            Timber.tag("okhttp Header: ").i("Token: Bearer $it")
        }

        val request = requestBuilder.build()

        return chain.proceed(request)
    }
}
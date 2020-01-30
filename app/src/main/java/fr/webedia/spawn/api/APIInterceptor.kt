package fr.webedia.spawn.api

import okhttp3.*

class APIInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return initInterceptor(chain)
    }

    private fun initInterceptor(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val signRequest = signRequest(request).build()
        return chain.proceed(signRequest) as Response
    }

    private fun signRequest(request: Request): Request.Builder {
        val builder = request.newBuilder()
        //builder.addHeader(CALLER_HEADER_LABEL, CALLER_HEADER_KEY)
        return builder
    }

}
package com.learn.pertemuan6sindi.repository

import com.learn.pertemuan6sindi.model.Quote
import com.learn.pertemuan6sindi.services.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuoteRepository {

    val QuoteApiUrl = "https://dummyjson.com/"

    private val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(QuoteApiUrl)
            .addConverterFactory(
                GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

    suspend fun getRandomQuote(): Quote {
        return api.getRandomQuote()
    }
}
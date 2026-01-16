package com.learn.pertemuan6sindi.services

import com.learn.pertemuan6sindi.model.Quote
import retrofit2.http.GET

interface ApiService {
    @GET("quotes/random")
    suspend fun getRandomQuote(): Quote
}
package com.repose.practical.mvvm.data.api

import com.repose.practical.mvvm.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("everything?domains=wsj.com&apiKey=eba9271e377941d3a919fe2e1032904d")
    suspend fun getUsers(): Response<NewsResponse>

}
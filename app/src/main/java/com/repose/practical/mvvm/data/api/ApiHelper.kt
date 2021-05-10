package com.repose.practical.mvvm.data.api

import com.repose.practical.mvvm.data.model.NewsResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getUsers(): Response<NewsResponse>
}
package com.repose.practical.mvvm.data.api

import com.repose.practical.mvvm.data.model.NewsResponse
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getUsers(): Response<NewsResponse> = apiService.getUsers()

}
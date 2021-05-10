package com.repose.practical.mvvm.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

class NewsResponse: Serializable{
    @SerializedName("articles")
    val articles: List<Article> = arrayListOf()
    @SerializedName("status")
    val status: String = ""
    @SerializedName("totalResults")
    val totalResults: Int = 0
}

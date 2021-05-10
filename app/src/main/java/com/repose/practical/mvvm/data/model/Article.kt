package com.repose.practical.mvvm.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Article : Serializable {
    @SerializedName("author")
    val author: String = ""

    @SerializedName("content")
    val content: String = ""

    @SerializedName("description")
    val description: String = ""

    @SerializedName("publishedAt")
    val publishedAt: String = ""

    @SerializedName("source")
    lateinit var source: Source

    @SerializedName("title")
    val title: String = ""

    @SerializedName("url")
    val url: String = ""

    @SerializedName("urlToImage")
    val urlToImage: String = ""
}
package com.example.bookshelf.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


interface ApiService {
    @GET("volumes?q=flowers+inauthor:keyes&key=AIzaSyATlVZUr9lFT2NMJf3WxP4VjqHuNNbZeLY")
    suspend fun getBook(): BooksApi
}

object ApiNetwork {
    // moshi
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.googleapis.com/books/v1/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

}
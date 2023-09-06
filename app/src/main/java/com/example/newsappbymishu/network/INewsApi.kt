package com.example.newsappbymishu.network

import com.example.newsappbymishu.model.TopNewsHeadlineModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/*https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=8e17db42bce34ca3825e28091e0ec8a7*/
interface INewsApi {
    @GET("top-headlines?sources=techcrunch&apiKey=8e17db42bce34ca3825e28091e0ec8a7")
    suspend fun getTopNewsHeadlineList() : Response<TopNewsHeadlineModel>

    companion object {
        var retrofitService: INewsApi? = null
        fun getInstance() : INewsApi {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(INewsApi::class.java)
            }
            return retrofitService!!
        }

    }
}
package com.rawtooth.admin_waste

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*

interface MyApi {
    @Multipart
    @POST("category/")
    fun uploadImage(
        @Header("Authorization") token : String,
        @Part image: MultipartBody.Part,
        @Part("description") desc: RequestBody,
        @Part("name") name: RequestBody,
        ): Call<String>
    companion object {
        operator fun invoke(): MyApi{
            return Retrofit.Builder()
                .baseUrl("http://192.168.43.251:9090/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}
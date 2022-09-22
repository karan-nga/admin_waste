package com.rawtooth.ngoregister

import com.rawtooth.Constant
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface MyNgoApi {
    @Multipart
    @POST("/ngo/")
    fun uplodImage(
        @Header("Authorization") token : String,
        @Part image: MultipartBody.Part,
        @Part("description") desc: RequestBody,
        @Part("name") name: RequestBody,
        @Part("location") location:RequestBody,
        @Part("ngoType") ngoType:RequestBody
    ): Call<String>
    companion object{
        operator fun invoke():MyNgoApi{
            return Retrofit.Builder()
                .baseUrl(Constant.baseurl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyNgoApi::class.java)
        }
    }
}
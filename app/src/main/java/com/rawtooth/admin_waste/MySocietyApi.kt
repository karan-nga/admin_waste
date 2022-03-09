package com.rawtooth.admin_waste
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*
interface MySocietyApi {
    @Multipart
    @POST("society/")
    fun uploadImage2(
        @Header("Authorization") token : String,
        @Part societyImage: MultipartBody.Part,
        @Part("name") name: RequestBody,
        @Part("email") email: RequestBody,
        @Part("address") address:RequestBody
    ): Call<String>
    companion object {
        operator fun invoke():MySocietyApi{
            return Retrofit.Builder()
                .baseUrl("http://192.168.43.251:9090/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(MySocietyApi::class.java)
        }

    }
}
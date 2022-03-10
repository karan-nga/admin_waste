package com.rawtooth.industryregister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.easyvolley.Callback
import com.easyvolley.EasyVolleyError
import com.easyvolley.EasyVolleyResponse
import com.easyvolley.NetworkClient
import com.google.gson.Gson
import com.rawtooth.admin_waste.databinding.ActivityIndustryRegisterBinding
import com.rawtooth.admin_waste.tokn

class IndustryRegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityIndustryRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityIndustryRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.industrySubmit.setOnClickListener{
            call()
        }
    }

    private fun call() {
        val name=binding.indusrtyName.text.toString()
        val email=binding.industryEmail.text.toString()
        val industryType=binding.spinnerType.selectedItem.toString()
        val description=binding.industryDescription.text.toString()
        val sector=binding.spinnerSector.selectedItem.toString()
        val address=binding.industryAddress.text.toString()
        val body= Gson().toJson(IndustyPost(name,email,industryType,description, sector,address))
        NetworkClient.post("http://192.168.0.145:9090/industry/")
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer $tokn")
            .addHeader("Content-Length", Integer.toString(body.length))
            .addHeader("Accept", "application/json")
            .setRequestBody(body)
            .setCallback(object :Callback<IndustyModel>{
                override fun onSuccess(t: IndustyModel?, response: EasyVolleyResponse?) {
                    Log.d("code",t.toString())
                }

                override fun onError(error: EasyVolleyError?) {
                    if (error != null) {
                        Log.e("code",error.mMessage.toString())
                    }
                }
            }).execute()
    }
}
package com.rawtooth.slumarea

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.easyvolley.Callback
import com.easyvolley.EasyVolleyError
import com.easyvolley.EasyVolleyResponse
import com.easyvolley.NetworkClient
import com.google.gson.Gson
import com.rawtooth.Constant
import com.rawtooth.admin_waste.databinding.ActivitySlumRegisterBinding
import com.rawtooth.admin_waste.tokn

class SlumRegister : AppCompatActivity() {
    lateinit var binding:ActivitySlumRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySlumRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.registerslum.setOnClickListener {
            val email=binding.slumEmail.text.toString()
            val contact=binding.slumContact.text.toString()
            val desc=binding.slumDescription.text.toString()
            val location=binding.slumLocation.text.toString()
           if(!email.isEmpty()&&!contact.isEmpty()&&!desc.isEmpty()&&!location.isEmpty()){
               register(email,contact,desc,location)
           }
            else{
                Toast.makeText(this,"Please enter all the details",Toast.LENGTH_SHORT).show()
           }
        }

    }

    private fun register(email: String, contact: String, desc: String, location: String) {
        val body=Gson().toJson(SlumModel(contact,desc,email,location))
        NetworkClient.post("${Constant.baseurl}/slum-waste/")
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer $tokn")
            .addHeader("Content-Length", Integer.toString(body.length))
            .addHeader("Accept", "application/json")
            .setRequestBody(body)
            .setCallback(object :Callback<SlumPost>{
                override fun onSuccess(t: SlumPost?, response: EasyVolleyResponse?) {
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
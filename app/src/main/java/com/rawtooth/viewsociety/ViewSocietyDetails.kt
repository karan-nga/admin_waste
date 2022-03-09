package com.rawtooth.viewsociety

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.easyvolley.Callback
import com.easyvolley.EasyVolleyError
import com.easyvolley.EasyVolleyResponse
import com.easyvolley.NetworkClient
import com.rawtooth.admin_waste.databinding.ActivityViewSocietyDetailsBinding
import com.rawtooth.admin_waste.tokn

class ViewSocietyDetails : AppCompatActivity() {
    lateinit var binding: ActivityViewSocietyDetailsBinding
    lateinit var adapter:GetSocietyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityViewSocietyDetailsBinding.inflate(layoutInflater)
        adapter= GetSocietyAdapter(this)
        binding.recycle.adapter=adapter
        binding.recycle.layoutManager=LinearLayoutManager(this)
        setContentView(binding.root)
        NetworkClient.get("http://192.168.43.251:9090/society/getAllSociety")
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer $tokn")
            .addHeader("Accept", "application/json")
            .setCallback(object : Callback<SocietyDetailsModel> {
                override fun onSuccess(t: SocietyDetailsModel?, response: EasyVolleyResponse?) {

                    if (t != null) {
                        Log.d("code",t.toString())
                        adapter.setData(t)
                    }
                }

                override fun onError(error: EasyVolleyError?) {
                    Log.e("code","Error"+error!!.mStatusCode.toString())
                }
            })
            .execute()
    }
}
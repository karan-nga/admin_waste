package com.rawtooth.getAllCategory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Network
import com.easyvolley.Callback
import com.easyvolley.EasyVolleyError
import com.easyvolley.EasyVolleyResponse
import com.easyvolley.NetworkClient
import com.rawtooth.admin_waste.R
import com.rawtooth.admin_waste.databinding.GetAllActivityBinding
import com.rawtooth.admin_waste.tokn


class GetAllCategoryActivity : AppCompatActivity() {
    lateinit var binding: GetAllActivityBinding
    lateinit var adapter:Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= GetAllActivityBinding.inflate(layoutInflater)
        adapter = Adapter(this)
        binding.recycle.adapter=adapter
        binding.recycle.layoutManager=LinearLayoutManager(this@GetAllCategoryActivity)

        setContentView(binding.root)

        NetworkClient.get("http://192.168.0.145:9090/category/getAllWasteCategory")
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer $tokn")
            .addHeader("Accept", "application/json")
            .setCallback(object : Callback<GetAllModel>{
                override fun onSuccess(t: GetAllModel?, response: EasyVolleyResponse?) {

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
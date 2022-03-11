package com.rawtooth.getngodetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.easyvolley.Callback
import com.easyvolley.EasyVolleyError
import com.easyvolley.EasyVolleyResponse
import com.easyvolley.NetworkClient
import com.rawtooth.admin_waste.R
import com.rawtooth.admin_waste.databinding.ActivityGetNgoDetailsBinding
import com.rawtooth.admin_waste.tokn
import com.rawtooth.getindustrydetails.GetIndustryDetailAdapter

class GetNgoDetailsActivity : AppCompatActivity() {
    lateinit var binding:ActivityGetNgoDetailsBinding
    lateinit var adapter: GetNgoDetailAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGetNgoDetailsBinding.inflate(layoutInflater)
        adapter= GetNgoDetailAdapter(this)
        binding.recycle.adapter=adapter
        binding.recycle.layoutManager=LinearLayoutManager(this)
        setContentView(binding.root)
        NetworkClient.get("http://192.168.43.251:9090/industry/getAllIndustries")
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer $tokn")
            .addHeader("Accept", "application/json")
            .setCallback(object:Callback<GetNgoDetailsModel>{
                override fun onSuccess(t: GetNgoDetailsModel?, response: EasyVolleyResponse?) {
                    if (t != null) {
                        Log.d("code",t.toString())
                        adapter.setData(t)
                    }
                }

                override fun onError(error: EasyVolleyError?) {
                    Log.e("code","Error"+error!!.mStatusCode.toString())
                }
            }).execute()
    }
}
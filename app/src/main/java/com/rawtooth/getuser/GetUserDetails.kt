package com.rawtooth.getuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.easyvolley.Callback
import com.easyvolley.EasyVolleyError
import com.easyvolley.EasyVolleyResponse
import com.easyvolley.NetworkClient
import com.rawtooth.admin_waste.R
import com.rawtooth.admin_waste.databinding.ActivityGetUserDetailsBinding
import com.rawtooth.admin_waste.tokn
import com.rawtooth.getAllCategory.Adapter

class GetUserDetails : AppCompatActivity() {
    lateinit var binding: ActivityGetUserDetailsBinding
    lateinit var adapter: GetUserDetailAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGetUserDetailsBinding.inflate(layoutInflater)
        adapter=GetUserDetailAdapter(this)
        binding.recycle.adapter=adapter
        binding.recycle.layoutManager=LinearLayoutManager(this)
        setContentView(binding.root)


        NetworkClient.get("http://192.168.0.145:9090/user/")
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer $tokn")
            .addHeader("Accept", "application/json")
            .setCallback(object :Callback<UserModel>{
                override fun onSuccess(t: UserModel?, response: EasyVolleyResponse?) {
                    if(t!=null){
                        Log.d("code",t.toString())
                        adapter.setData(t)
                    }
                }

                override fun onError(error: EasyVolleyError?) {
                    Log.e("code","Error"+error!!.mStatusCode.toString() )
                }
            }).execute()
    }
}
package com.rawtooth.admin_waste

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.easyvolley.Callback
import com.easyvolley.EasyVolleyError
import com.easyvolley.EasyVolleyResponse
import com.easyvolley.NetworkClient
import com.google.gson.Gson
import com.rawtooth.admin_waste.databinding.ActivityAdminLoginBinding
import com.rawtooth.admin_waste.models.TokenResponse
import com.rawtooth.admin_waste.roomdb.User
import com.rawtooth.admin_waste.roomdb.UserDatabase
import com.rawtooth.admin_waste.user.details
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
lateinit var tokn:String
class admin_login : AppCompatActivity(), View.OnClickListener {
    lateinit var loginBinding: ActivityAdminLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityAdminLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)
        loginBinding.loginbtn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val name=loginBinding.username.text.toString()
        val password=loginBinding.password.text.toString()
        onCheck(name,password)
    }

    private fun onCheck(name: String, password: String) {
        val body=Gson().toJson(LoginPost(name,password))
        NetworkClient.post("http://192.168.43.251:9090/generatetoken")
            .addHeader("Content-Type", "application/json")
            .addHeader("Content-Length", Integer.toString(body.length))
            .addHeader("Accept", "application/json")
            .setRequestBody(body)
            .setCallback(object:Callback<TokenResponse>{
                override fun onSuccess(t: TokenResponse?, response: EasyVolleyResponse?) {
                    Log.d("code",t.toString())
                    if (t != null) {
                        if(t.user.authorities[0].authority=="ADMIN"){
                            startActivity(Intent(this@admin_login, MainActivity::class.java))
                            tokn=t.token
                            val obj= details(t.user.username,t.user.email,t.user.phonenumber,t.token)
                            val userInfo = User(null,obj.name,obj.number,obj.email,obj.token)
                            GlobalScope.launch(Dispatchers.IO){
                                UserDatabase.getInstance(applicationContext).userDao().insert(userInfo)
                            }


                        }
                    }

                }

                override fun onError(error: EasyVolleyError?) {

                   Log.e("code","Error"+error!!.mStatusCode.toString() )
                    if(error.mStatusCode==500){
                        Toast.makeText(this@admin_login,"Invalid username and password please try again!",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }).execute()

    }
}
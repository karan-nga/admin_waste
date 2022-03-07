package com.rawtooth.admin_waste

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.easyvolley.NetworkClient
import com.rawtooth.admin_waste.databinding.ActivityMainBinding
import com.rawtooth.admin_waste.user.user_detail
import com.rawtooth.getAllCategory.GetAllCategoryActivity
import com.rawtooth.getuser.GetUserDetails

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        NetworkClient.init(application)
        binding.newTask.setOnClickListener{
            startActivity(Intent(this,AddCategory::class.java))
        }
        binding.btnViewCategory.setOnClickListener{
            startActivity(Intent(this,GetAllCategoryActivity::class.java))
        }
        binding.btnViewUser.setOnClickListener{
            startActivity(Intent(this,GetUserDetails::class.java))
        }

    }
    fun home(menuItem: MenuItem){
        startActivity(Intent(this,MainActivity::class.java))
    }
    fun user(menuItem: MenuItem){
        startActivity(Intent(this, user_detail::class.java))
    }
    fun login(menuItem: MenuItem){
        startActivity(Intent(this,admin_login::class.java))
    }
}
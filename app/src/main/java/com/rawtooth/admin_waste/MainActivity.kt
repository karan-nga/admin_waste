package com.rawtooth.admin_waste

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.easyvolley.NetworkClient
import com.rawtooth.admin_waste.databinding.ActivityMainBinding
import com.rawtooth.admin_waste.user.user_detail
import com.rawtooth.getAllCategory.GetAllCategoryActivity
import com.rawtooth.getindustrydetails.GetIndustryDetailsActivity
import com.rawtooth.getuserdetail.GetUserDetails
import com.rawtooth.industryregister.IndustryRegisterActivity
import com.rawtooth.viewsociety.ViewSocietyDetails

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        setSupportActionBar(mainBinding.topAppBar)
        toggle= ActionBarDrawerToggle(this,mainBinding.mainDrawer,R.string.open,R.string.close)
        mainBinding.mainDrawer.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        NetworkClient.init(application)
        mainBinding.newTask.setOnClickListener{
            startActivity(Intent(this,AddCategory::class.java))
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
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
    fun viewCategory(menuItem: MenuItem){
        startActivity(Intent(this,GetAllCategoryActivity::class.java))
    }
    fun viewUser(menuItem: MenuItem){
        startActivity(Intent(this,GetUserDetails::class.java))
    }
fun ngoRegister(menuItem: MenuItem){
    startActivity(Intent(this,NgoRegister::class.java))
}
    fun societyRegister(menuItem: MenuItem){
        startActivity(Intent(this,SocietyRegistration::class.java))
    }
    fun viewSociety(menuItem: MenuItem){
        startActivity(Intent(this,ViewSocietyDetails::class.java))
    }
    fun industryRegister(menuItem: MenuItem){
        startActivity(Intent(this,IndustryRegisterActivity::class.java))
    }
    fun viewIndustry(menuItem: MenuItem){
        startActivity(Intent(this,GetIndustryDetailsActivity::class.java))
    }
}
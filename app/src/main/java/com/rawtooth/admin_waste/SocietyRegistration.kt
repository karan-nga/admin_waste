package com.rawtooth.admin_waste

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.rawtooth.admin_waste.databinding.ActivitySocietyRegistrationBinding

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class SocietyRegistration : AppCompatActivity(), View.OnClickListener,UploadRequestBody.UploadCallBack {
    lateinit var binding: ActivitySocietyRegistrationBinding
    private var selectImage: Uri?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySocietyRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnBrowse.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener{
            uploadImage()
        }
    }

    private fun uploadImage() {
        if(selectImage==null){
            binding.layoutRoot.snackbar("Select image first")
            return
        }
        val parcelFileDescriptor = contentResolver.openFileDescriptor(selectImage!!,"r",null)?: return
        val file = File(cacheDir,contentResolver.getFileName(selectImage!!))
        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)
        binding.progressBar.progress=0
        val name=binding.societyNmae.text.toString()
        val email=binding.societyEmail.text.toString()
        val address=binding.societyAddress.text.toString()
        val categoryImage=UploadRequestBody(file,"image",this)
        MySocietyApi().uploadImage2("Bearer $tokn",
            MultipartBody.Part.createFormData("societyImage",file.name,categoryImage),
            RequestBody.create(MediaType.parse("multipart/form-data"), name),
            RequestBody.create(MediaType.parse("multipart/form-data"), email),
            RequestBody.create(MediaType.parse("multipart/form-data"),address))
            .enqueue(object :retrofit2.Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    binding.progressBar.progress=100
                    Log.d("code",response.body().toString())
                    binding.layoutRoot.snackbar(response.body().toString())
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    t.message?.let { binding.layoutRoot.snackbar(it) }
                    t.message?.let { Log.e("code", it) }
                }
            })
    }

    override fun onClick(p0: View?) {
        Intent(Intent.ACTION_PICK).also {
            it.type="image/*"
            val mimeType=arrayOf("image/jpeg","image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES,mimeType)
            startActivityForResult(it, AddCategory.REQUEST_CODE_PICK_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK){
            when(requestCode){
                AddCategory.REQUEST_CODE_PICK_IMAGE ->{
                    selectImage =data?.data
                    binding.browseImg.setImageURI(selectImage)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onProgressUpdate(percentage: Int) {
        binding.progressBar.progress=percentage
    }
    companion object {
        const val REQUEST_CODE_PICK_IMAGE = 101
    }
}
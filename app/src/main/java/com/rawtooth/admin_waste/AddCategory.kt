package com.rawtooth.admin_waste

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.easyvolley.Callback
import com.easyvolley.EasyVolleyError
import com.easyvolley.EasyVolleyResponse
import com.easyvolley.NetworkClient
import com.google.gson.Gson
import com.rawtooth.admin_waste.databinding.ActivityAddCategoryBinding
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


class AddCategory : AppCompatActivity(), View.OnClickListener ,UploadRequestBody.UploadCallBack{
    lateinit var binding: ActivityAddCategoryBinding
    private var selectImage: Uri?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddCategoryBinding.inflate(layoutInflater)
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
        val outputStream =FileOutputStream(file)
        inputStream.copyTo(outputStream)
        binding.progressBar.progress=0
        val name=binding.edtNmae.text.toString()
        val description=binding.edtDes.text.toString()
        val categoryImage=UploadRequestBody(file,"image",this)
//       +

            MyApi().uploadImage("Bearer $tokn",MultipartBody.Part.createFormData("categoryImage",file.name,categoryImage),
                RequestBody.create(MediaType.parse("multipart/form-data"), description),
                RequestBody.create(MediaType.parse("multipart/form-data"), name))

                .enqueue(object :retrofit2.Callback<String>{

                    override fun onResponse(
                        call: Call<String>,
                        response: Response<String>
                    ) {
                        binding.progressBar.progress=100
                        Log.d("code",response.body().toString())
                        binding.layoutRoot.snackbar(response.body().toString())
                        startActivity(Intent(this@AddCategory,MainActivity::class.java))
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        t.message?.let { binding.layoutRoot.snackbar(it) }
                        t.message?.let { Log.e("code", it) }
                    }
                })


    }

    private fun onCheck(name:String, description:String, categoryImage: UploadRequestBody) {

        val body=Gson().toJson(CategoryPost(name,description,categoryImage))
        NetworkClient.post("http://192.168.43.251:9090/category/")
            .addHeader("Content-Type", "multipart/form-data")
            .addHeader("Authorization", "Bearer $tokn")
            .addHeader("Content-Length", Integer.toString(body.length))
            .addHeader("Accept", "application/json")
            .setRequestBody(body)
            .setCallback(object :Callback<CategoryPost> {
                override fun onSuccess(t: CategoryPost?, response: EasyVolleyResponse?) {
                    Log.d("code",t.toString())
                   binding.progressBar.progress=100
                    Log.d("code",response.toString())
                    binding.layoutRoot.snackbar(response.toString())
                }

                override fun onError(error: EasyVolleyError?) {
                    Log.e("code","Error"+error!!.mStatusCode.toString() )
                    Log.e("code",error.mMessage)
                    binding.layoutRoot.snackbar(error.mMessage)
                }
                protected fun getParams(): Map<String, String>? {
                    val params: MutableMap<String, String> = HashMap()
                    params["name"] = name
                    params["description"] = description
                    params["categoryImage"] = categoryImage.toString()
                    return params
                }

            }).execute()


    }


    override fun onClick(p0: View?) {
        Intent(Intent.ACTION_PICK).also {
            it.type="image/*"
            val mimeType=arrayOf("image/jpeg","image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES,mimeType)
            startActivityForResult(it, REQUEST_CODE_PICK_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==Activity.RESULT_OK){
            when(requestCode){
                REQUEST_CODE_PICK_IMAGE ->{
                    selectImage =data?.data
                    binding.browseImg.setImageURI(selectImage)
                }
            }
        }
    }

    override fun onProgressUpdate(percentage: Int) {
        binding.progressBar.progress=percentage
    }

    companion object {
        const val REQUEST_CODE_PICK_IMAGE = 101
    }
}



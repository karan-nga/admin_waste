package com.rawtooth.admin_waste

import android.R.attr.password
import android.accounts.AccountManager.KEY_PASSWORD
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
       onCheck(name,description,categoryImage)
    }
    private fun onCheck(name:String, description:String, categoryImage: UploadRequestBody) {

        val body=Gson().toJson(CategoryPost(name,description,categoryImage))
        NetworkClient.post("http://192.168.0.145:9090/category/")
            .addHeader("Content-Type", "multipart/form-data")
            .addHeader("Authorization","Bearer "+ tokn)
            .addHeader("Content-Length", Integer.toString(body.length))
            .addHeader("Accept", "application/json")
            .setRequestBody(body)
            .setCallback(object :Callback<CategoryPost> {
                override fun onSuccess(t: CategoryPost?, response: EasyVolleyResponse?) {
                    Log.d("code",t.toString())
                   binding.progressBar.progress=100
                    binding.layoutRoot.snackbar(response.toString())
                }

                override fun onError(error: EasyVolleyError?) {
                    Log.e("code","Error"+error!!.mStatusCode.toString() )
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



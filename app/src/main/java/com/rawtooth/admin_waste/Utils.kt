package com.rawtooth.admin_waste

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.net.Uri
import android.provider.OpenableColumns
import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.snackbar(message: String){
     Snackbar.make(
         this,
         message,
         Snackbar.LENGTH_LONG
     ).also { snackbar ->
         snackbar.setAction("ok"){
             snackbar.dismiss()
         }
     }.show()
}
@SuppressLint("Range")
fun ContentResolver.getFileName(uri: Uri): String {
    var name=""
    val cursor = query(uri,null,null,null,null)
    cursor?.use{
        it.moveToFirst()
        name = cursor.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
    }
    return name
}
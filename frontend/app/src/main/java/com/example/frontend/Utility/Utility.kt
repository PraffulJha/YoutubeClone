package com.example.frontend.Utility

import android.content.Context
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

object Utility {

    fun uriToFile(context: Context, uri: Uri): File {
        val inputStream = context.contentResolver.openInputStream(uri)
        val file = File(context.cacheDir, "temp_file")
        file.outputStream().use { output ->
            inputStream?.copyTo(output)
        }
        return file
    }

    fun prepareFilePart(partName: String, file: File): MultipartBody.Part {
        val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData(partName, file.name, requestFile)
    }

    fun isRegisterFieldsEmpty(name : String,email :String,password : String,username :String,avatarFile : File) : Boolean{
        if(name.isEmpty()  || email.isEmpty() || password.isEmpty() || username.isEmpty() || avatarFile.exists()){
            return false
        }
        return true
    }

}
package com.example.youtubeclone

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeclone.activities.LoginActivity
import com.example.youtubeclone.apiServices.result.Result
import com.example.youtubeclone.config.Constansts.AVATAR
import com.example.youtubeclone.config.Constansts.EMAIL
import com.example.youtubeclone.config.Constansts.FULL_NAME
import com.example.youtubeclone.config.Constansts.PASSWORD
import com.example.youtubeclone.config.Constansts.USERNAME
import com.example.youtubeclone.viewmodel.DbViewModel

import com.example.youtubeclone.viewmodel.UserDataViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel : UserDataViewModel by lazy {
        ViewModelProvider(this)[UserDataViewModel::class.java]
    }
    private val dbViewModel : DbViewModel by lazy {
        ViewModelProvider(this)[DbViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var refreshToken by remember { mutableStateOf("") }
            if(intent != null){
                val rs = intent.getStringExtra("RefreshToken")
                if(!rs.isNullOrEmpty()){
                    refreshToken = rs
                }
            }
            if(refreshToken.isEmpty()){
               val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }else {
                MainScreen()
            }

        }
    }
    @Preview
    @Composable
    fun MainScreen(){

        Box(modifier = Modifier.background(Color.Red)){
            Text(text = "MAIN SCREEN")
        }

    }


}


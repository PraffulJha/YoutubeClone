package com.example.youtubeclone.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeclone.MainActivity
import com.example.youtubeclone.R
import com.example.youtubeclone.apiServices.result.Result
import com.example.youtubeclone.models.apiModels.ServerLoginUser
import com.example.youtubeclone.viewmodel.UserDataViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    private val userDataViewModel: UserDataViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var refreshTokenRequest by remember { mutableStateOf("") }


            LoginScreen()
        }
    }
    @Preview
    @Composable
    fun LoginScreen() {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        val uiState by  userDataViewModel.uiState.collectAsState()
        var passwordVisible by remember { mutableStateOf(false) }
        when(uiState){
            is Result.Loading -> {
                CircularProgressIndicator()
            }
            is Result.Success -> {
                val user = (uiState as Result.Success).data
                val intent = Intent(this,MainActivity::class.java)
                intent.putExtra("RefreshToken",user.data.refreshToken)
                startActivity(intent)
            }
            else ->{
                Log.e("MESSAGE",(uiState as Result.Failure).exception.message.toString())
                Toast.makeText(this,"Something went wrong",Toast.LENGTH_LONG).show()
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF0F0F0))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                // Replace the placeholder with an actual image resource if needed
                Image(
                    painter = painterResource(id = R.drawable.stream), // Replace with your image resource
                    contentDescription = "Login Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(bottom = 32.dp)
                )

                Text(
                    text = "Welcome Back!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "This here your details about food.",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("E-mail") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp, vertical = 8.dp)
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    singleLine = true,
                    visualTransformation = if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp, vertical = 8.dp),
                    trailingIcon = {
                        val image = if (passwordVisible)
                            Icons.Filled.Visibility
                        else  Icons.Filled.VisibilityOff

                        // Localized description for accessibility services
                        val description = if (passwordVisible) "Hide password" else "Show password"

                        // Toggle button to hide or display password
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = image, description)
                        }
                    }

                )

                Button(
                    onClick = { signInPage(email, password) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFFFFA726))
                ) {
                    Text(text = "Sign in", color = Color.White)
                }

                TextButton(onClick = { /* Navigate to the signup page */ }) {
                    Text(
                        text = "Don't have an account? Sign up",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }

    private fun signInPage(email: String, password: String){
        CoroutineScope(Dispatchers.IO).launch {
            userDataViewModel.loggedInUser(email, password = password)
        }
    }
}
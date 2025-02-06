package com.example.frontend.screens

import android.app.AlertDialog
import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.transformations
import coil3.transform.CircleCropTransformation
import com.example.frontend.R

@Composable
fun UpdateAvatarScreen(navigation: NavController) {
    val context = LocalContext.current
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var bitmapImg by remember { mutableStateOf<Bitmap?>(null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
        bitmapImg = null // Reset bitmap when selecting from gallery
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        bitmapImg = bitmap
        selectedImageUri = null // Reset URI when capturing from camera
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFF7F50)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(Color.Gray, CircleShape),
                contentAlignment = Alignment.BottomEnd
            ) {
                if (bitmapImg != null) {
                    Image(
                        painter = rememberAsyncImagePainter(bitmapImg),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else if (selectedImageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(context)
                                .data(selectedImageUri)
                                .transformations(CircleCropTransformation())
                                .build()
                        ),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.camera), // Replace with your camera icon resource
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .size(50.dp)
                    )
                }

                BasicText(
                    text = "📷",
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            val builder = AlertDialog.Builder(context)
                            builder.setTitle("Select Option")
                            builder.setMessage("Choose an option to set your avatar")
                            builder.setPositiveButton("Open Camera") { _, _ ->
                                cameraLauncher.launch(null)
                            }
                            builder.setNegativeButton("Open Gallery") { _, _ ->
                                imagePickerLauncher.launch("image/*")
                            }
                            builder.setNeutralButton("Cancel", null)
                            builder.show()
                        }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            BasicText(
                text = "Welcome MyName",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            BasicText(
                text = "Upload Your Avatar",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = { /* Handle Continue */ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                modifier = Modifier.fillMaxWidth(0.6f)
            ) {
                BasicText(
                    text = "Continue",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color(0xFFFF4500),
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Button(
                onClick = { navigation.navigateUp() },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF4500)),
                modifier = Modifier.fillMaxWidth(0.6f)
            ) {
                BasicText(
                    text = "Back",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

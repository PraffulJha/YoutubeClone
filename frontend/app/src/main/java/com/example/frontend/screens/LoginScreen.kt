package com.example.frontend.screens


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.frontend.R


@Composable
fun LoginScreen(navigation : NavController) {
    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF1C1C1C))) {
        // Lottie Animation as Background
        LottieBackgroundAnimation()

        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Login", fontSize = 32.sp, color = Color.White)

            Spacer(modifier = Modifier.height(30.dp))

            AnimatedTextField(
                label = "Email",
                keyboardType = KeyboardType.Email,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(15.dp))
            AnimatedTextField(
                label = "Password",
                keyboardType = KeyboardType.Password,
                visualTransformation = VisualTransformation.None, // Add masking here if needed
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { /* Handle Sign In */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFFF0000))
            ) {
                Text("Sign In", color = Color.White, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Forgot Password?",
                color = Color(0xFFFF0000),
                modifier = Modifier.padding(top = 10.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Don't have an account? Sign Up",
                color = Color.White,
                modifier = Modifier.clickable(onClick = {navigation.navigate("register")})
            )
        }
    }
}

@Composable
fun AnimatedTextField(
    label: String,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    var textState by remember { mutableStateOf(TextFieldValue("")) }
    var isFocused by remember { mutableStateOf(false) }

    val labelPosition by animateFloatAsState(targetValue = if (isFocused || textState.text.isNotEmpty()) -10f else 15f)
    val labelSize by animateFloatAsState(targetValue = if (isFocused || textState.text.isNotEmpty()) 12f else 16f)
    val labelColor by animateFloatAsState(targetValue = if (isFocused || textState.text.isNotEmpty()) 1f else 0.69f)

    Box(modifier = modifier) {
        // Label
        Text(
            text = label,
            fontSize = labelSize.sp,
            color = Color(1f, labelColor, labelColor),
            modifier = Modifier
                .absoluteOffset(y = labelPosition.dp)
                .padding(start = 10.dp)
        )

        // Input Field
//        BasicTextField(
//            value = textState,
//            onValueChange = { textState = it },
//            singleLine = true,
//            textStyle = TextStyle(color = Color.White),
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color(0xFF1C1C1C), shape= RectangleShape(16.dp))
//                .padding(vertical = 10.dp, horizontal = 15.dp)
//
//        )

        androidx.compose.material3.OutlinedTextField(
            value = textState,
            onValueChange = { textState = it },
            label = { Text("Label") },
            modifier = Modifier.fillMaxWidth()
                .background(Color(0xFF1C1C1C))
                .padding(vertical = 10.dp, horizontal = 15.dp)
        )
    }
}

@Composable
fun LottieBackgroundAnimation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.bganimations))
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = Modifier.fillMaxSize()
    )
}

package com.example.frontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import com.example.frontend.navigations.NavigationGraph
import com.example.frontend.viewmodel.UserApiViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val userApiViewmodel  : UserApiViewModel by lazy {
        ViewModelProvider(this)[UserApiViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp()
        }
    }
    @Composable
    fun MyApp() {
        MaterialTheme {
            NavigationGraph(userApiViewmodel)
        }
    }
}




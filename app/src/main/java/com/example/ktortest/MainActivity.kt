package com.example.ktortest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ktortest.api.ApiService
import com.example.ktortest.domain.ResponseModel
import com.example.ktortest.ui.theme.KtorTestTheme

class MainActivity : ComponentActivity() {

    // TODO Viewmodel, Flow ?
    private val apiService by lazy {
        ApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KtorTestTheme {
                val products = produceState(
                    initialValue = emptyList<ResponseModel>(),
                    producer = {
                        value = apiService.getProducts()
                    }
                )
                Log.d("p", products.value.toString())

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //Greeting("Android")
                    Text(text = products.value.toString())
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KtorTestTheme {
        Greeting("Android")
    }
}
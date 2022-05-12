package com.example.ktortest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
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

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    productsList(products)
                }
            }
        }
    }
}

//@Preview(showBackground = true)
@Composable
private fun productsList(products: State<List<ResponseModel>>) {
    LazyColumn {
        items(products.value) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val painter = rememberImagePainter(
                        data = it.image,
                        builder = {
                            error(R.drawable.ic_launcher_background)
                        }
                    )

                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        contentScale = ContentScale.Crop,
                        contentDescription = "Coil Image",
                        painter = painter
                    )
                    Text(
                        text = it.title,
                        modifier = Modifier.padding(16.dp)
                    )
                    Text(
                        text = it.description,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

//@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KtorTestTheme {
        Greeting("Android")
    }
}
package com.example.hallserviceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hallserviceapp.ui.theme.HallServiceAppTheme


class DynamicFoodActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HallServiceAppTheme {
                DynamicFoodScreen()
            }
        }
    }
}

@Composable
fun DynamicFoodScreen() {

    val lightBlue = Color(0xFF8FABE7) // Light blue color

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Add the background image
            Image(
                painter = painterResource(id = R.drawable.bgpic4), // Replace with your image resource
                contentDescription = null, // Content description can be null for decorative images
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds // Scale the image to fill the bounds
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    //.background(lightBlue)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                LazyColumn {

                    item { HeaderSectionAd() }
                    item { Spacer(modifier = Modifier.height(30.dp)) }
                    item {
                        ChangeOption(
                            "ADD Dining Food",
                            R.drawable.dyningfood,
                            "AddDiningActivity"
                        )
                    }
                    item { Spacer(modifier = Modifier.height(30.dp)) }
                    item {
                        ChangeOption(
                            "Delete Dining Food",
                            R.drawable.dyningfood,
                            "DeleteDiningActivity"
                        )
                    }
                    item { Spacer(modifier = Modifier.height(30.dp)) }
                    item {
                        ChangeOption(
                            "ADD Canteen Food",
                            R.drawable.foodd,
                            "AddCanteenActivity"
                        )
                    }
                    item { Spacer(modifier = Modifier.height(30.dp)) }
                    item {
                        ChangeOption(
                            "Delete Canteen Food",
                            R.drawable.foodd,
                            "DeleteCanteenActivity"
                        )
                    }
                    item { Spacer(modifier = Modifier.height(30.dp)) }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DynamicFoodScreenPreview() {
    HallServiceAppTheme {
        DynamicFoodScreen()
    }
}


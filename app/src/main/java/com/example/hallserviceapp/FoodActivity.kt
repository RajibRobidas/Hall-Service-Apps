package com.example.hallserviceapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hallserviceapp.ui.theme.HallServiceAppTheme

class FoodActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HallServiceAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FoodScreen()
                }
            }
        }
    }
}

@Composable
fun FoodScreen() {
    val lightBlue = Color(0xFF8FABE7) // Light blue color
    val gray = Color(0xFFA5960D)
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightBlue),
        verticalArrangement = Arrangement.Top,
    ) {
        // "Food" text at the top
        Spacer(modifier = Modifier.height(30.dp)) // For spacing

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Image on the left side
            Image(
                painter = painterResource(id = R.drawable.headline),
                contentDescription = "headline",
                modifier = Modifier
                    .clickable{
                        context.startActivity(Intent(context, UserActivity::class.java))  // Change to the desired activity
                    }
                    .padding(end = 10.dp)
                    .size(width = 100.dp, height = 40.dp)
            )

            Spacer(modifier = Modifier.width(10.dp)) // For spacing

            Text(
                text = "Food",
                color = Color.White,
                fontSize = 30.sp,
                modifier = Modifier
                    .background(gray)
                    .width(150.dp)
                    .padding(12.dp),
                    //.clip(RoundedCornerShape(8.dp)),
                textAlign = TextAlign.Center

            )
        }

        // Remaining content in a Box for central alignment
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Your other UI elements go here
                // For example, a row with an image and text might look like this:
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.dyningfood),
                        contentDescription = "Dining",
                        modifier = Modifier
                            .clickable{
                                context.startActivity(Intent(context, DiningActivity::class.java))  // Change to the desired activity
                            }
                            .size(130.dp)
                            .padding(16.dp)

                    )
                    //Spacer(modifier = Modifier.width(12.dp)) // For spacing
                    Text(
                        text = "Dining",
                        fontSize = 34.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(start = 16.dp)
                            .fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(20.dp)) // For spacing

                // Add more Rows or other components as needed
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.foodd),
                        contentDescription = "Canteen",
                        modifier = Modifier
                            .clickable{
                                context.startActivity(Intent(context, CanteenActivity::class.java))  // Change to the desired activity
                            }
                            .size(130.dp)
                            .padding(16.dp)

                    )
                    Text(
                        text = "Canteen",
                        fontSize = 34.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(start = 16.dp)
                            .fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(20.dp)) // For spacing

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.shop_food),
                        contentDescription = "Shop",
                        modifier = Modifier
                            .clickable{
                                context.startActivity(Intent(context, ShopActivity::class.java))  // Change to the desired activity
                            }
                            .size(130.dp)
                            .padding(16.dp)

                    )
                    //Spacer(modifier = Modifier.width(12.dp)) // For spacing
                    Text(
                        text = "Food Shop",
                        fontSize = 34.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(start = 16.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FoodScreenPreview() {
    FoodScreen()
}

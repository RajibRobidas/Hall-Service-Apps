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
import androidx.compose.foundation.shape.RoundedCornerShape
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
            .background(lightBlue)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        HeaderSectionFood()
        val lightGoldB = Color(0xFFE1E6E5)

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
                Spacer(modifier = Modifier.height(20.dp)) // For spacing
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(vertical = 8.dp)
                        .background(lightGoldB, shape = RoundedCornerShape(10.dp))
                        .clickable{
                            context.startActivity(Intent(context, DiningActivity::class.java))  // Change to the desired activity
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.dyningfood),
                        contentDescription = "Dining",
                        modifier = Modifier

                            .size(130.dp)
                            .padding(16.dp)

                    )
                    //Spacer(modifier = Modifier.width(12.dp)) // For spacing
                    Text(
                        text = "Dining",
                        fontSize = 34.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier//.padding(start = 16.dp)
                            .fillMaxWidth()
                    )
                }

                //Spacer(modifier = Modifier.height(20.dp)) // For spacing

                // Add more Rows or other components as needed
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp)
                        .background(lightGoldB, shape = RoundedCornerShape(10.dp))
                        .clickable{
                            context.startActivity(Intent(context, CanteenActivity::class.java))  // Change to the desired activity
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.foodd),
                        contentDescription = "Canteen",
                        modifier = Modifier

                            .size(130.dp)
                            .padding(16.dp)

                    )
                    Text(
                        text = "Canteen",
                        fontSize = 34.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier//.padding(start = 16.dp)
                            .fillMaxWidth()
                    )
                }

                //Spacer(modifier = Modifier.height(20.dp)) // For spacing

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp)
                        .background(lightGoldB, shape = RoundedCornerShape(10.dp))
                        .clickable{
                            context.startActivity(Intent(context, ShopActivity::class.java))  // Change to the desired activity
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.shop_food),
                        contentDescription = "Shop",
                        modifier = Modifier

                            .size(130.dp)
                            .padding(16.dp)

                    )
                    //Spacer(modifier = Modifier.width(12.dp)) // For spacing
                    Text(
                        text = "Food Shop",
                        fontSize = 34.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier//.padding(start = 16.dp)
                            .fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.height(40.dp)) // For spacing

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(lightGoldB, shape = RoundedCornerShape(10.dp))
                        .padding(12.dp)


                ) {
                    Text(
                        text = "Enter Dining Food",
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier//.padding(start = 16.dp)
                            .clickable{
                                context.startActivity(Intent(context, ReadDiningActivity::class.java))  // Change to the desired activity
                            }
                    )
                    //Spacer(modifier = Modifier.width(12.dp)) // For spacing
                    Text(
                        text = "Enter Canteen Food",
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier//.padding(start = 16.dp)
                            .clickable{
                                context.startActivity(Intent(context, ReadCanteenActivity::class.java))  // Change to the desired activity
                            }
                    )
                }
            }
        }
    }
}
@Composable
fun HeaderSectionFood() {

    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.headline),
            contentDescription = "Headline",
            modifier = Modifier
                .clickable{
                    context.startActivity(Intent(context, UserActivity::class.java))  // Change to the desired activity
                }
                .size(58.dp)
                .padding(end = 25.dp)
        )

        Spacer(modifier = Modifier.width(65.dp)) // For spacing

        Text(
            text = "Food",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .background(Color.White, shape = MaterialTheme.shapes.medium)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun FoodScreenPreview() {
    FoodScreen()
}

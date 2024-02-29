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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hallserviceapp.ui.theme.HallServiceAppTheme

class SportsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HallServiceAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SportsScreen()
                }
            }
        }
    }
}
@Composable
fun SportsScreen() {
    val lightBlue = Color(0xFF8FABE7) // Light blue color
    val gray = Color(0xFFFFFFFF)
    val green = Color(0xFF43B83A)
    val yellow = Color(0xFFF1EFE9)

    val context = LocalContext.current


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
            verticalArrangement = Arrangement.Top,
        ) {
            HeaderSectionSports()

            Spacer(modifier = Modifier.height(40.dp))

            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Available item list:",
                    fontSize = 30.sp,
                    color = Color.White,
                    modifier = Modifier
                        //.background(lightBlue, shape = RoundedCornerShape(10.dp))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    textAlign = TextAlign.Center
                )
            }
            // Available Item List Label

            Spacer(modifier = Modifier.height(30.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(gray, shape = RoundedCornerShape(10.dp))
                //.padding(16.dp)
            ) {
                Text(
                    text = "Room no : 101",
                    color = Color.Blue,
                    fontSize = 25.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .background(gray)
                )
                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    modifier = Modifier
                        .background(gray, shape = RoundedCornerShape(10.dp))
                        .padding(16.dp)
                ) {
                    Text(text = "1. Football", fontSize = 24.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "2. Cricket ball and stamp", fontSize = 24.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "3. Dart", fontSize = 24.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "4. Handball", fontSize = 24.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "5. Racquetbal", fontSize = 24.sp)
                    // Add more items as needed
                }
            }
        }
    }
}
@Composable
fun HeaderSectionSports() {

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
                .padding(start = 20.dp,end = 1.dp)
        )

        Spacer(modifier = Modifier.width(60.dp)) // For spacing

        Text(
            text = "Sports",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .background(Color.White, shape = MaterialTheme.shapes.medium)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun SportsScreenPreview() {
    HallServiceAppTheme {
        SportsScreen()
    }
}

package com.example.hallserviceapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hallserviceapp.ui.theme.HallServiceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HallServiceAppTheme {
                MainScreen()
            }
        }
        // Write a message to the database

    }
}

@Composable
fun MainScreen() {
    Scaffold(

        topBar = { AppBar() },
        content = { padding ->
            MainContent(padding)
        }
        // Remove the floatingActionButton if not needed
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {
    val lightblueTo = Color(0xFFD8D029) // Light blue color

    TopAppBar(
        title = { Text(text = "Hall App") },
        modifier = Modifier
            .background(lightblueTo) // Set the Topbackground color here

    )
}

@Composable
fun MainContent(padding: PaddingValues) {
    // Define a light blue color
    val lightBlue = Color(0xFF8FABE7) // Light blue color

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightBlue) // Set the background color here
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularImageView(imageRes = R.drawable.sust_logo, size = 120.dp)
        //Spacer(modifier = Modifier.height(30.dp))
        CircularImageView(imageRes = R.drawable.hall_pic, size = 290.dp, height = 180.dp)
        HallTitle()
        EnterButton()
    }
}


@Composable
fun CircularImageView(imageRes: Int, size: Dp, height: Dp = size) {
    Card(
        shape = CircleShape,
        modifier = Modifier
            .size(width = size, height = height)
            .padding(2.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Image",
            modifier = Modifier.fillMaxSize()
        )
    }
    Spacer(modifier = Modifier.height(30.dp))

}

@Composable
fun HallTitle() {
    Text(
        text = "SUST Hall 3",
        color = Color.White,
        fontSize = 30.sp,  // Adjusted for better fit
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
            .background(color = Color(51, 165, 125, 255),shape = MaterialTheme.shapes.medium),
        textAlign = TextAlign.Center
    )
}

@Composable
fun EnterButton() {
    val context = LocalContext.current
    Button(
        onClick = {
            context.startActivity(Intent(context, LoginActivity::class.java))  // Change to the desired activity
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(vertical = 16.dp)
    ) {
        Text(text = "Enter")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HallServiceAppTheme {
        MainScreen()
    }
}

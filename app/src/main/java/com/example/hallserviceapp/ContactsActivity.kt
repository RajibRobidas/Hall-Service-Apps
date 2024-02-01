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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hallserviceapp.ui.theme.HallServiceAppTheme

class ContactsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HallServiceAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ContactsScreen()
                }
            }
        }
    }
}
@Composable
fun ContactsScreen() {
    val lightBlue = Color(0xFF8FABE7) // Light blue color
    val gray = Color(0xFFBA6FBD)
    val green = Color(0xFF43B83A)
    val yellow = Color(0xFFC5B685)

    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightBlue, shape = RoundedCornerShape(10.dp)),
        verticalArrangement = Arrangement.Top,
    ) {
        // "Food" text at the top
        //Spacer(modifier = Modifier.height(20.dp)) // For spacing

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

            Spacer(modifier = Modifier.width(20.dp)) // For spacing

            Text(
                text = "Contacts",
                color = Color.White,
                fontSize = 30.sp,
                modifier = Modifier
                    .background(green)
                    .padding(12.dp)
                    .clip(RoundedCornerShape(8.dp)),
                textAlign = TextAlign.Center
            )
        }

        Box(
            //contentAlignment = Alignment.TopCenter,
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "List:",
                fontSize = 30.sp,
                color = Color.White,
                modifier = Modifier
                    .background(gray, shape = RoundedCornerShape(10.dp))
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp)),
                textAlign = TextAlign.Center
            )
        }
        // Available Item List Label
        val contactItems = listOf(
            ContactItem("Varsity Ambulance Service", "01764-64646"),
            ContactItem("Varsity Emergency Helpline", "01739-36852"),
            // ... add more items here
            ContactItem("Ambulance Helpline, Sylhet", "01789-782332"),
            ContactItem("Fire Service Station, Sylhet", "01730-336644"),

        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(lightBlue)
        ) {
            items(contactItems) { contactItem ->
                ContactItemView(contactItem)
            }
        }
    }

}

data class ContactItem(val name: String, val number: String){

}

@Composable
fun ContactItemView(contactItem: ContactItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .padding(8.dp),
    horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = contactItem.name,
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier
                    .width(150.dp)
        )

        val context = LocalContext.current
        Text(
            text = contactItem.number,
            fontSize = 24.sp,
            color = Color(0xFF1986B1), // Same blue color as in your XML
            modifier = Modifier
                .clickable{
                    context.startActivity(Intent(context, UserActivity::class.java))  // Change to the desired activity
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContactsScreenPreview() {
    HallServiceAppTheme {
        ContactsScreen()
    }
}

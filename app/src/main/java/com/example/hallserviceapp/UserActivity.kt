package com.example.hallserviceapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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


class UserActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HallServiceAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserScreen()
                }
            }
        }
    }
}


@Composable
fun UserScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        //color = MaterialTheme.colorScheme.background
    ) {
        val lightBlue = Color(0xFF8FABE7) // Light blue color

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .background(lightBlue) // Set the background color here
                .padding(30.dp)

        ) {
            Head()
            Spacer(modifier = Modifier.height(20.dp))
            Row1()
            //Spacer(modifier = Modifier.height(16.dp))
            Row2()
            //Spacer(modifier = Modifier.height(16.dp))
            Row3()
            //Spacer(modifier = Modifier.height(16.dp))
            Row4()
            //Spacer(modifier = Modifier.height(16.dp))
            Row5()
        }
    }
}

@Composable
fun Head() {
    val context = LocalContext.current

    Row(
        modifier = Modifier.fillMaxWidth(),
        //horizontalArrangement = Arrangement.Center // Center the items
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = "arrow",
            modifier = Modifier
                .clickable {
                    context.startActivity(Intent(context, LoginActivity::class.java))  // Change to the desired activity
                }
                .width(40.dp)
                .height(40.dp)
        )
        //Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "Mujtaba Ali Hall",
            fontSize = 24.sp,
            modifier = Modifier.padding(8.dp)
                .fillMaxWidth(),
            //.width(200.dp),
            textAlign = TextAlign.Center
        )
    }
}
@Composable
fun Row1() {
    val context = LocalContext.current

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center // Center the items
    ) {
        // Authority Column
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .width(130.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.government),
                contentDescription = "Authority",
                modifier = Modifier
                    .clickable {
                        context.startActivity(Intent(context, AuthorityActivity::class.java))  // Change to the desired activity
                    }
                    .width(80.dp)
                    .height(70.dp)
            )
            Text(
                text = "Authority",
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.width(8.dp)) // Reduce the width of the Spacer

        // Notice Column
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .width(130.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.notice),
                contentDescription = "Notice",
                modifier = Modifier
                    .clickable {
                        context.startActivity(Intent(context, NoticeActivity::class.java))  // Change to the desired activity
                    }
                    .width(80.dp)
                    .height(70.dp)
            )
            Text(
                text = "Notice",
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun Row2() {
    val context = LocalContext.current

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center // Center the items
    ) {
        // Office Column
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .width(130.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.officess),
                contentDescription = "Office",
                modifier = Modifier
                    .clickable {
                        context.startActivity(Intent(context, OfficeActivity::class.java))  // Change to the desired activity
                    }
                    .width(80.dp)
                    .height(70.dp)
            )
            Text(
                text = "Office",
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))

        // Students Column
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .width(130.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.studentss),
                contentDescription = "Students",
                modifier = Modifier
                    .clickable {
                        context.startActivity(Intent(context, StudentsInformationActivity::class.java))  // Change to the desired activity
                    }
                    .width(80.dp)
                    .height(70.dp)
            )
            Text(
                text = "Students",
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun Row3() {
    val context = LocalContext.current

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center // Center the items
    ) {
        // Food Column
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .width(130.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.foodd),
                contentDescription = "Food",
                modifier = Modifier
                    .clickable {
                        context.startActivity(Intent(context, FoodActivity::class.java))  // Change to the desired activity
                    }
                    .width(80.dp)
                    .height(70.dp)
            )
            Text(
                text = "Food",
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))

        // Medicine Column
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .width(130.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.medicinss),
                contentDescription = "Medicine",
                modifier = Modifier
                    .clickable {
                        context.startActivity(Intent(context, MedicineActivity::class.java))  // Change to the desired activity
                    }
                    .width(80.dp)
                    .height(70.dp)
            )
            Text(
                text = "Medicine",
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun Row4() {
    val context = LocalContext.current

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center // Center the items
    ) {
        // Services Column
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .width(130.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.servicess),
                contentDescription = "Services",
                modifier = Modifier
                    .clickable {
                        context.startActivity(Intent(context, ServicesActivity::class.java))  // Change to the desired activity
                    }
                    .width(80.dp)
                    .height(70.dp)
            )
            Text(
                text = "Services",
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))

        // Complaints Column
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .width(130.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.complaintss),
                contentDescription = "Complaints",
                modifier = Modifier
                    .clickable {
                        context.startActivity(Intent(context, ComplaintsActivity::class.java))  // Change to the desired activity
                    }
                    .width(80.dp)
                    .height(70.dp)
            )
            Text(
                text = "Complaints",
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun Row5() {
    val context = LocalContext.current

    Row(
        modifier = Modifier.fillMaxWidth(),
        //horizontalArrangement = Arrangement.SpaceBetween
        horizontalArrangement = Arrangement.Center // Center the items
    ) {
        // Sports Column
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .width(130.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.sportss),
                contentDescription = "Sports",
                modifier = Modifier
                    .clickable {
                        context.startActivity(Intent(context, SportsActivity::class.java))  // Change to the desired activity
                    }
                    .width(80.dp)
                    .height(70.dp)
            )
            Text(
                text = "Sports",
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))

        // Contacts Column
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .width(130.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.contactss),
                contentDescription = "Contacts",
                modifier = Modifier
                    .clickable {
                        context.startActivity(Intent(context, ContactsActivity::class.java))  // Change to the desired activity
                    }
                    .width(80.dp)
                    .height(70.dp)
            )
            Text(
                text = "Contacts",
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserScreenPreview() {
    HallServiceAppTheme {
        UserScreen()
    }
}

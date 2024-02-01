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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hallserviceapp.ui.theme.HallServiceAppTheme


class AdminActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HallServiceAppTheme {
                AdminScreen()
            }
        }
    }
}

@Composable
fun AdminScreen() {

    val lightBlue = Color(0xFF8FABE7) // Light blue color

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(lightBlue)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            LazyColumn {
                item { HeaderSectionAd() }
                item { Spacer(modifier = Modifier.height(30.dp)) }
                item { ChangeOption("Read Complaints", R.drawable.complainttt,"ReadComplaintsActivity") }
                item { Spacer(modifier = Modifier.height(30.dp)) }
                item { ChangeOption("Upload Notice", R.drawable.notice,"UploadNoticeActivity") }
                item { Spacer(modifier = Modifier.height(30.dp)) }
                item { ChangeOption("Delete Notice", R.drawable.delete_notice,"DeleteNoticeActivity") }
                item { Spacer(modifier = Modifier.height(30.dp)) }
                // Add more options here using item { }
                item { ChangeOption("Add User", R.drawable.placeholder_a,"AddUserActivity") }
                item { Spacer(modifier = Modifier.height(30.dp)) }
                item { ChangeOption("Delete User", R.drawable.placeholder,"DeleteUser") }
                item { Spacer(modifier = Modifier.height(30.dp)) }
                item { ChangeOption("Delete Information", R.drawable.icon_account_circle,"DeleteInformation") }
                item { Spacer(modifier = Modifier.height(30.dp)) }
                item { ChangeOption("Add Student", R.drawable.student_p,"StudentActivity") }
                item { Spacer(modifier = Modifier.height(30.dp)) }
                item { ChangeOption("Add Office", R.drawable.office_worker,"UpdateOfficeActivity") }
                item { Spacer(modifier = Modifier.height(30.dp)) }
                item { ChangeOption("Add Authority", R.drawable.authoriy_p,"UpdateAuthorityActivity") }
                item { Spacer(modifier = Modifier.height(30.dp)) }
                item { ChangeOption("Reset Password", R.drawable.resetpassword,"SignUpActivity") }

            }
        }
    }
}

@Composable
fun HeaderSectionAd() {

    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = "Headline",
            modifier = Modifier
                .clickable {
                    context.startActivity(
                        Intent(
                            context,
                            FrontAdminActivity::class.java
                        )
                    )  // Change to the desired activity
                }
                .size(58.dp)
                .padding(10.dp)
                //.padding(end = 25.dp)
        )

        Spacer(modifier = Modifier.width(35.dp)) // For spacing

        Text(
            text = "View as User",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .background(Color.White, shape = MaterialTheme.shapes.medium)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable {
                    context.startActivity(
                        Intent(
                            context,
                            UserActivity::class.java
                        )
                    )  // Change to the desired activity
                }
        )
    }
}

@Composable
fun ChangeOption(text: String, iconResId: Int, actName : String) {

    val context = LocalContext.current

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(16.dp)
                .clickable {
                    val intent = when (actName) {

                        "ReadComplaintsActivity" -> Intent(context, ReadComplaintsActivity::class.java)
                        "UploadNoticeActivity" -> Intent(context, UploadNoticeActivity::class.java)
                        "DeleteNoticeActivity" -> Intent(context, DeleteNoticeActivity::class.java)
                        "StudentActivity" -> Intent(context, StudentActivity::class.java)
                        "AddUserActivity" -> Intent(context, AddUserActivity::class.java)
                        "DeleteInformation" -> Intent(context, UpdateStudentActivity::class.java)
                        "UpdateOfficeActivity" -> Intent(context, UpdateOfficeActivity::class.java)
                        "UpdateAuthorityActivity" -> Intent(context, UpdateAuthorityActivity::class.java)
                        "SignUpActivity" -> Intent(context, SignUpActivity::class.java)
                        "DeleteUser" -> Intent(context, RemoveUserActivity::class.java)

                        // Add other cases here
                        else -> return@clickable
                    }
                    context.startActivity(intent)
                }
        ) {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = text,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdminScreenPreview() {
    HallServiceAppTheme {
        AdminScreen()
    }
}


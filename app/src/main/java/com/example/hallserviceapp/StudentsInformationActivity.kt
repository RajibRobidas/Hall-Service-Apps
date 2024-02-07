package com.example.hallserviceapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.hallserviceapp.ui.theme.HallServiceAppTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class StudentsInformationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HallServiceAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StudentsInformationScreen()
                }
            }
        }
    }
}

@Composable
fun StudentsInformationScreen() {
    val lightBlue = Color(0xFF8FABE7) // Light blue color

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightBlue)
            .padding(16.dp)
    ) {
        HeaderSectionStudent()
        // SearchSection()
        StudentsInformationSection()
    }
}
@Composable
fun HeaderSectionStudent() {
    val yellow = Color(0xFF40E48A)
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        verticalAlignment = Alignment.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.headline),
            contentDescription = "hradline",
            modifier = Modifier
                .clickable {
                    context.startActivity(
                        Intent(
                            context,
                            UserActivity::class.java
                        )
                    )
                }
                .padding(10.dp)
                .size(width = 50.dp, height = 25.dp)
        )

        Text(
            text = "Student Information",
            color = Color.Black,
            fontSize = 20.sp,
            modifier = Modifier
                .background(yellow, shape = RoundedCornerShape(10.dp))
                .padding(10.dp)
                .clip(RoundedCornerShape(8.dp)),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun StudentsInformationSection() {
    val database = FirebaseDatabase.getInstance().getReference("students")
    var studentList by remember { mutableStateOf(listOf<Student>()) }
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                isLoading = false
                studentList = snapshot.children.mapNotNull { it.getValue(Student::class.java) }
            }

            override fun onCancelled(error: DatabaseError) {
                isLoading = false
                isError = true
            }
        })
    }

    if (isLoading) {
        CircularProgressIndicator()
    } else if (isError) {
        Text("Error loading student information.")
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(studentList) { student ->
                StudentItem(student)
            }
        }
    }
}

@Composable
fun StudentItem(student: Student) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //ShowImages(authority.imageUrl)

            val painter: Painter = rememberImagePainter(student.imageUrl)

            Image(
                painter = painter,
                contentDescription = "Student Image",
                modifier = Modifier
                    .size(80.dp)
                    .padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.padding(16.dp)) {

                Text(text = "Name:  ${student.name}",  style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = "Registration Number:  ${student.registrationNumber}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Department:  ${student.department}", style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Hometown:  ${student.hometown}", style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Phone Number:  ${student.phoneNumber}", style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(4.dp))
                // Add more details or actions for each student item here

            }
        }
    }
}

data class Student(
    val id : String = "",
    val department: String = "",
    val hometown: String = "",
    val imageUrl: String = "",
    val name: String = "",
    val phoneNumber: String = "",
    val registrationNumber: String = ""
)

@Preview(showBackground = true)
@Composable
fun StudentsInformationScreenPreview() {
    HallServiceAppTheme {
        StudentsInformationScreen()
    }
}

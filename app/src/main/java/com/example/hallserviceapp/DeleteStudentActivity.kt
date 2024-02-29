package com.example.hallserviceapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.layout.ContentScale
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

class DeleteStudentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HallServiceAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DeleteStudentScreen()
                }
            }
        }
    }
}

@Composable
fun HeaderSectionStudentD() {
    val yellow = Color(0xFF40E48A)
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        verticalAlignment = Alignment.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = "arrow",
            modifier = Modifier
                .clickable {
                    context.startActivity(
                        Intent(
                            context,
                            UpdateStudentActivity::class.java
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
fun DeleteStudentScreen() {
    val lightBlue = Color(0xFF8FABE7) // Light blue color
    var registrationNumber by remember { mutableStateOf("") }

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
                .padding(16.dp)
        ) {
            HeaderSectionStudentD()
            //SearchSection()
            DeleteStudentSections(registrationNumber = registrationNumber) // Pass the registration number to filter the list
        }
    }
}

@Composable
fun SearchSection() {
    var text by remember { mutableStateOf("") }
    var isButtonClicked by remember { mutableStateOf(false) }
    var registrationNumber by remember { mutableStateOf("") }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = registrationNumber,
            onValueChange = { registrationNumber = it },
            label = { Text("Enter Registration Number") },
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = { isButtonClicked = true },
            modifier = Modifier.height(56.dp)
        ) {
            Text(text = "Search")
        }
    }
    Spacer(modifier = Modifier.height(8.dp))

    if (isButtonClicked) {
        DeleteStudentSections(registrationNumber = registrationNumber) // Pass the registration number to filter the list
        isButtonClicked =false
    }
}

@Composable
fun DeleteStudentSections(registrationNumber: String) {
    val database = FirebaseDatabase.getInstance().getReference("students")
    var studentList by remember { mutableStateOf(listOf<Student>()) }
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                isLoading = false
                studentList = snapshot.children.mapNotNull { it.getValue(Student::class.java)?.copy(id = it.key ?: "") }
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
                if (student.registrationNumber == registrationNumber || registrationNumber.isEmpty()) {
                    StudentItemWithDelete(student){ studentId->
                        database.child(studentId).removeValue()
                    }
                }
            }
        }
    }
}

@Composable
fun StudentItemWithDelete(student: Student, onDelete: (String) -> Unit) {
    val context = LocalContext.current
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
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
                Text(text = "Name: ${student.name}", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = "Registration Number: ${student.registrationNumber}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Department: ${student.department}", style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Hometown: ${student.hometown}", style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Phone Number: ${student.phoneNumber}", style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(4.dp))

                val database = FirebaseDatabase.getInstance().getReference("students")
/*
                Button(
                    onClick = {
                        // Delete student from Firebase
                        database.child(student.id).removeValue()
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(context, "Student deleted", Toast.LENGTH_SHORT).show()
                                } else {
                                    Toast.makeText(context, "Failed to delete student", Toast.LENGTH_SHORT).show()
                                }
                            }
                    },
                    modifier = Modifier
                        .padding(vertical = 3.dp)
                ) {
                    Text(text = "Delete")
                }
*/
                Button(onClick = { onDelete(student.id) }) {
                    Text("Delete")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeleteStudentPreview() {
    HallServiceAppTheme {
        DeleteStudentScreen()
    }
}

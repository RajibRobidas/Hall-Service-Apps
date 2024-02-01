package com.example.hallserviceapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hallserviceapp.ui.theme.HallServiceAppTheme
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.UUID

class StudentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HallServiceAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    StudentsScreen()
                }
            }
        }
    }
}

@Composable
fun StudentsScreen() {
    var studentName by remember { mutableStateOf("") }
    var registrationNumber by remember { mutableStateOf("") }
    var department by remember { mutableStateOf("") }
    var hometown by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val context = LocalContext.current
    val storageReference = FirebaseStorage.getInstance().reference
    val databaseReference = Firebase.database.reference
    val lightBlue = Color(0xFF8FABE7)
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightBlue)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HeaderSectionStudents()

        Spacer(modifier = Modifier.size(16.dp))

        LazyColumn {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LoadImage { uri ->
                        imageUri = uri
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    ShowImage(imageUri)
                }

                OutlinedTextField(
                    value = studentName,
                    onValueChange = { studentName = it },
                    label = { Text("Student Name") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                )
                OutlinedTextField(
                    value = registrationNumber,
                    onValueChange = { registrationNumber = it },
                    label = { Text("Registration Number") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                )
                OutlinedTextField(
                    value = department,
                    onValueChange = { department = it },
                    label = { Text("Department") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                )
                OutlinedTextField(
                    value = hometown,
                    onValueChange = { hometown = it },
                    label = { Text("Hometown") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                )
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = { Text("Phone Number") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                )

                Spacer(modifier = Modifier.size(16.dp))

                if (showDialog) {
                    ProgressDialog()
                }

                Button(
                    onClick = {
                        if (studentName.isNotEmpty() && registrationNumber.isNotEmpty() &&
                            department.isNotEmpty() && hometown.isNotEmpty() && phoneNumber.isNotEmpty() && imageUri != null) {
                            showDialog = true
                            uploadStudentToFirebase(
                                context,
                                studentName,
                                registrationNumber,
                                department,
                                hometown,
                                phoneNumber,
                                imageUri,
                                storageReference,
                                databaseReference
                            )
                            showDialog = false

                        } else {
                            Toast.makeText(
                                context,
                                "Please fill all fields and select an image",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxWidth()
                ) {
                    Text("Add Student Information")
                }


            }
        }
    }
}

@Composable
fun HeaderSectionStudents() {
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
                            MainActivity::class.java
                        )
                    )
                }
                .padding(end = 10.dp)
                .size(width = 90.dp, height = 30.dp)
        )

        Text(
            text = "Add Student",
            color = Color.Black,
            fontSize = 20.sp,
            modifier = Modifier
                .background(yellow, shape = RoundedCornerShape(10.dp))
                .padding(10.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    }
}

fun uploadStudentToFirebase(
    context: Context,
    studentName: String,
    registrationNumber: String,
    department: String,
    hometown: String,
    phoneNumber: String,
    imageUri: Uri?,
    storageReference: StorageReference,
    databaseReference: DatabaseReference
) {
    imageUri?.let { uri ->
        val imageRef = storageReference.child("images/${UUID.randomUUID()}")
        imageRef.putFile(uri)
            .addOnSuccessListener { taskSnapshot ->
                taskSnapshot.storage.downloadUrl.addOnSuccessListener { uri ->
                    val imageUrl = uri.toString()

                    val studentId = UUID.randomUUID().toString()

                    val student = Students(
                        name = studentName,
                        registrationNumber = registrationNumber,
                        department = department,
                        hometown = hometown,
                        phoneNumber = phoneNumber,
                        imageUrl = imageUrl
                    )

                    databaseReference.child("students").child(studentId).setValue(student)
                        .addOnSuccessListener {
                            Toast.makeText(
                                context,
                                "Student information uploaded successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(
                                context,
                                "Failed to upload student information",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }
            }
            .addOnFailureListener {
                Toast.makeText(
                    context,
                    "Failed to upload image",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}
data class Students(
    val name: String,
    val registrationNumber: String,
    val department: String,
    val hometown: String,
    val phoneNumber: String,
    val imageUrl: String
)

@Preview(showBackground = true)
@Composable
fun StudentsScreenPreview() {
    HallServiceAppTheme {
        StudentsScreen()
    }
}

/*
package com.example.hallserviceapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.hallserviceapp.ui.theme.HallServiceAppTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow

class StudentsInformationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HallServiceAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
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
    val viewModel = remember { StudentViewModel() }
    val students by viewModel.studentsFlow.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightBlue)
            .padding(16.dp)
    ) {
        HeaderSection("Students")
        SearchSection()
        StudentList(students)
    }
}

class StudentViewModel : ViewModel() {
    private val database: DatabaseReference by lazy {
        FirebaseDatabase.getInstance().reference.child("students")
    }

    val studentsFlow = MutableStateFlow<List<Students>>(emptyList())

    init {
        fetchStudents()
    }

    private fun fetchStudents() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val students = mutableListOf<Students>()
                for (studentSnapshot in snapshot.children) {
                    val student = studentSnapshot.getValue(Students::class.java)
                    student?.let {
                        students.add(it)
                    }
                }
                studentsFlow.value = students
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
                Log.e("FirebaseDatabase", "Error fetching students: ${error.message}")

            }
        })
    }
}

@Composable
fun SearchSection() {
    var searchText by remember { mutableStateOf(TextFieldValue()) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Search Student",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .weight(1f)
                .padding(top = 15.dp)
        )

        BasicTextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier
                .weight(1f)
                .padding(top = 15.dp)
                .background(Color.White, shape = MaterialTheme.shapes.small)
                .padding(8.dp)
                .border(1.dp, MaterialTheme.colorScheme.onBackground, shape = MaterialTheme.shapes.small)
                .padding(end = 16.dp)
        )
    }
}

@Composable
fun StudentList(students: List<Students>) {
    LazyColumn {
        items(students) { student ->
            StudentItem(student)
        }
    }
}

@Composable
fun StudentItem(student: Students) {
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp)
    ) {
        Text(text = "Name: ${student.name}")
        Text(text = "Registration Number: ${student.registrationNumber}")
        Text(text = "Department: ${student.department}")
        Text(text = "Hometown: ${student.hometown}")
        Text(text = "Phone Number: ${student.phoneNumber}")
    }
}

data class Student(
    val id: String, // Unique identifier for the student
    val name: String,
    val registrationNumber: String,
    val department: String,
    val hometown: String,
    val phoneNumber: String
)

@Preview(showBackground = true)
@Composable
fun StudentsInformationScreenPreview() {
    HallServiceAppTheme {
        StudentsInformationScreen()
    }
}

 */
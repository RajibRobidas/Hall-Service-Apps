package com.example.hallserviceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hallserviceapp.ui.theme.HallServiceAppTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ReadComplaintsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HallServiceAppTheme {
                ReadComplaintsScreen()
            }
        }
    }
}

@Composable
fun ReadComplaintsScreen() {
    val database = FirebaseDatabase.getInstance().getReference("complaints")
    var complaintsList by remember { mutableStateOf(listOf<ComplaintSs>()) }
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        database.orderByChild("date").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                isLoading = false
                complaintsList = snapshot.children.mapNotNull { it.getValue(ComplaintSs::class.java)?.copy(id = it.key ?: "") }
            }

            override fun onCancelled(error: DatabaseError) {
                isLoading = false
                isError = true
            }
        })
    }
    val lightBlue = Color(0xFF8FABE7) // Light blue color
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightBlue, shape = RoundedCornerShape(10.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        //HeaderSection()
        if (isLoading) {
            CircularProgressIndicator()
        } else if (isError) {
            Text("Error loading complaints.")
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                items(complaintsList) { complaint ->
                    ComplaintItem(complaint) { complaintId ->
                        database.child(complaintId).removeValue()
                    }
                }
            }
        }
    }

}

@Composable
fun ComplaintItem(complaint: ComplaintSs, onDelete: (String) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(text = "Date: ${complaint.date}", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "User ID: ${complaint.userId}", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Title: ${complaint.title}", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Description: ${complaint.text}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Button(onClick = { onDelete(complaint.id) }) {
                    Text("Delete")
                }
            }
        }
    }
}

data class ComplaintSs(
    val id: String = "",
    val userId: String = "",
    val title: String = "",
    val text: String = "",
    val date: String = ""
)

@Preview(showBackground = true)
@Composable
fun ReadComplaintsPreview() {
    HallServiceAppTheme {
        ReadComplaintsScreen()
    }
}
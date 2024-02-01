package com.example.hallserviceapp

import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hallserviceapp.ui.theme.HallServiceAppTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class DeleteNoticeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HallServiceAppTheme {
                DeleteNoticeContent()
            }
        }
    }
}

@Composable
fun DeleteNoticeContent() {
    var noticeList by remember { mutableStateOf<List<NoticeDN>>(emptyList()) }
    val database = FirebaseDatabase.getInstance().getReference("notices")
    val lightBlue = Color(0xFF8FABE7) // Light blue color

    LaunchedEffect(key1 = Unit) {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val notices = snapshot.children.mapNotNull { it.getValue(NoticeDN::class.java) }
                noticeList = notices
            }
            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        }
        database.addValueEventListener(listener)

        // Cleanup lambda to remove the listener

    }
    val notices = noticeList
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightBlue, shape = RoundedCornerShape(10.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(5.dp))
        HeaderSectionDe()
        Spacer(modifier = Modifier.height(8.dp))

        if (notices.isNotEmpty()) {
            LazyColumn {
                items(notices) { notice ->
                    DeleteNoticeItem(notice){ noticestId ->
                        database.child(noticestId).removeValue()
                    }
                }
            }
        } else {
            Text(text = "No notices found", style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
fun HeaderSectionDe() {

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

        Spacer(modifier = Modifier.width(50.dp)) // For spacing

        Text(
            text = "Delete Notice",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .background(Color.White, shape = MaterialTheme.shapes.medium)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun DeleteNoticeItem(notice: NoticeDN, onDelete: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Date: ${notice.date}", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Title: ${notice.title}", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Text: ${notice.text}", style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "File: ${notice.pdfUri}", style = MaterialTheme.typography.bodyMedium)
            if (notice.pdfUri.isNotEmpty()) {
                val context = LocalContext.current
                Button(onClick = {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(notice.pdfUri)
                    context.startActivity(intent)
                }) {
                    Text(text = "View PDF")
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Button(onClick = {
                onDelete(notice.id) }
            ) {
                Text(text = "Delete")
            }
        }
    }
}

data class NoticeDN(
    val id: String = "",
    val userid: String = "",
    val title: String = "",
    val date: String = "",
    val text: String = "",
    val pdfUri: String = ""
)

@Preview(showBackground = true)
@Composable
fun DeleteNoticePreview() {
    HallServiceAppTheme {
        DeleteNoticeContent()
    }
}

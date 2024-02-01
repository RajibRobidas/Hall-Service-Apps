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

class AuthorityActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HallServiceAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AuthorityScreen()
                }
            }
        }
    }
}

@Composable
fun AuthorityScreen() {
    val lightBlue = Color(0xFF8FABE7) // Light blue color

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightBlue)
            .padding(16.dp)
    ) {
        HeaderSectionAuthoritys()
        // SearchSection()
        AuthorityInformationSection()
    }
}

@Composable
fun HeaderSectionAuthoritys() {
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
                            AdminActivity::class.java
                        )
                    )
                }
                .padding(10.dp)
                .size(width = 50.dp, height = 25.dp)
        )

        Text(
            text = "Authority Information",
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
fun AuthorityInformationSection() {
    val database = FirebaseDatabase.getInstance().getReference("authorities")
    var authorityList by remember { mutableStateOf(listOf<Authoritys>()) }
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                isLoading = false
                authorityList = snapshot.children.mapNotNull { it.getValue(Authoritys::class.java) }
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
        Text("Error loading authority information.")
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(authorityList) { authority ->
                AuthorityItem(authority)
            }
        }
    }
}

@Composable
fun AuthorityItem(authority: Authoritys) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            val painter: Painter = rememberImagePainter(authority.imageUrl)

            Image(
                painter = painter,
                contentDescription = "Authority Image",
                modifier = Modifier
                    .size(80.dp)
                    .padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.padding(16.dp)) {

                Text(text = "Name:  ${authority.name}", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = "Designation:  ${authority.designation}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Email:  ${authority.email}", style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Phone Number:  ${authority.phoneNumber}", style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(4.dp))
                // Add more details or actions for each authority item here

            }
        }
    }
}

data class Authoritys(
    val designation: String = "",
    val email: String = "",
    val imageUrl: String = "",
    val name: String = "",
    val phoneNumber: String = ""
)

@Preview(showBackground = true)
@Composable
fun AuthorityScreenPreview() {
    HallServiceAppTheme {
        AuthorityScreen()
    }
}

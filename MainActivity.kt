package com.arashsuren.studio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { StudioApp() }
    }
}

@Composable
fun StudioApp() {
    var tab by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(selected = tab == 0, onClick = { tab = 0 },
                    icon = { Icon(Icons.Default.Folder, null) }, label = { Text("Projects") })
                NavigationBarItem(selected = tab == 1, onClick = { tab = 1 },
                    icon = { Icon(Icons.Default.Create, null) }, label = { Text("Create") })
                NavigationBarItem(selected = tab == 2, onClick = { tab = 2 },
                    icon = { Icon(Icons.Default.Collections, null) }, label = { Text("Gallery") })
                NavigationBarItem(selected = tab == 3, onClick = { tab = 3 },
                    icon = { Icon(Icons.Default.Person, null) }, label = { Text("Characters") })
            }
        }
    ) { padding ->
        when (tab) {
            0 -> ProjectsScreen(Modifier.padding(padding))
            1 -> CreateScreen(Modifier.padding(padding))
            2 -> GalleryScreen(Modifier.padding(padding))
            else -> CharactersScreen(Modifier.padding(padding))
        }
    }
}

@Composable
fun Header(title: String, subtitle: String, modifier: Modifier = Modifier) {
    Column(modifier.fillMaxWidth().padding(20.dp)) {
        Text("Studio Arash Suren", style = MaterialTheme.typography.labelLarge)
        Text(title, style = MaterialTheme.typography.headlineMedium)
        Text(subtitle, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun ProjectsScreen(modifier: Modifier = Modifier) {
    val projects = listOf(
        "NEGAREH-E ZAMAN — Episode 22",
        "Arash Suren Teaser",
        "THE LIVING LEGACY OF PARTHIA",
        "SEVEN HOUSES AND THREE EMPIRES"
    )
    Column(modifier.fillMaxSize()) {
        Header("Projects", "Your creative worlds")
        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(projects) { name ->
                Card(Modifier.fillMaxWidth()) {
                    Row(Modifier.padding(16.dp)) {
                        Icon(Icons.Default.Folder, null)
                        Spacer(Modifier.padding(6.dp))
                        Text(name)
                    }
                }
            }
        }
    }
}

@Composable
fun CreateScreen(modifier: Modifier = Modifier) {
    var prompt by remember { mutableStateOf("") }
    var mode by remember { mutableStateOf("Image to Video") }

    Column(modifier.fillMaxSize().padding(20.dp)) {
        Header("Create", "Multi-engine AI creation")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { mode = "Text to Image" }) { Text("Text → Image") }
            Button(onClick = { mode = "Image to Video" }) { Text("Image → Video") }
        }
        Spacer(Modifier.height(14.dp))
        Text("Mode: $mode")
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = prompt,
            onValueChange = { prompt = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Prompt") },
            minLines = 4
        )
        Spacer(Modifier.height(14.dp))
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                Text("AI Router", style = MaterialTheme.typography.titleMedium)
                Text("Provider selection will be connected here.")
                Text("Credits: Demo mode")
            }
        }
        Spacer(Modifier.height(14.dp))
        Button(onClick = { }) {
            Icon(Icons.Default.Add, null)
            Spacer(Modifier.padding(4.dp))
            Text("Generate")
        }
    }
}

@Composable
fun GalleryScreen(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize()) {
        Header("Gallery", "Generated images, videos and audio")
        Card(Modifier.padding(16.dp).fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                Text("Gallery is ready for real generation results.")
                Text("No generations yet.")
            }
        }
    }
}

@Composable
fun CharactersScreen(modifier: Modifier = Modifier) {
    val chars = listOf("Arash Suren", "Panthea", "Wispa", "Mitra", "Custom Reference")
    Column(modifier.fillMaxSize()) {
        Header("Characters", "References and continuity")
        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(chars) { name ->
                Card(Modifier.fillMaxWidth()) {
                    Row(Modifier.padding(16.dp)) {
                        Icon(Icons.Default.Person, null)
                        Spacer(Modifier.padding(6.dp))
                        Text(name)
                    }
                }
            }
        }
    }
}

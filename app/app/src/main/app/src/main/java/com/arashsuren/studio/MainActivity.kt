package com.arashsuren.studio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                StudioArashSurenApp()
            }
        }
    }
}

@Composable
fun StudioArashSurenApp() {
    var selectedTab by remember { mutableIntStateOf(0) }

    val titles = listOf(
        "Studio",
        "Projects",
        "Gallery",
        "Create",
        "Characters"
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                titles.forEachIndexed { index, title ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        icon = {
                            Icon(
                                imageVector = when (index) {
                                    0 -> Icons.Default.AutoAwesome
                                    1 -> Icons.Default.Folder
                                    2 -> Icons.Default.Collections
                                    3 -> Icons.Default.Create
                                    else -> Icons.Default.Person
                                },
                                contentDescription = title
                            )
                        },
                        label = { Text(title) }
                    )
                }
            }
        }
    ) { padding ->
        when (selectedTab) {
            0 -> HomeScreen(padding)
            1 -> ProjectsScreen(padding)
            2 -> GalleryScreen(padding)
            3 -> CreateScreen(padding)
            4 -> CharactersScreen(padding)
        }
    }
}

@Composable
fun HomeScreen(padding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Studio Arash Suren",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "AI Creative Studio",
            style = MaterialTheme.typography.titleMedium
        )

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(18.dp)) {
                Text(
                    text = "Welcome",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Create images, videos, comics, characters and cinematic stories from one studio."
                )
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(18.dp)) {
                Text("Credits")
                Spacer(modifier = Modifier.height(6.dp))
                Text("Demo balance: 1,000 credits")
            }
        }
    }
}

@Composable
fun ProjectsScreen(padding: PaddingValues) {
    val projects = listOf(
        "Negareh-e Zaman",
        "Arash Suren Teaser",
        "Museum — The Living Legacy",
        "Seven Houses & Three Empires"
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                "Projects",
                style = MaterialTheme.typography.headlineMedium
            )
        }

        items(projects) { project ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = project,
                    modifier = Modifier.padding(18.dp)
                )
            }
        }
    }
}

@Composable
fun GalleryScreen(padding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            "Gallery",
            style = MaterialTheme.typography.headlineMedium
        )

        Text("Your generated images and videos will appear here.")
    }
}

@Composable
fun CreateScreen(padding: PaddingValues) {
    var selectedMode by remember { mutableStateOf("Text → Image") }
    var prompt by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            "Create",
            style = MaterialTheme.typography.headlineMedium
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                selected = selectedMode == "Text → Image",
                onClick = { selectedMode = "Text → Image" },
                label = { Text("Text → Image") }
            )

            FilterChip(
                selected = selectedMode == "Image → Video",
                onClick = { selectedMode = "Image → Video" },
                label = { Text("Image → Video") }
            )
        }

        OutlinedTextField(
            value = prompt,
            onValueChange = { prompt = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            label = { Text("Prompt") },
            placeholder = {
                Text("Describe what you want to create...")
            }
        )

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Generate")
        }

        Text(
            text = "Engine: Demo Engine",
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = "Credits: Demo mode",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun CharactersScreen(padding: PaddingValues) {
    val characters = listOf(
        "Arash Suren",
        "Panthea",
        "Wispa",
        "Mitra",
        "Custom Reference"
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                "Characters & References",
                style = MaterialTheme.typography.headlineMedium
            )
        }

        items(characters) { character ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Text(
                    character,
                    modifier = Modifier.padding(18.dp)
                )
            }
        }
    }
}

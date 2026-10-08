package com.arashsuren.studio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudioApp()
        }
    }
}

@Composable
fun StudioApp() {
    var selected by remember { mutableIntStateOf(0) }

    val tabs = listOf(
        "Home",
        "Projects",
        "Create",
        "Gallery",
        "Characters"
    )

    val icons = listOf(
        Icons.Default.Home,
        Icons.Default.Folder,
        Icons.Default.AutoAwesome,
        Icons.Default.Image,
        Icons.Default.People
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Studio Arash Suren")
                }
            )
        },
        bottomBar = {
            NavigationBar {
                tabs.forEachIndexed { index, label ->
                    NavigationBarItem(
                        selected = selected == index,
                        onClick = { selected = index },
                        icon = {
                            Icon(
                                icons[index],
                                contentDescription = label
                            )
                        },
                        label = { Text(label) }
                    )
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            when (selected) {
                0 -> Home()
                1 -> Projects()
                2 -> Create()
                3 -> Gallery()
                4 -> Characters()
            }
        }
    }
}

@Composable
fun Home() {
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            "Welcome to your creative studio",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            "Build images, videos, characters and stories from one mobile workspace."
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(18.dp)
            ) {
                Text(
                    "Credits",
                    style = MaterialTheme.typography.titleMedium
                )
                Text("100 demo credits")
            }
        }

        Text(
            "Your showcase",
            style = MaterialTheme.typography.titleLarge
        )

        listOf(
            "Arash Suren Teaser",
            "Studio Intro",
            "The Living Legacy of Parthia",
            "Seven Houses & Three Empires"
        ).forEach {
            Text("• $it")
        }
    }
}

@Composable
fun Projects() {
    val projects = listOf(
        "Negareh-e Zaman",
        "Arash Suren Teaser",
        "Museum — Living Legacy",
        "Seven Houses & Three Empires"
    )

    LazyColumn(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Text(
                "Projects",
                style = MaterialTheme.typography.headlineSmall
            )
        }

        items(projects) { name ->
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    name,
                    modifier = Modifier.padding(18.dp)
                )
            }
        }
    }
}

@Composable
fun Create() {
    var prompt by remember { mutableStateOf("") }
    var mode by remember { mutableStateOf("Text → Image") }

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            "Create",
            style = MaterialTheme.typography.headlineSmall
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf(
                "Text → Image",
                "Image → Video"
            ).forEach {
                FilterChip(
                    selected = mode == it,
                    onClick = { mode = it },
                    label = { Text(it) }
                )
            }
        }

        OutlinedTextField(
            value = prompt,
            onValueChange = { prompt = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            label = { Text("Prompt") },
            placeholder = {
                Text("Describe your scene...")
            }
        )

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Generate")
        }

        Text("Engine: Demo Router")
        Text("Generation API will be connected in the next build.")
    }
}

@Composable
fun Gallery() {
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            "Gallery",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            "Your generated images and videos will appear here."
        )

        Text("No generated media yet.")
    }
}

@Composable
fun Characters() {
    val characters = listOf(
        "Arash Suren",
        "Panthea",
        "Wispa",
        "Mitra",
        "Custom Reference"
    )

    LazyColumn(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Text(
                "Characters & References",
                style = MaterialTheme.typography.headlineSmall
            )
        }

        items(characters) { name ->
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    name,
                    modifier = Modifier.padding(18.dp)
                )
            }
        }
    }
}

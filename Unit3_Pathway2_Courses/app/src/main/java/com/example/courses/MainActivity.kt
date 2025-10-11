package com.example.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TopicScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TopicScreen(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(DataSource.topics) { topic ->
            TopicCard(topic)
        }
    }
}

@Composable
fun TopicCard(topic: Topic) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(topic.imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = 68.dp, height = 68.dp)
                .aspectRatio(1f),
        )
        Column {
            Text(
                text = stringResource(topic.name), modifier = Modifier.padding(
                    start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp
                )
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.ic_grain),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
                Text(
                    text = topic.availableCourses.toString(),
                    modifier = Modifier.padding(
                        start = 8.dp
                    )
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    CoursesTheme {
        TopicScreen()
    }
}
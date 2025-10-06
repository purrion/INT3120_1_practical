package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

data class Artwork(
    val title: String,
    val artist: String,
    val year: String,
    val imageRes: Int
)

@Composable
fun ArtSpaceScreen(modifier: Modifier = Modifier) {
    val artworks = listOf(
        Artwork(
            title = stringResource(R.string.artwork_title_1),
            artist = stringResource(R.string.artwork_artist_1),
            year = stringResource(R.string.artwork_year_1),
            imageRes = R.drawable.artwork_1
        ),
        Artwork(
            title = stringResource(R.string.artwork_title_2),
            artist = stringResource(R.string.artwork_artist_2),
            year = stringResource(R.string.artwork_year_2),
            imageRes = R.drawable.artwork_2
        ),
        Artwork(
            title = stringResource(R.string.artwork_title_3),
            artist = stringResource(R.string.artwork_artist_3),
            year = stringResource(R.string.artwork_year_3),
            imageRes = R.drawable.artwork_3
        ),
        Artwork(
            title = stringResource(R.string.artwork_title_4),
            artist = stringResource(R.string.artwork_artist_4),
            year = stringResource(R.string.artwork_year_4),
            imageRes = R.drawable.artwork_4
        )
    )

    var currentId by remember { mutableIntStateOf(0) }
    val artwork = artworks[currentId]

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtworkWall(imageRes = artwork.imageRes, title = artwork.title)
        ArtworkDescriptor(
            title = artwork.title, artist = artwork.artist, year = artwork.year
        )
        DisplayController(
            onPrevious = {
                currentId = if (currentId > 0) currentId - 1 else artworks.size - 1
            }, onNext = {
                currentId = if (currentId < artworks.size - 1) currentId + 1 else 0
            }
        )
    }
}

@Composable
fun ArtworkWall(
    imageRes: Int,
    title: String
) {
    Box(modifier = Modifier.padding(vertical = 60.dp)) {
        Box(
            modifier = Modifier
                .width(300.dp)
                .height(380.dp)
                .shadow(4.dp, RectangleShape)
                .padding(30.dp)
        ) {
            Image(
                painterResource(imageRes),
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Composable
fun ArtworkDescriptor(
    title: String,
    artist: String,
    year: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
    ) {
        Text(
            text = title,
            fontSize = 26.sp,
            textAlign = TextAlign.Center
        )
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(artist)
            Text(text = "($year)", fontStyle = FontStyle.Italic)
        }
    }
}

@Composable
fun DisplayController(
    onPrevious: () -> Unit,
    onNext: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = onPrevious) { Text("Previous") }
        Button(onClick = onNext) { Text("Next") }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}
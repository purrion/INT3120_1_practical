package com.example.composearticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composearticle.ui.theme.ComposeArticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeArticleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Article.Page(
                        title = stringResource(R.string.sample_article_title),
                        description = stringResource(R.string.sample_article_description),
                        content = stringResource(R.string.sample_article_content),
                        bannerImagePainter = painterResource(R.drawable.bg_compose_background),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


object Article {
    @Composable
    fun Page(
        title: String,
        description: String,
        content: String,
        bannerImagePainter: Painter,
        modifier: Modifier = Modifier,
        bannerDescription: String? = null,
    ) {
        Column(modifier = modifier) {
            Banner(
                imagePainter = bannerImagePainter,
                contentDescription = bannerDescription
            )
            Title(text = title, modifier = Modifier.padding(16.dp))
            Description(text = description, modifier = Modifier.padding(start = 16.dp, end = 16.dp))
            Content(text = content, modifier = Modifier.padding(16.dp))
        }
    }

    @Composable
    private fun Banner(
        imagePainter: Painter,
        modifier: Modifier = Modifier,
        contentDescription: String? = null
    ) {
        Image(
            painter = imagePainter,
            contentDescription = contentDescription,
            modifier = modifier
        )
    }

    @Composable
    private fun Title(text: String, modifier: Modifier = Modifier) {
        Text(text = text, modifier = modifier, fontSize = 24.sp)
    }

    @Composable
    private fun Description(text: String, modifier: Modifier = Modifier) {
        Text(text = text, modifier = modifier, textAlign = TextAlign.Justify)
    }

    @Composable
    private fun Content(text: String, modifier: Modifier = Modifier) {
        Text(text = text, modifier = modifier, textAlign = TextAlign.Justify)
    }
}

@Preview(showBackground = true)
@Composable
fun BannerPreview() {
    ComposeArticleTheme {
        Article.Page(
            title = stringResource(R.string.sample_article_title),
            description = stringResource(R.string.sample_article_description),
            content = stringResource(R.string.sample_article_content),
            bannerImagePainter = painterResource(R.drawable.bg_compose_background)
        )
    }
}
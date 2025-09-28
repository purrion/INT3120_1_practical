package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Email
import androidx.compose.material.icons.sharp.Phone
import androidx.compose.material.icons.sharp.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme
import com.example.businesscard.ui.theme.Green60
import com.example.businesscard.ui.theme.Green90
import com.example.businesscard.ui.theme.Teal90

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BusinessCard(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BusinessCard(
    modifier: Modifier = Modifier
) {
    val profileImage = painterResource(R.drawable.android_logo)
    val fullName = stringResource(R.string.sample_fullname)
    val jobTitle = stringResource(R.string.sample_job_title)

    val contacts = listOf(
        Icons.Sharp.Phone to stringResource(R.string.sample_contact_phone),
        Icons.Sharp.Share to stringResource(R.string.sample_username),
        Icons.Sharp.Email to stringResource(R.string.sample_email)
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Green60),
    ) {
        ProfileSection(
            profileImage, fullName, jobTitle,
            Modifier.weight(3f)
        )
        ContactSection(
            contacts,
            Modifier
                .weight(1f)
                .padding(bottom = 32.dp)
        )
    }
}

@Composable
fun ProfileSection(
    profileImage: Painter,
    fullName: String,
    jobTitle: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = profileImage,
            contentDescription = fullName,
            modifier = Modifier
                .background(Teal90)
                .fillMaxWidth(0.3f)
        )
        Text(text = fullName, fontSize = 40.sp)
        Text(
            text = jobTitle, color = Green90,
            fontSize = 20.sp
        )
    }
}

@Composable
fun ContactSection(
    contacts: List<Pair<ImageVector, String>>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        contacts.forEach { (icon, text) ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(imageVector = icon, contentDescription = null)
                Text(text = text)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    BusinessCardTheme {
        BusinessCard()
    }
}
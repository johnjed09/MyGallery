package com.example.mygallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mygallery.ui.theme.MyGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyGalleryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppMainScreen()
                }
            }
        }
    }
}

@Composable
fun AppMainScreen() {
    var currentImageID by remember { mutableStateOf(1) }
    val currentImage: Int
    val currentTitle: Int
    val currentDetails: Int

    when (currentImageID) {
        1 -> {
            currentImage = R.drawable.img1
            currentTitle = R.string.title_img1
            currentDetails = R.string.subtitle_img1
        }
        2 -> {
            currentImage = R.drawable.img2
            currentTitle = R.string.title_img2
            currentDetails = R.string.subtitle_img2

        }
        3 -> {
            currentImage = R.drawable.img3
            currentTitle = R.string.title_img3
            currentDetails = R.string.subtitle_img3

        }
        4 -> {
            currentImage = R.drawable.img4
            currentTitle = R.string.title_img4
            currentDetails = R.string.subtitle_img4

        }
        5 -> {
            currentImage = R.drawable.img5
            currentTitle = R.string.title_img5
            currentDetails = R.string.subtitle_img5

        }
        else -> {
            currentImage = R.drawable.img6
            currentTitle = R.string.title_img6
            currentDetails = R.string.subtitle_img6
        }
    }

    Column(
        modifier = Modifier
            .padding(32.dp, 38.dp, 32.dp, 0.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        ArtWorkWall(currentImage)
        ArtDescriptor(currentTitle, currentDetails)
        DisplayControllers(
            onClickNext = { currentImageID = nextImage(currentImageID) },
            onClickPrev = { currentImageID = prevImage(currentImageID) })
    }
}

@Composable
fun ArtWorkWall(currentImage: Int) {
    val image = painterResource(currentImage)

    Image(
        painter = image,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray, RoundedCornerShape(16.dp))
            .size(500.dp)
    )
}

@Composable
fun ArtDescriptor(currentTitle: Int, currentDetails: Int) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 18.dp)
    ) {
        Text(
            fontSize = 24.sp,
            text = stringResource(currentTitle),
            letterSpacing = 4.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            fontSize = 18.sp,
            text = stringResource(currentDetails),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

fun nextImage(currentImageID: Int): Int {
    val newImageID = currentImageID + 1

    return if (newImageID > 6) 1
    else newImageID
}

fun prevImage(currentImageID: Int): Int {
    val newImageID = currentImageID - 1

    return if (newImageID < 1) 6
    else newImageID
}

@Composable
fun DisplayControllers(onClickNext: () -> Unit, onClickPrev: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(onClick = onClickPrev) {
            Text(text = "Prev")
        }

        Button(
            onClick = onClickNext,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
        ) {
            Text(text = "Next")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyGalleryTheme {
        AppMainScreen()
    }
}
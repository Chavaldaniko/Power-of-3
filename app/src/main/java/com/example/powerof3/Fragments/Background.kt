package com.example.powerof3.Fragments

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.powerof3.R

@Composable
fun ShowMainPicture() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.main_picture),
            contentDescription = "PNG Image",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .sizeIn(
                    minWidth = 300.dp,
                    maxWidth = 700.dp,
                    minHeight = 300.dp,
                    maxHeight = 700.dp
                )
                .fillMaxWidth(0.8f)
                .offset(y = 200.dp)
        )
    }
}

@Composable
fun GameOverPicture() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 40.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.game_over_picture),
            contentDescription = "PNG Image",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .sizeIn(
                    minWidth = 200.dp,
                    maxWidth = 400.dp,
                    minHeight = 100.dp,
                    maxHeight = 200.dp
                )
                .fillMaxWidth(0.7f)
        )
    }

}
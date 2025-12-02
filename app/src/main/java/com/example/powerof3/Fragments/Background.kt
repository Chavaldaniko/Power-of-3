package com.example.powerof3.Fragments

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.powerof3.R

@Composable
fun ShowPNGOnWhiteBackground() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    )
    {
        /*
       Box(
           modifier = Modifier.fillMaxSize(),
           contentAlignment = Alignment.Center
       ) {

           Image(
               painter = painterResource(id = R.drawable.imba),
               contentDescription = "PNG Image",
               contentScale = ContentScale.Fit,
               modifier = Modifier.fillMaxSize(0.8f)
           )

        }
        */


    }
}
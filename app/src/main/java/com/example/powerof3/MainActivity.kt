package com.example.powerof3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.powerof3.Fragments.InputFragment
import com.example.powerof3.Fragments.ShowPNGOnWhiteBackground
import com.example.powerof3.Fragments.GameScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            var showInputFragment by remember { mutableStateOf(true) }
            var gameSize by remember { mutableStateOf<Int?>(null) }
            var userName by remember { mutableStateOf("") }

            Box(Modifier.fillMaxSize()) {
                ShowPNGOnWhiteBackground()

                if (showInputFragment) {
                    InputFragment { userInput ->
                        userName = userInput.name
                        gameSize = userInput.number

                        println("Имя пользователя: $userName")
                        println("Введенное число: $gameSize")

                        showInputFragment = false
                    }
                } else {
                    gameSize?.let { size ->
                        GameScreen(size = size)
                    }
                }
            }
        }
    }
}
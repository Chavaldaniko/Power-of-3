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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            var showInputFragment by remember { mutableStateOf(true) }

            Box(Modifier.fillMaxSize()) {
                ShowPNGOnWhiteBackground()

                if (showInputFragment) {
                    InputFragment { userInput ->
                        val name = userInput.name
                        val number = userInput.number

                        println("Имя пользователя: $name")
                        println("Введенное число: $number")

                        showInputFragment = false

                        if (number != null) {
                            GameObject(name, number)
                        }


                    }
                }
            }
        }
    }

    private fun GameObject(name: String, number: Int) {
        println("GameObject: Имя=$name, Число=$number")
    }
}
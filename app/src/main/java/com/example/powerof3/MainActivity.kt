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
import com.example.powerof3.repository.RecordsRepository
import androidx.room.Room
import com.example.powerof3.database.DataBase

class MainActivity : ComponentActivity() {

    // Объявляем как lateinit
    private lateinit var database: DataBase
    private lateinit var repository: RecordsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        database = Room.databaseBuilder(
            applicationContext,
            DataBase::class.java,
            "game-records-db"
        )
            .fallbackToDestructiveMigration(false)
            .build()

        repository = RecordsRepository(database.recordsDAO())

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
                        GameScreen(
                            size = size,
                            playerName = userName,
                            recordsRepository = repository,
                            onGameFinished = {
                                showInputFragment = true
                                gameSize = null
                                userName = ""
                            }
                        )
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        database.close()
        super.onDestroy()
    }
}
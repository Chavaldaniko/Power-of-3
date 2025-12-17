package com.example.powerof3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.powerof3.Fragments.*
import com.example.powerof3.repository.RecordsRepository
import androidx.room.Room
import com.example.powerof3.database.DataBase

class MainActivity : ComponentActivity() {

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
3
        repository = RecordsRepository(database.recordsDAO())

        setContent {
            var currentScreen by remember { mutableStateOf<AppScreen>(AppScreen.InputFragment) }
            var gameSize by remember { mutableStateOf<Int?>(null) }
            var userName by remember { mutableStateOf("") }

            Box(Modifier.fillMaxSize()) {

                when (currentScreen) {
                    AppScreen.InputFragment -> {
                        InputFragment { userInput ->
                            userName = userInput.name
                            gameSize = userInput.number
                            currentScreen = AppScreen.GameScreen
                        }
                    }

                    AppScreen.GameScreen -> {
                        gameSize?.let { size ->
                            GameScreen(
                                size = size,
                                playerName = userName,
                                recordsRepository = repository,
                                onGameFinished = {
                                    currentScreen = AppScreen.InputFragment
                                    gameSize = null
                                    userName = ""
                                },
                                onViewRecords = {
                                    currentScreen = AppScreen.RecordsFragment
                                }
                            )
                        }
                    }

                    AppScreen.RecordsFragment -> {
                        RecordsFragment(
                            recordsRepository = repository,
                            onNavigateBack = {
                                currentScreen = AppScreen.GameScreen
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

    sealed class AppScreen {
        object InputFragment : AppScreen()
        object GameScreen : AppScreen()
        object RecordsFragment : AppScreen()
    }
}
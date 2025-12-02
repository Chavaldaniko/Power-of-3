package com.example.powerof3.Fragments

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

data class UserInput(
    val name: String = "",
    val number: Int? = null
)

@Composable
fun InputFragment(
    onInputComplete: (UserInput) -> Unit = {}
) {
    var name by remember { mutableStateOf("") }
    var numberText by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .wrapContentHeight(),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Введите данные",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Ваше имя") },
                    placeholder = { Text("Введите имя") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = numberText,
                    onValueChange = { if (it.matches(Regex("^\\d*$"))) numberText = it },
                    label = { Text("Введите число") },
                    placeholder = { Text("0") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Button(
                    onClick = {
                        val userInput = UserInput(
                            name = name,
                            number = numberText.toIntOrNull()
                        )
                        onInputComplete(userInput)
                        focusManager.clearFocus()
                    },
                    enabled = name.isNotEmpty() && numberText.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Подтвердить")
                }

                if (name.isEmpty() || numberText.isEmpty()) {
                    Text(
                        text = "Заполните все поля",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}
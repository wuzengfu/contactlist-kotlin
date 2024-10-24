package com.example.contactlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.contactlist.ui.theme.ContactListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier) {
    val textFieldPlaceHolder = "Enter name here..."
    var name by remember { mutableStateOf("") }
    var contactList by remember { mutableStateOf<List<String>>(listOf()) }

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Column {
            Row {
                OutlinedTextField(value = name, onValueChange = { textFieldValue ->
                    name = textFieldValue
                }, placeholder = { Text(textFieldPlaceHolder) }, modifier = Modifier.weight(3f))

                FilledTonalButton(onClick = {
                    if (name.isBlank()) {
                        return@FilledTonalButton
                    }
                    contactList = contactList + name
                    name = ""
                }, modifier = Modifier.weight(1f)) {
                    Text("Add")
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))

            LazyColumn {
                items(contactList) { contact ->
                    Text(contact)
                    HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ContactListTheme {
        Greeting(modifier = Modifier)
    }
}
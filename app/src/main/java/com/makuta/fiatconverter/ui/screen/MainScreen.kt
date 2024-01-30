package com.makuta.fiatconverter.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

class MainScreen {

    @Composable
    fun Content(modifier : Modifier){
        Box(modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
            Text(text = "Hello, App!",modifier.align(Alignment.Center))
        }
    }

    @Preview
    @Composable
    fun Preview(){
         MainScreen().Content(Modifier.background(Color.White))
    }
}
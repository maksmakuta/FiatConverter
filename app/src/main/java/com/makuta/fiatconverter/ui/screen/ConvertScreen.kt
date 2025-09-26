package com.makuta.fiatconverter.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.makuta.fiatconverter.ui.component.InputSelector
import com.makuta.fiatconverter.ui.component.NumPad
import com.makuta.fiatconverter.ui.theme.AppTheme

@Composable
fun ConvertScreen(){
    var inputValue by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.systemBarsPadding().fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column{
            InputSelector(title = "From")
            InputSelector(title = "To")
            NumPad(
                onNumberClick = { number ->
                    if (inputValue.length < 10) {
                        inputValue += number
                    }
                },
                onDeleteClick = {
                    if (inputValue.isNotEmpty()) {
                        inputValue = inputValue.dropLast(1)
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0)
@Composable
fun PreviewDark(){
    AppTheme(darkTheme = true,false) {
        ConvertScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLight(){
    AppTheme(darkTheme = false,false) {
        ConvertScreen()
    }
}
package com.makuta.fiatconverter.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.makuta.fiatconverter.ui.theme.AppTheme
import com.makuta.fiatconverter.vm.ConvertVM

@Composable
fun ConvertScreen(){
    val vm : ConvertVM = viewModel<ConvertVM>()
}

@Preview(showBackground = true, backgroundColor = 0)
@Composable
fun PreviewDark(){
    AppTheme(darkTheme = true) {
        ConvertScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLight(){
    AppTheme(darkTheme = false) {
        ConvertScreen()
    }
}
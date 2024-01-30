package com.makuta.fiatconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import com.makuta.fiatconverter.ui.screen.MainScreen
import com.makuta.fiatconverter.ui.theme.FiatConverterTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            FiatConverterTheme.Composer {
                MainScreen().Content(Modifier)
            }
        }
    }
}
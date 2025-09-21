package com.makuta.fiatconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.makuta.fiatconverter.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                SampleScreen()
            }
        }
    }
}

@Composable
fun SampleScreen() {
    Surface(
        tonalElevation = 2.dp,
        modifier = androidx.compose.ui.Modifier.fillMaxSize()
    ) {
        androidx.compose.material3.Text("Hello, AppTheme!", style = MaterialTheme.typography.titleLarge, modifier = androidx.compose.ui.Modifier.padding(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLight() {
    AppTheme(darkTheme = false) {
        SampleScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDark() {
    AppTheme(darkTheme = true) {
        SampleScreen()
    }
}
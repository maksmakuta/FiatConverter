package com.makuta.fiatconverter.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.makuta.fiatconverter.ui.theme.AppTheme

@Composable
fun CurrencySelector(){
    OutlinedCard{
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            AsyncImage(model = "https://flagcdn.com/56x42/us.webp", contentDescription = "", modifier = Modifier.padding(4.dp))
            Text("USD")
            Icon(Icons.Default.KeyboardArrowDown, contentDescription = "")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSelectorDark(){
    AppTheme(darkTheme = true,false) {
        CurrencySelector()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSelectorLight(){
    AppTheme(darkTheme = true,false) {
        CurrencySelector()
    }
}

package com.makuta.fiatconverter.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.makuta.fiatconverter.ui.theme.AppTheme

@Composable
fun CurrencyItem(data : Pair<String, Double>){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = "https://flagcdn.com/h80/${data.first.substring(0,2).lowercase()}.webp",
            contentDescription = null,
        )

        Text(
            text = data.second.toString() + " " + data.first,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLight() {
    AppTheme(darkTheme = false) {
        CurrencyItem(Pair("USD",2.656))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDark() {
    AppTheme(darkTheme = true) {
        CurrencyItem(Pair("USD",2.656))
    }
}
package com.makuta.fiatconverter.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.makuta.fiatconverter.ui.theme.AppTheme
import com.makuta.fiatconverter.ui.theme.AppTypography

@Composable
fun InputSelector(title: String) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth().padding(16.dp, 8.dp)
    ){
        Column (
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ){
            Text(
                text = title,
                modifier = Modifier.padding(8.dp),
                style = AppTypography.titleMedium
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                CurrencySelector()
                CurrencyInput()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDark(){
    AppTheme(darkTheme = true,false) {
        InputSelector(title = "From")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLight(){
    AppTheme(darkTheme = false,false) {
        InputSelector("To")
    }
}


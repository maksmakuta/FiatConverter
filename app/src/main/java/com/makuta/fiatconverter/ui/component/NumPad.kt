package com.makuta.fiatconverter.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.makuta.fiatconverter.ui.theme.AppTheme
import com.makuta.fiatconverter.R

@Composable
fun NumPad(
    onNumberClick: (String) -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val buttonModifier = Modifier
            .size(72.dp)
            .clip(RoundedCornerShape(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            NumpadButton("1", buttonModifier) { onNumberClick("1") }
            NumpadButton("2", buttonModifier) { onNumberClick("2") }
            NumpadButton("3", buttonModifier) { onNumberClick("3") }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            NumpadButton("4", buttonModifier) { onNumberClick("4") }
            NumpadButton("5", buttonModifier) { onNumberClick("5") }
            NumpadButton("6", buttonModifier) { onNumberClick("6") }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            NumpadButton("7", buttonModifier) { onNumberClick("7") }
            NumpadButton("8", buttonModifier) { onNumberClick("8") }
            NumpadButton("9", buttonModifier) { onNumberClick("9") }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            NumpadButton(".", buttonModifier) { onNumberClick(".") }

            NumpadButton("0", buttonModifier) { onNumberClick("0") }

            ActionButton(
                onClick = onDeleteClick,
                backgroundColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer,
                modifier = buttonModifier
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_backspace),
                    contentDescription = "Delete",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNumPadDark(){
    AppTheme(darkTheme = true,false) {
        NumPad({ _ ->}, {})
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNumPadLight(){
    AppTheme(darkTheme = false,false) {
        NumPad({ _ ->}, {})
    }
}
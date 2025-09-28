package com.makuta.fiatconverter.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.makuta.fiatconverter.ui.theme.AppTheme
import com.makuta.fiatconverter.ui.theme.AppTypography
import com.makuta.fiatconverter.vm.ConvertVM

@Composable
fun ConvertScreen(){
    val vm : ConvertVM = viewModel()

    var input           by remember { mutableIntStateOf(0) }
    var currencyFrom    by remember { mutableStateOf("USD") }
    var currencyTo      by remember { mutableStateOf("EUR") }
    var amountFrom      by remember { mutableStateOf("0.00") }
    var amountTo        by remember { mutableStateOf("0.00") }
    var list            by remember { mutableIntStateOf(0) }

    val currencies      by vm.currencies.collectAsState()
    val error           by vm.errorMessage.collectAsState()
    val converted       by vm.convertData.collectAsState()

    fun convert(){
        vm.convert(
            if(input == 0) currencyFrom else currencyTo,
            if(input == 0) currencyTo else currencyFrom,
            (if(input == 0) amountFrom else amountTo).toDoubleOrNull() ?: 0.0
        )

        if(input == 0){
            amountTo = converted.result.toString()
        }else{
            amountFrom = converted.result.toString()
        }
    }

    fun onInput(num : Int){
        var data = if(input == 0) { amountFrom } else { amountTo }
        data = when (num) {
            -1 -> if(data.isNotEmpty()) data.dropLast(1) else ""
            -2 -> if(data.count { it -> it == '.' } == 0) "$data." else data
            10 -> ""
            else -> data + num.toString()
        }
        if(input == 0) {
            amountFrom = data
            if(amountFrom.isNotEmpty())
                convert()
        } else {
            amountTo = data
            if(amountTo.isNotEmpty())
                convert()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
            Column (
                modifier = Modifier.padding(0.dp, 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                OutlinedCard (
                    onClick = {
                        input = 0
                        amountFrom = ""
                        converted.result = 0.0
                    },
                    modifier = Modifier.fillMaxWidth().padding(16.dp, 8.dp),
                    border = CardDefaults.outlinedCardBorder(input == 0)
                ){
                    Text("From", modifier = Modifier.padding(top = 16.dp, start = 16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ){
                        Card{
                            Row(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clickable(
                                        onClick = {
                                            list = 1
                                        }
                                    ),
                            ){
                                CurrencyItem(currencyFrom)
                                Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                            }
                            DropdownCurrencies(currencies,list == 1,{list = 0 }) { code -> currencyFrom = code }
                        }
                        Card(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(8.dp),
                                 horizontalArrangement = Arrangement.End
                            ) {
                                Text(text = if(input == 1) converted.result.toString() else amountFrom )
                            }
                        }
                    }
                }

                OutlinedCard (
                    onClick = {
                        input = 1
                        amountTo = ""
                        converted.result = 0.0
                    },
                    modifier = Modifier.fillMaxWidth().padding(16.dp, 8.dp),
                    border = CardDefaults.outlinedCardBorder(input == 1)
                ){
                    Text("To", modifier = Modifier.padding(top = 16.dp, start = 16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ){
                        Card{
                            Row(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clickable(
                                        onClick = {
                                            list = 2
                                        }
                                    ),
                            ){
                                CurrencyItem(currencyTo)
                                Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                            }
                            DropdownCurrencies(currencies,list == 2, {list = 0 }) { code -> currencyTo = code }
                        }
                        Card(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(8.dp),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Text(text = if(input == 0) converted.result.toString() else amountTo)
                            }
                        }
                    }
                }

                if(error.isNotEmpty()){
                    Text(error, color = MaterialTheme.colorScheme.error)
                }

                Column (
                    modifier = Modifier.fillMaxWidth().padding(16.dp, 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ){
                        NumButton("7") { onInput(7) }
                        NumButton("8") { onInput(8) }
                        NumButton("9") { onInput(9) }
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ){
                        NumButton("4") { onInput(4) }
                        NumButton("5") { onInput(5) }
                        NumButton("6") { onInput(6) }
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ){
                        NumButton("1") { onInput(1) }
                        NumButton("2") { onInput(2) }
                        NumButton("3") { onInput(3) }
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ){
                        NumButton(".") { onInput(-2) }
                        NumButton("0") { onInput(0) }
                        NumButton("<") { onInput(-1) }
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ){
                        NumButton("C") { onInput(10) }
                    }
                }
            }
        }
}

@Composable
fun NumButton(
    text : String,
    onClick : () -> Unit
){
    Button(
        onClick = onClick,
        modifier = Modifier.size(72.dp),
        shape = RoundedCornerShape(24.dp)
    ) {
        Text(
            text,
            style = AppTypography.headlineSmall
        )
    }
}

@Composable
fun CurrencyItem(code : String){
    Row (
        verticalAlignment = Alignment.CenterVertically
    ){
        AsyncImage(
            model = "https://flagcdn.com/56x42/${code.substring(0,2).lowercase()}.webp",
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(code)
    }
}

@Composable
fun DropdownCurrencies(
    items : List<String>,
    visible : Boolean,
    onDismiss: () -> Unit,
    onClick: (String) -> Unit
){
    DropdownMenu(
        expanded = visible,
        onDismissRequest = onDismiss,
    ) {
        items.forEach { it ->
            DropdownMenuItem(
                text = @Composable {
                    CurrencyItem(it)
                },
                onClick = {
                    onClick(it)
                    onDismiss()
                }
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF16130A)
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

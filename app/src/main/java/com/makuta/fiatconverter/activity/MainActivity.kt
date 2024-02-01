@file:OptIn(ExperimentalMaterial3Api::class)

package com.makuta.fiatconverter.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults.colors
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.makuta.fiatconverter.CurrencyData
import com.makuta.fiatconverter.theme.FiatConverterTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            FiatConverterTheme.Composer {
                Content(Modifier)
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Content(modifier: Modifier){
        val vm : MainVM = viewModel()
        val list = vm.list.observeAsState()
        val resp = vm.resp.observeAsState()

        val composeCoroutine = rememberCoroutineScope()
        val sheetState = rememberModalBottomSheetState()

        val currencyMod = remember{ mutableStateOf(true) }
        val currencyFrom = remember { mutableStateOf("USD") }
        val currencyTo = remember { mutableStateOf("EUR") }
        val currencyAmount = remember { mutableStateOf("0.0") }

        Box(
            modifier = modifier.fillMaxSize()
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)) {
                Box(
                    modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .clickable {
                            composeCoroutine.launch {
                                currencyMod.value = true
                                sheetState.show()
                            }
                        }
                ) {
                    Text(text = "From : ${currencyFrom.value}",modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 16.dp))
                }
                Box(
                    modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .clickable {
                            composeCoroutine.launch {
                                currencyMod.value = false
                                sheetState.show()
                            }
                        }
                ) {
                    Text(
                        text = "To : ${currencyTo.value}", modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp, 16.dp)
                    )
                }
                TextField(
                    value = currencyAmount.value,
                    label = { Text(text = "Amount") },
                    onValueChange = {str ->
                        currencyAmount.value = if(str.isEmpty())
                            "0"
                        else
                            str
                    },
                    colors = colors(
                        focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                        unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    ),
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
                Text(
                    text = genResult(resp.value,currencyFrom.value,currencyTo.value,currencyAmount.value),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                    )
                Button(onClick = {
                    vm.convert(currencyFrom.value.lowercase())
                },modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)) {
                    Text(text = "Convert", color = MaterialTheme.colorScheme.background)
                }
            }
        }
        if(sheetState.isVisible) {
            ModalBottomSheet(onDismissRequest = {}, sheetState = sheetState,dragHandle = { BottomSheetDefaults.DragHandle() }) {
                if (list.value != null) {
                    val map = list.value!!
                    val values = map.keys
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(values.toList(), key = { it.hashCode() }) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    if (!currencyMod.value) {
                                        currencyTo.value = it.uppercase()
                                    } else {
                                        currencyFrom.value = it.uppercase()
                                    }
                                    composeCoroutine.launch {
                                        sheetState.hide()
                                    }
                                }
                                .padding(16.dp, 16.dp)
                            ) {
                                Text(text = it.uppercase(),modifier = Modifier.weight(0.2f))
                                Text(text = map[it] ?: "Unknown",modifier = Modifier.weight(0.5f))
                            }
                            Divider(
                                color = MaterialTheme.colorScheme.tertiaryContainer
                            )
                        }
                    }
                } else {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
            }
        }
    }

    private fun genResult(resp : CurrencyData?, currencyFrom : String, currencyTo : String, amount : String) : String {
        return if (resp != null) {
            val m = resp[currencyTo.lowercase()]!! * amount.toDouble()
            "$amount ${currencyFrom.uppercase()} = ${ "%.2f".format(m) } ${currencyTo.uppercase()}"
        }else
            ""
    }

}

@Preview
@Composable
fun Preview(){
    MainActivity().Content(Modifier.background(Color.White))
}
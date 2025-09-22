package com.makuta.fiatconverter.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.makuta.fiatconverter.net.ApiService
import com.makuta.fiatconverter.ui.component.CurrencyItem
import com.makuta.fiatconverter.ui.theme.AppTheme
import com.makuta.fiatconverter.vm.RatesVM

@Composable
fun RatesScreen(
    modifier : Modifier = Modifier
){
    val vm = viewModel{ RatesVM(ApiService().provideService()) }
    val rates = vm.rates.collectAsState()

    LaunchedEffect(Unit) {
        vm.loadRates()
    }

    if(rates.value != null){
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(rates.value!!.rates.toList()) { item ->
                CurrencyItem(item)
            }
        }
    }else{
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                Modifier.align(alignment = Alignment.Center)
            ) {
                CircularProgressIndicator()
                Text("Loading...")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLight() {
    AppTheme(darkTheme = false) {
        RatesScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDark() {
    AppTheme(darkTheme = true) {
        RatesScreen()
    }
}
package com.makuta.fiatconverter.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.makuta.fiatconverter.model.ExchangeRates
import com.makuta.fiatconverter.net.ApiInterface
import com.makuta.fiatconverter.net.NetResult
import com.makuta.fiatconverter.net.safeNetCall
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RatesVM(
    private val api : ApiInterface
) : ViewModel(){

    private val _rates = MutableStateFlow<ExchangeRates?>(null)
    val rates: StateFlow<ExchangeRates?> = _rates

    fun loadRates(base: String = "USD", symbols: List<String> = listOf()) {
        viewModelScope.launch {
            try {
                val res = safeNetCall{api.getRates()}
                if(res is NetResult.Success){
                    _rates.value = res.data
                }
            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }

}
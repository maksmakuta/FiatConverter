package com.makuta.fiatconverter.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.makuta.fiatconverter.model.ConvertResponse
import com.makuta.fiatconverter.net.UniRateService
import com.makuta.fiatconverter.utils.NetResult
import com.makuta.fiatconverter.utils.safeNetCall
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ConvertVM : ViewModel() {

    private val uniRateService = UniRateService().provideService()

    private val _errorMessage = MutableStateFlow("")
    private val _convertData = MutableStateFlow(
        ConvertResponse(
            0.0,
            "",
            0.0,
            ""
        )
    )
    private val _currencies = MutableStateFlow(
        listOf(
            "AED", "AFN", "ALL", "AMD", "AOA", "ARS", "AUD", "AWG", "AZN",
            "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB",
            "BRL", "BSD", "BTN", "BWP", "BYN", "BZD", "CAD", "CDF", "CHF",
            "CLP", "CNY", "COP", "CRC", "CUC", "CUP", "CVE", "CZK", "DJF",
            "DKK", "DOP", "DZD", "EGP", "ERN", "ETB", "EUR", "FJD", "FKP",
            "GBP", "GEL", "GHS", "GIP", "GMD", "GNF", "GTQ", "GYD", "HKD",
            "HNL", "HTG", "HUF", "IDR", "ILS", "INR", "IQD", "IRR", "ISK",
            "JMD", "JOD", "JPY", "KES", "KGS", "KHR", "KMF", "KPW", "KRW",
            "KWD", "KYD", "KZT", "LAK", "LBP", "LKR", "LRD", "LSL", "LYD",
            "MAD", "MDL", "MGA", "MKD", "MMK", "MNT", "MOP", "MRU", "MUR",
            "MVR", "MWK", "MXN", "MYR", "MZN", "NAD", "NGN", "NIO", "NOK",
            "NPR", "NZD", "OMR", "PAB", "PEN", "PGK", "PHP", "PKR", "PLN",
            "PYG", "QAR", "RON", "RSD", "RUB", "RWF", "SAR", "SBD", "SCR",
            "SDG", "SEK", "SGD", "SHP", "SLE", "SOS", "SRD", "SSP", "STN",
            "SYP", "SZL", "THB", "TJS", "TMT", "TND", "TOP", "TRY", "TTD",
            "TWD", "TZS", "UAH", "UGX", "USD", "UYU", "UZS", "VED", "VND",
            "VUV", "WST", "YER", "ZAR", "ZMW", "ZWL"
        )
    )

    val currencies = _currencies.asStateFlow()
    val errorMessage = _errorMessage.asStateFlow()
    val convertData = _convertData.asStateFlow()

    fun convert(from : String,to : String, value : Double){
        viewModelScope.launch {
            val result = safeNetCall {
                uniRateService.convert(from,to,value)
            }
            when(result){
                is NetResult.Error -> _errorMessage.value = result.message ?: "Error code: ${ result.code ?: 404}"
                is NetResult.Success -> _convertData.value = result.data
            }
        }
    }

}
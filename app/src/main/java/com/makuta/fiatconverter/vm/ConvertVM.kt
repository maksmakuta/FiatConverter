package com.makuta.fiatconverter.vm

import androidx.compose.animation.core.withInfiniteAnimationFrameMillis
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.makuta.fiatconverter.net.UniRateService
import com.makuta.fiatconverter.utils.NetResult
import com.makuta.fiatconverter.utils.safeNetCall
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ConvertVM : ViewModel() {
    private val service = UniRateService().provideService()

    private val _currencies = MutableStateFlow<List<String>>(listOf())
    val currencies = _currencies.asStateFlow()

    private val _error = MutableStateFlow("")
    val error = _error.asStateFlow()

    fun loadList(){
        viewModelScope.launch {
            val result = safeNetCall{
                service.getCurrencies()
            }
            if(result is NetResult.Success){
                _error.value = ""

                val data = result.data

                _currencies.value = data.currencies.filter { it -> it.length == 3 }
            }else{
                _error.value = (result as NetResult.Error).message ?: "Error Code: ${result.code ?: "Unknown"}"
            }
        }
    }

    fun convert(from : String, to : String, amount : Double){}

}
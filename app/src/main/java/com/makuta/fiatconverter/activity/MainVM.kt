package com.makuta.fiatconverter.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.makuta.fiatconverter.CurrencyData
import com.makuta.fiatconverter.CurrencyList
import com.makuta.fiatconverter.api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainVM : ViewModel(){

    private val service = ApiService().provideService()

    private val _list = MutableLiveData<CurrencyList>()
    private val _resp = MutableLiveData<CurrencyData>()

    val list : LiveData<CurrencyList> = _list
    val resp : LiveData<CurrencyData> = _resp

    init {
        loadList()
    }

    fun loadList(){
        viewModelScope.launch(Dispatchers.IO){
            _list.postValue(service.list())
        }
    }

    fun convert(currencyCode: String){
        viewModelScope.launch(Dispatchers.IO){
            val js = service.convert(currencyCode)
            val jsData = js.getAsJsonObject(currencyCode)
            val data : CurrencyData = Gson().fromJson(jsData,object : TypeToken<CurrencyData>(){}.type)
            _resp.postValue(data)
        }
    }

}

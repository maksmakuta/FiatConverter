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
import org.json.JSONObject

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
            val jsRaw = service.list().toString()
            val jsObj = JSONObject(jsRaw)
            val data = mutableMapOf<String,String>()
            jsObj.keys().forEach {
                val value = jsObj.getJSONObject(it)
                if(value.has("currency_name") && value.has("currency_code")) {
                    val name = value.getString("currency_name")
                    val code = value.getString("currency_code")
                    if (code.length == 3) {
                        data[code] = name.replaceFirstChar { c -> c.uppercase() }
                    }
                }
            }
            _list.postValue(data.toSortedMap())
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

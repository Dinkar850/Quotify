package com.example.quotify

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context: Context):ViewModel() {
    private var quoteList: Array<Quotes> = emptyArray()
    private var index = 0

    init {
        quoteList = loadQuoteFromAssets()
    }

    private fun loadQuoteFromAssets(): Array<Quotes> {
        //mainActivity se mainViewModel mei context pass karenge for quote.json ki unloading
        //and if kuch pass karna rehta hai in a viewmodel, we use viewmodelfactory
        val inputStream = context.assets.open("quotes.json")
        val size : Int = inputStream.available() //gives size of file ya inputstream
        val buffer = ByteArray(size)
        inputStream.read(buffer) // buffer stores the inputstream's bytearray
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8) //converting byteArray to string
        val gson = Gson() // making gson object
        return gson.fromJson(json, Array<Quotes>::class.java) //parsing json


    }
    fun getIndex() : Int = index
    fun getSize() : Int = quoteList.size
    fun getQuote() =  quoteList[index]
    fun nextQuote() = quoteList[++index]
    fun prevQuote() =  quoteList[--index]


}
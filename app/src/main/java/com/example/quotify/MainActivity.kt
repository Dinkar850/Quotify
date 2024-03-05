package com.example.quotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.quotify.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(application)).get(MainViewModel::class.java)
        binding.nextText.isEnabled = mainViewModel.getIndex() != mainViewModel.getSize() - 1
        setQuote(mainViewModel.getQuote())


    }

    private fun setQuote(quote: Quotes) {
        binding.quoteText.text = quote.text
        binding.quoteAuthor.text = quote.author
    }

    fun onPrevious(view: View) {
        if(mainViewModel.getIndex() == 1) binding.previousText.isEnabled = false
        setQuote(mainViewModel.prevQuote())
    }
    fun onNext(view: View) {
        binding.previousText.isEnabled = true
        setQuote(mainViewModel.nextQuote())
    }
    fun onShare(view: View) {
        //implicitIntenttosendmessage
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().text.toString())
        startActivity(intent)
    }
}
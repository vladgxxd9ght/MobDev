package com.example.laba3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textView = findViewById<TextView>(R.id.textView6)
        val receivedText = intent.getStringExtra("textremember") ?: ""
        textView.text = receivedText

        val buttonClose = findViewById<Button>(R.id.button2)
        val buttonShare = findViewById<Button>(R.id.buttonShare)
        val buttonSearch = findViewById<Button>(R.id.buttonSearch)

        buttonClose.setOnClickListener {
            finish()
        }

        buttonShare.setOnClickListener {
            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, receivedText)
            }
            val shareIntent = Intent.createChooser(sendIntent, "Поделиться через:")
            startActivity(shareIntent)
        }

        buttonSearch.setOnClickListener {
            val searchUri = Uri.parse("https://www.google.com/search?q=${Uri.encode(receivedText)}")
            val browserIntent = Intent(Intent.ACTION_VIEW, searchUri)
            startActivity(browserIntent)
        }
    }
}
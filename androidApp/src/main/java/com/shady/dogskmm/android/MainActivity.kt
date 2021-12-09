package com.shady.dogskmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shady.dogskmm.Greeting
import androidx.activity.compose.setContent
import androidx.compose.material.Text

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text(text = greet())
        }
    }
}

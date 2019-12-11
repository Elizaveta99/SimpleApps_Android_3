package com.android.calculator7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goButton(view: View) {
        val intent = Intent(this, CalculatorActivity::class.java)
        // start your next activity
        startActivity(intent)
    }
}

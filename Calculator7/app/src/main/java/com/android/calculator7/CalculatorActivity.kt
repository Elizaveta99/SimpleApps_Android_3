package com.android.calculator7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.calculator7.ui.calculator.CalculatorFragment

class CalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CalculatorFragment.newInstance())
                .commitNow()
        }
    }

}

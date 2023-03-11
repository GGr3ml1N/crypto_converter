package com.ggr3ml1n.cryptoconverter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ggr3ml1n.cryptoconverter.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val b = BaseImpl(10)
        Derived(b).print()
    }

    interface Base {
        fun print()
    }

    class BaseImpl(val x: Int) : Base {
        override fun print() { print(x) }
    }

    class Derived(b: Base) : Base by b
}
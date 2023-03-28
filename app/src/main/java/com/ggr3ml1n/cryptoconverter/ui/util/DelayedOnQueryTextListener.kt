package com.ggr3ml1n.cryptoconverter.ui.util

import android.os.Handler
import android.os.Looper
import android.text.Editable

abstract class DelayedOnQueryTextListener : TextWatcherAdapter() {

    override fun afterTextChanged(p0: Editable?) {
        Handler(Looper.getMainLooper()).postDelayed({
            onDelayerQueryTextChange(p0.toString())
        }, 300)
    }

    abstract fun onDelayerQueryTextChange(newText: String)
}
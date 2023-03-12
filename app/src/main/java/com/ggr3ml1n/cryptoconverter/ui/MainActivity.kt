package com.ggr3ml1n.cryptoconverter.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ggr3ml1n.cryptoconverter.R
import com.ggr3ml1n.cryptoconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!

    private var currentFragment : Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragment(FavoritesFragment.newInstance(), this)
    }

    private fun setFragment(newFrag: Fragment, activity: AppCompatActivity) {
        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame, newFrag)
        transaction.commit()
        currentFragment = newFrag
    }
}
package com.ggr3ml1n.cryptoconverter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ggr3ml1n.cryptoconverter.R
import com.ggr3ml1n.cryptoconverter.adapters.FavoritesAdapter
import com.ggr3ml1n.cryptoconverter.databinding.FragmentFavoritesBinding
import com.ggr3ml1n.cryptoconverter.model.CryptoModel

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private var adapter = FavoritesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }

    private fun init(){
        binding.rcFavorites.layoutManager = LinearLayoutManager(requireContext())
        binding.rcFavorites.adapter = adapter

        //Тут я добавил тестовую модельку для того, чтобы посмотреть как она отображается на RCView
        adapter.add(
            CryptoModel(
            R.drawable.sample_icon,
            "BTC",
            "Bitcoin",
            R.drawable.sample_graphic,
            "$ 29850.15",
            "1.12412 BTC",
            "54.21%"
        )
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoritesFragment()
    }
}
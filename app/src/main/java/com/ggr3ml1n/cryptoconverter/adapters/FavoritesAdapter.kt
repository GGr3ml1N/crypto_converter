package com.ggr3ml1n.cryptoconverter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ggr3ml1n.cryptoconverter.R
import com.ggr3ml1n.cryptoconverter.databinding.CryptoItemBinding
import com.ggr3ml1n.cryptoconverter.dataclasses.CryptoModel

class FavoritesAdapter : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    private val listOfCryptoModel = mutableListOf<CryptoModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavoritesViewHolder.create(parent)

    override fun getItemCount(): Int = listOfCryptoModel.size

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(listOfCryptoModel[position])
    }

    fun add(cryptoModel: CryptoModel) {
        listOfCryptoModel.add(cryptoModel)
        notifyItemInserted(listOfCryptoModel.size-1)
    }

    class FavoritesViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val binding = CryptoItemBinding.bind(item)

        fun bind(cryptoModel: CryptoModel) = with(binding) {
            imIcon.setImageResource(cryptoModel.iconImage)
            imGraphic.setImageResource(cryptoModel.graphImage)
            tvCost.text = cryptoModel.cost
            tvCryptoCost.text = cryptoModel.cryptoCost
            tvFullName.text = cryptoModel.fullName
            tvShortName.text = cryptoModel.shortName
            tvPercent.text = cryptoModel.percents
        }

        companion object {
            fun create(parent: ViewGroup) = FavoritesViewHolder(
                LayoutInflater
                    .from(parent.context)
                    .inflate(
                        R.layout.crypto_item,
                        parent,
                        false,
                    )
            )
        }
    }
}
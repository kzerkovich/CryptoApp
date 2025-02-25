package com.kzerk.cryptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.kzerk.cryptoapp.R
import com.kzerk.cryptoapp.databinding.ItemCoinInfoBinding
import com.kzerk.cryptoapp.domain.CoinInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context: Context) :
	ListAdapter<CoinInfo, CoinInfoViewHolder>(CoinInfoDiffCallback){

	var onCoinClickListener: OnCoinClickListener? = null

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
		val binding = ItemCoinInfoBinding.inflate(
			LayoutInflater.from(parent.context),
			parent,
			false
		)
		return CoinInfoViewHolder(binding)
	}

	override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
		val coin = getItem(position)
		with(holder.binding) {
			with(coin) {
				val symbolsTemplate = context.resources.getString(R.string.symbols_template)
				val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
				tvSymbols.text = String.format(symbolsTemplate, fromSymbol, toSymbol)
				tvPrice.text = price
				tvLastUpdate.text = String.format(lastUpdateTemplate, lastUpdate)
				Picasso.get().load(imageUrl).into(ivLogoCoin)
				root.setOnClickListener {
					onCoinClickListener?.onCoinClick(this)
				}
			}
		}
	}

	interface OnCoinClickListener {
		fun onCoinClick(coinPriceInfo: CoinInfo)
	}
}
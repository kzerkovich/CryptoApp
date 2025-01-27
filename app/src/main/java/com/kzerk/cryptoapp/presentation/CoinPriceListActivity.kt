package com.kzerk.cryptoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kzerk.cryptoapp.R
import com.kzerk.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.kzerk.cryptoapp.domain.CoinInfo
import com.kzerk.cryptoapp.presentation.adapters.CoinDetailFragment
import com.kzerk.cryptoapp.presentation.adapters.CoinInfoAdapter


class CoinPriceListActivity : AppCompatActivity() {

	private lateinit var viewModel: CoinViewModel
	private val binding by lazy {
		ActivityCoinPriceListBinding.inflate(layoutInflater)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		val adapter = CoinInfoAdapter(this)
		adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
			override fun onCoinClick(coinPriceInfo: CoinInfo) {
				if (isOnePaneMode()) {
					launchDetailActivity(coinPriceInfo.fromSymbol)
				}
				else {
					launchDetailFragment(coinPriceInfo.fromSymbol)
				}
			}
		}
		binding.rvCoinPriceList.adapter = adapter
		binding.rvCoinPriceList.itemAnimator = null
		viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
		viewModel.coinInfoList.observe(this) {
			adapter.submitList(it)
		}
	}

	private fun isOnePaneMode() = binding.fragmentContainer == null

	private fun launchDetailFragment(fromSymbol: String) {
		supportFragmentManager.popBackStack()
		supportFragmentManager
			.beginTransaction()
			.replace(R.id.fragment_container, CoinDetailFragment.newInstance(fromSymbol))
			.addToBackStack(null)
			.commit()
	}

	private fun launchDetailActivity(fromSymbol: String) {
		val intent = CoinDetailActivity.newIntent(
			this@CoinPriceListActivity,
			fromSymbol
		)
		startActivity(intent)
	}
}
package com.kzerk.cryptoapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.kzerk.cryptoapp.data.repository.CoinRepositoryImpl
import com.kzerk.cryptoapp.domain.GetCoinInfoListUseCase
import com.kzerk.cryptoapp.domain.GetCoinInfoUseCase
import com.kzerk.cryptoapp.domain.LoadDataUseCase

class CoinViewModel(application: Application) : AndroidViewModel(application) {

	private val repository = CoinRepositoryImpl(application)

	private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
	private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
	private val loadDataUseCase = LoadDataUseCase(repository)


	val coinInfoList = getCoinInfoListUseCase()

	fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)


	init {
		loadDataUseCase()
	}

}
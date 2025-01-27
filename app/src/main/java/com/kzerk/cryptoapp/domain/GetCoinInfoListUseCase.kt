package com.kzerk.cryptoapp.domain

class GetCoinInfoListUseCase(
	private val repository: CoinRepository
) {
	operator fun invoke() = repository.getCoinInfoList()
}
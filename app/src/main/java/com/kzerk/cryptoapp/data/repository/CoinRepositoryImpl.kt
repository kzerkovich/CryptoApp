package com.kzerk.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.kzerk.cryptoapp.data.database.AppDatabase
import com.kzerk.cryptoapp.data.mapper.CoinMapper
import com.kzerk.cryptoapp.data.workers.RefreshDataWorker
import com.kzerk.cryptoapp.domain.CoinInfo
import com.kzerk.cryptoapp.domain.CoinRepository

class CoinRepositoryImpl(
	private val application: Application
) : CoinRepository {

	private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()

	private val mapper = CoinMapper()

	override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
		return coinInfoDao.getPriceList().map {
			it.map {
				mapper.mapDbModelToEntity(it)
			}
		}
	}

	override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
		return coinInfoDao.getPriceInfoAboutCoin(fromSymbol).map {
			mapper.mapDbModelToEntity(it)
		}
	}

	override fun loadData() {
		val workManager = WorkManager.getInstance(application)
		workManager.enqueueUniqueWork(
			RefreshDataWorker.NAME,
			ExistingWorkPolicy.REPLACE,
			RefreshDataWorker.makeRequest()
		)
	}
}
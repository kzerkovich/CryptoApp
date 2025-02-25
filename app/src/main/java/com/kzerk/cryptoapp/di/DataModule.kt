package com.kzerk.cryptoapp.di

import android.app.Application
import com.kzerk.cryptoapp.data.database.AppDatabase
import com.kzerk.cryptoapp.data.database.CoinInfoDao
import com.kzerk.cryptoapp.data.network.ApiFactory
import com.kzerk.cryptoapp.data.network.ApiService
import com.kzerk.cryptoapp.data.repository.CoinRepositoryImpl
import com.kzerk.cryptoapp.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

	@Binds
	@ApplicationScope
	fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

	companion object {
		@Provides
		@ApplicationScope
		fun provideCoinInfoDao(
			application: Application
		): CoinInfoDao {
			return AppDatabase.getInstance(application).coinPriceInfoDao()
		}

		@Provides
		@ApplicationScope
		fun provideApiService(): ApiService {
			return ApiFactory.apiService
		}
	}
}
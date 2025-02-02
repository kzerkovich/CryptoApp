package com.kzerk.cryptoapp.di

import android.app.Application
import com.kzerk.cryptoapp.presentation.CoinApp
import com.kzerk.cryptoapp.presentation.CoinDetailFragment
import com.kzerk.cryptoapp.presentation.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
	modules = [
		DataModule::class,
		ViewModelModule::class,
		WorkerModule::class
	]
)
interface ApplicationComponent {

	fun inject(activity: CoinPriceListActivity)
	fun inject(fragment: CoinDetailFragment)

	fun inject(application: CoinApp)

	@Component.Factory
	interface Factory {
		fun create(
			@BindsInstance application: Application
		): ApplicationComponent
	}
}
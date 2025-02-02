package com.kzerk.cryptoapp.presentation

import android.app.Application
import androidx.work.Configuration
import com.kzerk.cryptoapp.data.workers.CoinWorkerFactory
import com.kzerk.cryptoapp.di.DaggerApplicationComponent
import javax.inject.Inject

class CoinApp: Application(), Configuration.Provider {

	@Inject
	lateinit var workerFactory: CoinWorkerFactory

	val component by lazy {
		DaggerApplicationComponent.factory().create(this)
	}

	override fun onCreate() {
		component.inject(this)
		super.onCreate()
	}

	override val workManagerConfiguration: Configuration
		get() = Configuration.Builder()
			.setWorkerFactory(workerFactory)
			.build()

}
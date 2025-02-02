package com.kzerk.cryptoapp.di

import androidx.work.ListenableWorker
import com.kzerk.cryptoapp.data.workers.ChildWorkerFactory
import com.kzerk.cryptoapp.data.workers.RefreshDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

	@Binds
	@IntoMap
	@WorkerKey(RefreshDataWorker::class)
	fun bindRefreshDataWorkerFactory(worker: RefreshDataWorker.Factory): ChildWorkerFactory
}
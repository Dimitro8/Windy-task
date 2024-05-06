package com.saventiy.windy_task.di.module

import com.saventiy.windy_task.flow.SummationFlowImpl
import com.saventiy.windy_task.flow.SummationFlowInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
internal open class AppModule {

    @Provides
    @ViewModelScoped
    fun provideSummationFlow(): SummationFlowImpl = SummationFlowImpl()
}
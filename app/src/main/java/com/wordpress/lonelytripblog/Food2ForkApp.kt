package com.wordpress.lonelytripblog

import android.app.Application
import com.wordpress.lonelytripblog.data.*
import com.wordpress.lonelytripblog.viewmodel.Food2ForkViewModel
import org.koin.android.ext.android.startKoin
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Food2ForkApp: Application() {

    private val appModule = module {
        single<RetrofitInterface> { Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(RetrofitInterface::class.java) }
        single { StringProvider(resources) }
        single<Food2ForkRepository> { Food2ForkRepositoryImpl(get(), get()) }

        viewModel { Food2ForkViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }

}
package com.kumagai.melisample.di

import com.google.gson.Gson
import com.kumagai.melisample.data.repository.MeliRepositoryImpl
import com.kumagai.melisample.data.source.MeliApi
import com.kumagai.melisample.domain.repository.MeliRepository
import com.kumagai.melisample.domain.use_case.SearchUseCase
import com.kumagai.melisample.domain.use_case.SearchUseCaseImpl
import com.kumagai.melisample.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { Gson() }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.mercadolibre.com").addConverterFactory(GsonConverterFactory.create(
                get()
            ))
            .build()
            .create(MeliApi::class.java)
    }

    single<MeliRepository> { MeliRepositoryImpl(get()) }

    single<SearchUseCase> { SearchUseCaseImpl(get()) }

    viewModel { SearchViewModel(get()) }
}
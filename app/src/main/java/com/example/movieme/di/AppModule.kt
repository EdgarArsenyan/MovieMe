package com.example.movieme.di

import com.example.data.api.MovieApi
import com.example.data.api.MovieApiRepoImpl
import com.example.data.util.AppConstants
import com.example.domain.repo.MovieRepository
import com.example.domain.useCase.GetByIdUseCase
import com.example.domain.useCase.GetByTitleUseCase
import com.example.movieme.viewModel.MovieViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    viewModel<MovieViewModel> {
        MovieViewModel(
            getByIdUseCase = get(),
            getByTitleUseCase = get()
        )
    }
}

val dataModule = module {

    single<MovieRepository> {
        MovieApiRepoImpl(movieApi = get())
    }
}

val serviceModule = module {
    single<MovieApi> {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .baseUrl(AppConstants.BASE_URL)
            .build().create(MovieApi::class.java)
    }
}

val okHttpModule = module {
    single {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }
}

val domainModule = module {
    factory<GetByTitleUseCase> {
        GetByTitleUseCase(movieRepo = get() )
    }

    factory<GetByIdUseCase> {
        GetByIdUseCase(movieRepo = get() )
    }
}
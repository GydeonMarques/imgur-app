package br.com.stefanini.imgur.di

import br.com.stefanini.imgur.BuildConfig
import br.com.stefanini.imgur.data.api.ImgurApi
import br.com.stefanini.imgur.data.interceptores.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModuleDI {

    private const val TIMEOUT_SECONDS = 30L

    @Provides
    fun retrofit(
        authInterceptor: AuthInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): Retrofit {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()

        return Retrofit
            .Builder()
            .client(httpClient)
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
    }

    @Provides
    fun imgurApi(retrofit: Retrofit): ImgurApi {
        return retrofit.create(ImgurApi::class.java)
    }

    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                setLevel(HttpLoggingInterceptor.Level.NONE)
            }
        }
    }

    @Provides
    fun authInterceptor(): AuthInterceptor {
        return AuthInterceptor()
    }
}
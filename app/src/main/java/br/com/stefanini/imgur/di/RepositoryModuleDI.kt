package br.com.stefanini.imgur.di

import br.com.stefanini.imgur.data.repositories.GetImageListRepositoryImpl
import br.com.stefanini.imgur.domain.repositories.GetImageListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModuleDI {

    @Binds
    fun getImageListRepository(repository: GetImageListRepositoryImpl): GetImageListRepository
}
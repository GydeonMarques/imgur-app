package br.com.stefanini.imgur.di

import br.com.stefanini.imgur.domain.usecases.GetImageListUseCase
import br.com.stefanini.imgur.domain.usecases.GetImageListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface UseCaseModuleDI {

    @Binds
    fun getImageListUseCase(useCase: GetImageListUseCaseImpl): GetImageListUseCase

}
package br.com.stefanini.imgur.domain.usecases

import br.com.stefanini.imgur.domain.models.DataWrapper
import br.com.stefanini.imgur.domain.models.GalleryModel
import br.com.stefanini.imgur.domain.repositories.GetImageListRepository
import javax.inject.Inject

internal class GetImageListUseCaseImpl @Inject constructor(
    private val repository: GetImageListRepository
) : GetImageListUseCase {

    override suspend fun execute(query: String): DataWrapper<List<GalleryModel>> {
        return repository.execute(query)
    }
}
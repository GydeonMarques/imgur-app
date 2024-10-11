package br.com.stefanini.imgur.domain.repositories

import br.com.stefanini.imgur.domain.models.DataWrapper
import br.com.stefanini.imgur.domain.models.GalleryModel

interface GetImageListRepository {
    suspend fun execute(query: String): DataWrapper<List<GalleryModel>>
}
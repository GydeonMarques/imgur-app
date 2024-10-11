package br.com.stefanini.imgur.data.repositories

import br.com.stefanini.imgur.data.api.ImgurApi
import br.com.stefanini.imgur.data.mappers.mapToModels
import br.com.stefanini.imgur.di.IoDispatcher
import br.com.stefanini.imgur.domain.models.DataWrapper
import br.com.stefanini.imgur.domain.models.GalleryModel
import br.com.stefanini.imgur.domain.repositories.GetImageListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class GetImageListRepositoryImpl @Inject constructor(
    private val api: ImgurApi,
    @IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : GetImageListRepository {

    override suspend fun execute(query: String): DataWrapper<List<GalleryModel>> {
        return withContext(dispatcher) {
            try {
                val response = api.search(query)
                when {
                    response.isSuccessful -> DataWrapper.Success(
                        data = response.body()?.data?.mapToModels() ?: emptyList()
                    )

                    response.errorBody() != null -> {
                        DataWrapper.Failure(
                            code = response.code(),
                            message = response.message(),
                        )
                    }

                    else -> DataWrapper.Failure(
                        code = response.code(),
                        message = response.errorBody()?.string(),
                    )
                }

            } catch (exception: Exception) {
                DataWrapper.Failure(
                    code = 0,
                    message = exception.message,
                )
            }
        }
    }
}
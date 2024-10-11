package br.com.stefanini.imgur.data.api

import br.com.stefanini.imgur.data.models.GalleryDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface ImgurApi {

    @GET("/3/gallery/search")
    suspend fun search(@Query("q") query: String): Response<GalleryDataResponse>
}
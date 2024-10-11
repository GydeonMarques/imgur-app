package br.com.stefanini.imgur.data.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty


@JsonIgnoreProperties(ignoreUnknown = true)
internal data class GalleryResponse(
    @JsonProperty("id") val id: String?,
    @JsonProperty("title") val title: String?,
    @JsonProperty("images") val images: List<ImageResponse>?
)
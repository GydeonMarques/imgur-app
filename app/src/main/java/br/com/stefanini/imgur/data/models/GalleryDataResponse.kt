package br.com.stefanini.imgur.data.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
internal data class GalleryDataResponse(
    @JsonProperty("data") val data: List<GalleryResponse>?,
)
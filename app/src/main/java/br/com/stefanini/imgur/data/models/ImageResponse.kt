package br.com.stefanini.imgur.data.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
internal data class ImageResponse(
    @JsonProperty("id") val id: String?,
    @JsonProperty("type") val type: String?,
    @JsonProperty("title") val title: String?,
    @JsonProperty("link") val imageUrl: String?
)
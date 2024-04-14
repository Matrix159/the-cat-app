package com.matrix159.thecatapp.core.data.model

import com.matrix159.thecatapp.core.domain.model.Image
import kotlinx.serialization.Serializable

/**
 * API model for image results from the cat API.
 */
@Serializable
data class ApiImage(
  val id: String,
  val width: Int,
  val height: Int,
  val url: String
) {
  fun toImage() = Image(
    id = id,
    width = width,
    height = height,
    url = url
  )
}
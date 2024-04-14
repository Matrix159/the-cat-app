package com.matrix159.thecatapp.core.domain.model

/**
 * Domain model for cat images.
 */
data class Image(
  val id: String,
  val width: Int,
  val height: Int,
  val url: String
)
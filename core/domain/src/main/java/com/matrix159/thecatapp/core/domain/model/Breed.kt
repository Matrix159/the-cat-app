package com.matrix159.thecatapp.core.domain.model

/**
 * Domain model for cat breeds.
 */
data class Breed(
  val id: String,
  val childFriendly: Int,
  val description: String,
  val dogFriendly: Int,
  val energyLevel: Int,
  val image: Image?,
  val name: String,
)

package com.matrix159.thecatapp.core.data.model

import com.matrix159.thecatapp.core.domain.model.Breed
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

/**
 * API model for breed list results from the cat API.
 */
@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class ApiBreed(
  val id: String,
  @JsonNames("child_friendly")
  val childFriendly: Int,
  val description: String,
  @JsonNames("dog_friendly")
  val dogFriendly: Int,
  @JsonNames("energy_level")
  val energyLevel: Int,
  val image: ApiImage?,
  val name: String,
) {
  fun toBreed(): Breed {
    return Breed(
      id = id,
      childFriendly = childFriendly,
      description = description,
      dogFriendly = dogFriendly,
      energyLevel = energyLevel,
      image = image?.toImage(),
      name = name,
    )
  }
}

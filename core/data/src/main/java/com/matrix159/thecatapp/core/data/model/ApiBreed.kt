package com.matrix159.thecatapp.core.data.model

import com.matrix159.thecatapp.core.domain.model.Breed
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class ApiBreed(
//  val adaptability: Int,
//  @JsonNames("affection_level")
//  val affectionLevel: Int,
//  @JsonNames("alt_names")
//  val altNames: String?,
//  @JsonNames("cfa_url")
//  val cfaUrl: String?,
//  @JsonNames("child_friendly")
//  val childFriendly: Int,
//  @JsonNames("country_code")
//  val countryCode: String,
//  @JsonNames("country_codes")
//  val countryCodes: String,
  val description: String,
//  @JsonNames("dog_friendly")
//  val dogFriendly: Int,
//  @JsonNames("energy_level")
//  val energyLevel: Int,
//  val experimental: Int,
//  val grooming: Int,
//  val hairless: Int,
//  @JsonNames("health_issues")
//  val healthIssues: Int,
//  val hypoallergenic: Int,
  val id: String,
  val image: ApiImage?,
//  val indoor: Int,
//  val intelligence: Int,
//  val lap: Int?,
//  @JsonNames("life_span")
//  val lifeSpan: String,
  val name: String,
//  val natural: Int,
//  val origin: String,
//  val rare: Int,
//  @JsonNames("reference_image_id")
//  val referenceImageId: String?,
//  val rex: Int,
//  @JsonNames("shedding_level")
//  val sheddingLevel: Int,
//  @JsonNames("short_legs")
//  val shortLegs: Int,
//  @JsonNames("social_needs")
//  val socialNeeds: Int,
//  @JsonNames("stranger_friendly")
//  val strangerFriendly: Int,
//  @JsonNames("suppressed_tail")
//  val suppressedTail: Int,
//  val temperament: String,
//  @JsonNames("vcahospitals_url")
//  val vcaHospitalsUrl: String?,
//  @JsonNames("vetstreet_url")
//  val vetStreetUrl: String?,
//  val vocalisation: Int,
//  val weight: ApiWeight,
//  @JsonNames("wikipedia_url")
//  val wikipediaUrl: String?,
) {
  fun toBreed(): Breed {
    return Breed(
//      adaptability = adaptability,
//      affectionLevel = affectionLevel,
//      altNames = altNames,
//      cfaUrl = cfaUrl,
//      childFriendly = childFriendly,
//      countryCode = countryCode,
//      countryCodes = countryCodes,
      description = description,
//      dogFriendly = dogFriendly,
//      energyLevel = energyLevel,
//      experimental = experimental,
//      grooming = grooming,
//      hairless = hairless,
//      healthIssues = healthIssues,
//      hypoallergenic = hypoallergenic,
      id = id,
      image = image?.toImage(),
//      indoor = indoor,
//      intelligence = intelligence,
//      lap = lap,
//      lifeSpan = lifeSpan,
      name = name,
//      natural = natural,
//      origin = origin,
//      rare = rare,
//      referenceImageId = referenceImageId,
//      rex = rex,
//      sheddingLevel = sheddingLevel,
//      shortLegs = shortLegs,
//      socialNeeds = socialNeeds,
//      strangerFriendly = strangerFriendly,
//      suppressedTail = suppressedTail,
//      temperament = temperament,
//      vcaHospitalsUrl = vcaHospitalsUrl,
//      vetStreetUrl = vetStreetUrl,
//      vocalisation = vocalisation,
//      weight = weight.toWeight(),
//      wikipediaUrl = wikipediaUrl,
    )
  }
}

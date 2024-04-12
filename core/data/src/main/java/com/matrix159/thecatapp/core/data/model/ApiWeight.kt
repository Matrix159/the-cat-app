package com.matrix159.thecatapp.core.data.model

import com.matrix159.thecatapp.core.domain.model.Weight
import kotlinx.serialization.Serializable

@Serializable
data class ApiWeight(
  val imperial: String,
  val metric: String
) {
  fun toWeight() = Weight(
    imperial = imperial,
    metric = metric
  )
}
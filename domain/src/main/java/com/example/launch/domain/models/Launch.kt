package com.example.launch.domain.models

import java.io.Serializable
import java.util.*

data class LaunchResponse(val docs: List<Launch>)

data class Launch(
    val id: String,
    val links: Link,
    val name: String,
    val cores: List<Flight>,
    val date_utc: Date,
    val details: String,
    val success: Boolean
) : Serializable

data class Link(
    val patch: Patch
)

data class Patch(
    val small: String,
    val large: String
)

data class Flight(
    val flight: Int
)
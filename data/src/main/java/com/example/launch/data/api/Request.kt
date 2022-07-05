package com.example.launch.data.api

import com.google.gson.annotations.SerializedName

data class Request(
    val query: Query,
    val options: Option
)

data class Query(
    val date_utc: DateUTC
)

data class Option(
    val limit: Int,
    val sort: SortDate
)

data class DateUTC(
    @SerializedName("${'$'}gte") val gte: String
)

data class SortDate(
    val date_utc: String
)
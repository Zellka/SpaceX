package com.example.launch.domain.models

data class Crew(
    val name: String,
    val agency: String,
    val status: String,
    val launches: List<String>,
    val image: String
)
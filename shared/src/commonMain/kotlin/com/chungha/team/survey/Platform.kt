package com.chungha.team.survey

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
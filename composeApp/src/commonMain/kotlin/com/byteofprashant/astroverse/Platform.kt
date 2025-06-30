package com.byteofprashant.astroverse

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
package com.hmmanit.android.cleanweather.mapper

interface Mapper<in FROM, out TO> {
    fun map(from: FROM): TO
}
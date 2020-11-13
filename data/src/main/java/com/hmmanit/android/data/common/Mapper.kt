package com.hmmanit.android.data.common

interface Mapper<in FROM, out TO> {
    fun map(from: FROM): TO
}
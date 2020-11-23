package com.hmmanit.android.domain.common

interface Mapper<in FROM, out TO> {
    fun map(from: FROM): TO
}
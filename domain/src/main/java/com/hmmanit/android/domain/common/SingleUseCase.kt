package com.hmmanit.android.domain.common

import io.reactivex.Scheduler
import io.reactivex.Single


abstract class SingleUseCase<T>(
    private val uiThread: Scheduler,
    private val executorThread: Scheduler
) {

    operator fun invoke(): Single<T> {
        return create().subscribeOn(executorThread).observeOn(uiThread)
    }

    protected abstract fun create(): Single<T>
}
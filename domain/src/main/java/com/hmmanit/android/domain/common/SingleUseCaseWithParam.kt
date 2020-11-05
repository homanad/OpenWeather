package com.hmmanit.android.domain.common

import io.reactivex.Scheduler
import io.reactivex.Single

abstract class SingleUseCaseWithParam<T, in Params>(
    private val uiThread: Scheduler,
    private val executorThread: Scheduler
) {

    operator fun invoke(params: Params): Single<T> {
        return create(params).subscribeOn(executorThread)
            .observeOn(uiThread)
    }

    protected abstract fun create(params: Params): Single<T>
}
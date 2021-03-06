package com.hmmanit.android.domain.common

import io.reactivex.Scheduler
import io.reactivex.Single

abstract class SingleUseCaseWithParam<T, in Param>(
    private val uiThread: Scheduler,
    private val executorThread: Scheduler
) {

    operator fun invoke(param: Param): Single<T> {
        return create(param).subscribeOn(executorThread)
            .observeOn(uiThread)
    }

    protected abstract fun create(param: Param): Single<T>
}
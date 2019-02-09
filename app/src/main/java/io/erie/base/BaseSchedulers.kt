package io.erie.base

import io.reactivex.Scheduler

interface BaseSchedulers {
    fun mainTread(): Scheduler
    fun backgroundThread(): Scheduler
}
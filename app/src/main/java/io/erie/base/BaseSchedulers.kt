package io.erie.base

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler

interface BaseSchedulers {
    fun ui(): Scheduler
    fun io(): Scheduler
    fun computation(): Scheduler
}

class AppSchedulers : BaseSchedulers {
    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
    override fun io(): Scheduler = Schedulers.io()
    override fun computation(): Scheduler = Schedulers.computation()
}

class TrampolineSchedulers : BaseSchedulers {
    override fun ui(): Scheduler = Schedulers.trampoline()
    override fun io(): Scheduler = Schedulers.trampoline()
    override fun computation(): Scheduler = Schedulers.trampoline()
}

class TestSchedulers(private val scheduler: TestScheduler) : BaseSchedulers {
    override fun ui(): Scheduler = scheduler
    override fun io(): Scheduler = scheduler
    override fun computation(): Scheduler = scheduler
}
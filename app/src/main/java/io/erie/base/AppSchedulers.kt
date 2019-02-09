package io.erie.base

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppSchedulers : BaseSchedulers {
    override fun mainTread(): Scheduler = AndroidSchedulers.mainThread()
    override fun backgroundThread(): Scheduler = Schedulers.io()
}
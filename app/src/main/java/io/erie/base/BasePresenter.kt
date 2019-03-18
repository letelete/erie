package io.erie.base

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<out V> {
    private var view: V? = null
    var compositeObservable = CompositeDisposable()

    fun getView(): V? = view

    @Suppress("UNCHECKED_CAST")
    fun attachView(view: Any?) {
        this.view = view as V?
    }

    open fun detachView() {
        view = null
        compositeObservable.clear()
    }
}
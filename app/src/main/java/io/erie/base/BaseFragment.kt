package io.erie.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import io.erie.commons.extensions.inflate
import javax.inject.Inject

abstract class BaseFragment<P : BasePresenter<Any>> : DaggerFragment() {

    @Inject
    lateinit var presenter: P

    protected var rootView: View? = null

    abstract fun getLayout(): Int
    abstract fun initialize()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = container?.inflate(getLayout())
        presenter.attachView(this)
        initialize()
        return rootView
    }
}
package io.erie.base

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import io.erie.R
import javax.inject.Inject

abstract class BaseActivity<P : BasePresenter<Any>> : DaggerAppCompatActivity() {

    @Inject
    lateinit var presenter: P

    abstract fun getLayout(): Int
    abstract fun initialize()

    override fun onCreate(savedInstanceState: Bundle?) {
        setupTheme()
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        presenter.attachView(this)
        initialize()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    private fun setupTheme() {
        setTheme(R.style.AppTheme_NoActionBar_Light)
    }
}
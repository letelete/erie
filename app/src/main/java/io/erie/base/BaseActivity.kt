package io.erie.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<P : BasePresenter<Any>> : AppCompatActivity() {

    @Inject
    lateinit var presenter: P

    protected abstract fun inject()
    protected abstract fun getLayout(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        initPresenter()
    }

    private fun initPresenter() {
        presenter.attachView(this)
        presenter.initialize()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}
package io.erie.base

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import io.erie.R
import javax.inject.Inject

abstract class BaseActivity<P : BasePresenter<Any>> : DaggerAppCompatActivity(), BaseView {

    @Inject
    lateinit var presenter: P

    abstract fun getLayout(): Int
    abstract fun initialize()
    abstract fun initializeThemeSelector()
    abstract var themeSelector: Int

    protected val themes: List<Int> = listOf(
        R.style.AppTheme_NoActionBar_Light,
        R.style.AppTheme_NoActionBar_Dark,
        R.style.AppTheme_NoActionBar_Amoled
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this)
        initializeThemeSelector()
        setupTheme()
        setContentView(getLayout())
        initialize()
    }

    private fun setupTheme() {
        val themeResId = themes[themeSelector]
        setTheme(themeResId)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun recreateActivity() {
        recreate()
    }

}
package io.erie.ui.allarticles

import com.google.android.material.chip.Chip
import io.erie.R
import io.erie.base.BaseFragment

class AllArticlesFragment : BaseFragment<AllArticlesPresenter>(),
    AllArticlesView, AllArticlesFilterDialogFragment.OnClickListener {

    override fun getLayout(): Int = R.layout.fragment_allarticles

    override fun initialize() {
    }

    override fun onLanguageClick(languagesChip: Chip) {
    }

    override fun onDateFromClick(dateFromChip: Chip) {
    }

    override fun onDateToClick(dateToChip: Chip) {
    }
}
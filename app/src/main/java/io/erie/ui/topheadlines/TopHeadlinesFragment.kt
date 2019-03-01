package io.erie.ui.topheadlines

import com.google.android.material.chip.Chip
import io.erie.R
import io.erie.base.BaseFragment

class TopHeadlinesFragment : BaseFragment<TopHeadlinesPresenter>(), TopHeadlinesView,
    TopHeadlinesFilterDialogFragment.OnClickListener {

    override fun getLayout(): Int = R.layout.fragment_topheadlines

    override fun initialize() {
    }

    override fun onCountryClick(languagesChip: Chip) {
    }

    override fun onCategoryClick(dateFromChip: Chip) {
    }
}
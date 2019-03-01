package io.erie.ui.topheadlines

import android.graphics.drawable.Drawable
import com.google.android.material.chip.Chip
import io.erie.R
import io.erie.base.BaseFilterDialogFragment
import kotlinx.android.synthetic.main.fragment_topheadlines_filterdialog.*
import kotlinx.android.synthetic.main.include_filterdialog_peekbar.view.*

class TopHeadlinesFilterDialogFragment : BaseFilterDialogFragment() {

    interface OnClickListener {
        fun onCountryClick(languagesChip: Chip)
        fun onCategoryClick(dateFromChip: Chip)
    }

    var listener: OnClickListener? = null

    override fun getLayout(): Int = R.layout.fragment_topheadlines_filterdialog

    override fun setPeekBarBackground() {
        val backgroundDrawable: Drawable? =
            context?.getDrawable(R.drawable.shape_filterdialog_topheadlines_peekbar)
        include_topHeadlines_peekBar?.view_filterDialog_background?.background = backgroundDrawable
    }

    override fun implementListeners() {
        include_topHeadlines_peekBar?.imageButton_filterDialog_closeDialog?.setOnClickListener {
            dismiss()
        }
        chipGroup_topHeadlines_countries?.setOnClickListener {
            listener?.onCountryClick(it as Chip)
        }
        chipGroup_topHeadlines_categories?.setOnClickListener {
            listener?.onCategoryClick(it as Chip)
        }
    }
}
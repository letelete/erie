package io.erie.ui.allarticles

import android.graphics.drawable.Drawable
import com.google.android.material.chip.Chip
import io.erie.R
import io.erie.base.BaseFilterDialogFragment
import kotlinx.android.synthetic.main.fragment_allarticles_filterdialog.*
import kotlinx.android.synthetic.main.include_filterdialog_peekbar.view.*

class AllArticlesFilterDialogFragment: BaseFilterDialogFragment() {

    interface OnClickListener {
        fun onLanguageClick(languagesChip: Chip)
        fun onDateFromClick(dateFromChip: Chip)
        fun onDateToClick(dateToChip: Chip)
    }

    var listener: OnClickListener? = null

    override fun getLayout(): Int = R.layout.fragment_allarticles_filterdialog

    override fun setPeekBarBackground() {
        val backgroundDrawable: Drawable? =
            context?.getDrawable(R.drawable.shape_filterdialog_allarticles_peekbar)
        include_allArticles_peekBar?.view_filterDialog_background?.background = backgroundDrawable
    }

    override fun implementListeners() {
        include_allArticles_peekBar?.imageButton_filterDialog_closeDialog?.setOnClickListener {
            dismiss()
        }
        chipGroup_allArticles_languages?.setOnClickListener {
            listener?.onLanguageClick(it as Chip)
        }
        chip_allArticles_dateFrom?.setOnClickListener {
            listener?.onDateFromClick(it as Chip)
        }
        chip_allArticles_dateTo?.setOnClickListener {
            listener?.onDateToClick(it as Chip)
        }
    }
}
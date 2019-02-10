package io.erie.ui.feed.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.erie.model.Post
import io.reactivex.subjects.PublishSubject
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_feed_nomoreposts.view.*

class FooterViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private companion object {
        const val MIN_ITEMS_AMOUNT_TO_USE_ELEVATOR = 10
    }

    fun bindView(items: List<Post>, subject: PublishSubject<Int>) {
        val showElevatorButton = items.count() >= MIN_ITEMS_AMOUNT_TO_USE_ELEVATOR

        containerView.floatingactionbutton_nomoreposts_scrolltotop.apply {
            if (showElevatorButton) {
                show()
                setOnClickListener {
                    subject.onNext(0)
                }
            } else {
                hide()
            }
        }
    }
}
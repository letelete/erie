package io.erie.ui.feed.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.erie.R
import io.erie.commons.extensions.inflate
import io.erie.model.Post
import io.erie.ui.feed.viewholders.FooterViewHolder
import io.erie.ui.feed.viewholders.PostsViewHolder
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class FeedAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var posts: List<Post> = emptyList()
    private val scrollToTopSubject = PublishSubject.create<Int>()
    val scrollToTopEvent: Observable<Int> = scrollToTopSubject

    private enum class HolderType(viewType: Int) {
        POST(0),
        FOOTER(1)
    }

    override fun getItemCount(): Int {
        val footer = 1
        return posts.count() + footer
    }

    override fun getItemViewType(position: Int): Int {
        val showFooterAsPlaceholder = posts.count() <= 0 && position == 0
        val showFooterAsLastItem = position == posts.count()

        return when {
            showFooterAsPlaceholder || showFooterAsLastItem -> HolderType.FOOTER.ordinal
            else -> HolderType.POST.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HolderType.FOOTER.ordinal -> {
                val view = parent.inflate(R.layout.item_feed_nomoreposts)
                FooterViewHolder(view)
            }
            else -> {
                val view = parent.inflate(R.layout.item_feed_post)
                PostsViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            HolderType.POST.ordinal -> {
                val postHolder = holder as PostsViewHolder
                postHolder.bindView()
            }
            HolderType.FOOTER.ordinal -> {
                val footerHolder = holder as FooterViewHolder
                footerHolder.bindView(items = posts, subject = scrollToTopSubject)
            }
        }
    }

    fun updatePostsData(posts: List<Post>) {
        this.posts = posts
        notifyDataSetChanged()
    }
}

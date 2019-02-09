package io.erie.ui.feed

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.erie.R
import io.erie.commons.extensions.inflate
import io.erie.model.Post
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.post_item.view.*
import javax.inject.Inject

class FeedAdapter @Inject constructor() : RecyclerView.Adapter<FeedAdapter.ErieViewHolder>() {

    private val clickItemSubject = PublishSubject.create<Post>()
    val clickItemEvent: Observable<Post> = clickItemSubject

    private var posts: List<Post> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ErieViewHolder {
        val view: View = parent.inflate(R.layout.post_item)
        return ErieViewHolder(view)
    }

    override fun onBindViewHolder(holder: ErieViewHolder, position: Int) {
        holder.bindPost(posts[position])
    }

    inner class ErieViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        init {
            containerView.setOnClickListener {
                clickItemSubject.onNext(posts[layoutPosition])
            }
        }

        fun bindPost(post: Post) {
            containerView.textview_post_title.text = post.title.capitalize()
            containerView.textview_post_body.text = post.body.capitalize()
        }
    }

    override fun getItemCount(): Int = posts.count()

    fun updatePostsData(posts: List<Post>) {
        this.posts = posts
        notifyDataSetChanged()
    }
}

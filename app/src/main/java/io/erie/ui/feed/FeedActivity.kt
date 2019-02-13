package io.erie.ui.feed

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import io.erie.R
import io.erie.base.BaseActivity
import io.erie.model.Post
import io.erie.ui.feed.adapters.FeedAdapter
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_feed.*
import javax.inject.Inject

class FeedActivity : BaseActivity<FeedPresenter>(), FeedContract.View {

    @Inject
    lateinit var feedAdapter: FeedAdapter

    private var scrollToTopDisposable: Disposable? = null

    override fun inject() {
        AndroidInjection.inject(this)
    }

    override fun getLayout(): Int = R.layout.activity_feed

    override fun initializeView() {
        setSupportActionBar(toolbar_feed_logo)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        recyclerview_feed_posts.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = feedAdapter
        }

        swiperefreshlayout_feed_updateposts.setOnRefreshListener {
            presenter.fetchPosts()
        }

        scrollToTopDisposable = feedAdapter
            .scrollToTopEvent.subscribe { recyclerview_feed_posts.smoothScrollToPosition(0) }

        presenter.fetchPosts()
    }

    override fun showLoading() {
        if (!swiperefreshlayout_feed_updateposts.isRefreshing) {
            swiperefreshlayout_feed_updateposts.isRefreshing = true
        }
    }

    override fun hideLoading() {
        if (swiperefreshlayout_feed_updateposts.isRefreshing) {
            swiperefreshlayout_feed_updateposts.isRefreshing = false
        }
    }

    override fun showPostsList(posts: List<Post>) {
        feedAdapter.updatePostsData(posts)
    }

    override fun showPostsFetchingError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }
}




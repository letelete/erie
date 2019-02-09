package io.erie.ui.feed

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import io.erie.R
import io.erie.base.BaseActivity
import io.erie.model.Post
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class FeedActivity : BaseActivity<FeedPresenter>(), FeedContract.View {

    @Inject
    lateinit var feedAdapter: FeedAdapter

    private var postClickedSubscription: Disposable? = null

    override fun inject() {
        AndroidInjection.inject(this)
    }

    override fun getLayout(): Int = R.layout.activity_main

    override fun initializeView() {
        recyclerview_main_posts.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = feedAdapter
        }

        postClickedSubscription = feedAdapter.clickItemEvent
            .subscribe { presenter.showPostComments(it) }

        swiperefreshlayout_main_updateposts.setOnRefreshListener {
            presenter.fetchPosts()
        }

        presenter.fetchPosts()
    }

    override fun showLoading() {
        if (!swiperefreshlayout_main_updateposts.isRefreshing) {
            swiperefreshlayout_main_updateposts.isRefreshing = true
        }
    }

    override fun hideLoading() {
        if (swiperefreshlayout_main_updateposts.isRefreshing) {
            swiperefreshlayout_main_updateposts.isRefreshing = false
        }
    }

    override fun showPostsList(posts: List<Post>) {
        feedAdapter.updatePostsData(posts)
    }

    override fun showPostsFetchingError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }
}




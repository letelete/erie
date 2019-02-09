package io.erie.ui.feed

import io.erie.model.Post

interface FeedContract {

    interface View {
        fun initializeView()
        fun showPostsList(posts: List<Post>)
        fun showPostsFetchingError(errorMessage: String)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        fun fetchPosts()
        fun showPostComments(post: Post)
    }
}
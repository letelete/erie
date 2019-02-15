package io.erie.ui.feed

import io.erie.base.TrampolineSchedulers
import io.erie.model.Post
import io.erie.network.ApiService
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class FeedPresenterTest {

    @Mock
    lateinit var view: FeedContract.View

    @Mock
    lateinit var apiService: ApiService

    private lateinit var presenter: FeedPresenter
    private val trampolineSchedulersProvider: TrampolineSchedulers = TrampolineSchedulers()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = FeedPresenter(trampolineSchedulersProvider, apiService)
        presenter.attachView(view)
    }

    @Test
    fun initialize() {
        presenter.initialize()

        verify(view).initializeView()
    }

    @Test
    fun `when fetching posts, should show loading animation`() {
        `when`(apiService.posts()).thenReturn(Observable.just(emptyList()))
        presenter.fetchPosts()

        then(view).should().showLoading()
    }

    @Test
    fun `when posts fetched, should hide loading animation`() {
        `when`(apiService.posts()).thenReturn(Observable.just(emptyList()))
        presenter.fetchPosts()

        then(view).should().hideLoading()

    }

    @Test
    fun `when posts fetch success, should show posts list`() {
        val posts = listOf(Post("Body", 0, "title", 0))

        `when`(apiService.posts()).thenReturn(Observable.just(posts))
        presenter.fetchPosts()

        then(view).should().showPostsList(posts)
    }

    @Test
    fun `when posts fetch error, should show the error message`() {
        val errorMessage = "500"

        `when`(apiService.posts()).thenReturn(Observable.error(Exception(errorMessage)))
        presenter.fetchPosts()

        then(view).should().showPostsFetchingError(errorMessage)
    }

    @Test
    fun detachView() {
        presenter.detachView()
        verify(view).hideLoading()
    }
}
package io.erie.repository

import androidx.paging.PagedList
import io.reactivex.Observable

data class Listing<T>(val pagedList: Observable<PagedList<T>>)
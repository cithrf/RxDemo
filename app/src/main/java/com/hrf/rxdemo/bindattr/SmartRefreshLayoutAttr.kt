package com.hrf.rxdemo.bindattr

import androidx.databinding.BindingAdapter
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener

@BindingAdapter(value = ["onRefreshListener", "onLoadMoreListener"], requireAll = false)
fun bindListener(
    smartLayout: SmartRefreshLayout,
    refreshListener: OnRefreshListener?,
    loadMoreListener: OnLoadMoreListener?
) {
    smartLayout.setOnRefreshListener(refreshListener)
    smartLayout.setOnLoadMoreListener(loadMoreListener)
}

@BindingAdapter(value = ["refreshing", "moreLoading", "hasMore"], requireAll = false)
fun bindSmartRefreshLayout(
    smartLayout: SmartRefreshLayout,
    refreshing: Boolean,
    moreLoading: Boolean,
    hasMore: Boolean

) {
    if (refreshing) smartLayout.finishRefresh()
    if (moreLoading) smartLayout.finishLoadMore()
    smartLayout.setEnableLoadMore(hasMore)
}
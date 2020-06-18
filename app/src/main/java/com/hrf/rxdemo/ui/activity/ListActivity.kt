package com.hrf.rxdemo.ui.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.hrf.rxdemo.R
import com.hrf.rxdemo.base.BaseActivity
import com.hrf.rxdemo.bean.HomeBeam
import com.hrf.rxdemo.databinding.ActivityListBinding
import com.hrf.rxdemo.ui.adapter.ListAdapter
import com.hrf.rxdemo.viewmodel.ListViewModel
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : BaseActivity(), OnRefreshListener, OnLoadMoreListener {

    private var page : Int = 0
    private lateinit var binding: ActivityListBinding
    private lateinit var model : ListViewModel
    private lateinit var adapter: ListAdapter
    lateinit var list : ArrayList<HomeBeam>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_list)
        binding.loadMoreListener = this
        binding.refreshListener = this
        initData()
    }

    private fun initData(){
        list = arrayListOf()
        model = createVModel(ListViewModel::class.java)
        adapter = ListAdapter()
        adapter.submitList(list)
        recyclerView.adapter = adapter
        binding.model = model
        model.liveData.observe(this, Observer {
            if (page == 0){
                list.clear()
                refreshLayout.setEnableLoadMore(true)
            }else{
                if (it.isNullOrEmpty() || it.size != 20){
                    refreshLayout.setEnableLoadMore(false)
                }
            }
            list.addAll(it)
            adapter.submitList(list)
            binding.model = this@ListActivity.model
            adapter.notifyDataSetChanged()
        })
        refreshLayout.autoRefresh()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        page = 0
        model.getList(page)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        page++
        model.getList(page)
    }

    override fun onError(t: String) {
        super.onError(t)
        model.finishRefreshorLoadData = true
        binding.model = this@ListActivity.model
        refreshLayout.setEnableLoadMore(false)
    }

}
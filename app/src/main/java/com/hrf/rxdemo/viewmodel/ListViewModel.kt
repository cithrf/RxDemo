package com.hrf.rxdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import com.hrf.rxdemo.base.BaseViewModel
import com.hrf.rxdemo.bean.HomeBeam
import com.hrf.rxdemo.bean.HomeListBean
import com.hrf.rxdemo.model.MainModel

class ListViewModel : BaseViewModel() {

    //是否刷新完成(刷新、加载)
    var finishRefreshorLoadData = false
    //是否有更多数据
    var hasMoreData = true
    private val model : MainModel by lazy { MainModel() }
    val liveData: MutableLiveData<List<HomeBeam>> = MutableLiveData()

    fun getList(page : Int){
        //RxJava
        finishRefreshorLoadData = false
        model.getList(page).onResult {
            finishRefreshorLoadData = true
            if (it.data == null || it.data!!.datas.isNullOrEmpty()){
                liveData.value = arrayListOf()
            }else{
                liveData.value = it.data!!.datas
            }
        }
    }

}
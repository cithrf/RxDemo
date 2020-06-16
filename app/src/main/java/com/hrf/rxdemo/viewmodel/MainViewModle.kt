package com.hrf.rxdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import com.hrf.rxdemo.base.BaseViewModel
import com.hrf.rxdemo.bean.BannerBean
import com.hrf.rxdemo.bean.HomeListBean
import com.hrf.rxdemo.model.MainModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.await

class MainViewModle : BaseViewModel() {

    private val model : MainModel by lazy { MainModel() }
    val liveData: MutableLiveData<BannerBean> = MutableLiveData()
    val liveData2: MutableLiveData<HomeListBean> = MutableLiveData()

    fun getList(){
        //RxJava
        model.getList(0).onResult {
            liveData2.value = it
        }

    }

    fun getBannerList(){
        //协程
        GlobalScope.launch {
            val bean = model.getBannerList().await()
            liveData.postValue(bean)
        }

    }
}
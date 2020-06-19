package com.hrf.rxdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import com.hrf.rxdemo.base.BaseViewModel
import com.hrf.rxdemo.bean.BannerBean
import com.hrf.rxdemo.bean.HomeListBean
import com.hrf.rxdemo.model.MainModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.await

class MainViewModle : BaseViewModel() {

    private val model : MainModel by lazy { MainModel() }
    val liveData: MutableLiveData<BannerBean> = MutableLiveData()
    val liveData2: MutableLiveData<HomeListBean> = MutableLiveData()

    fun getList(){
        //RxJava
        model.getList(0).onResult {
            //做点其他的事情
            //省略其他逻辑代码
            liveData2.value = it
        }

        //没有其他逻辑，直接返回数据
        model.getList(0).onResult(liveData2::setValue)

    }

    fun getBannerList(){
        //协程
        GlobalScope.launch {
            val bean = model.getBannerList().await()
            liveData.postValue(bean)
        }

    }
}
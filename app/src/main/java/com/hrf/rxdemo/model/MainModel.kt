package com.hrf.rxdemo.model

import com.hrf.rxdemo.base.BaseBean
import com.hrf.rxdemo.base.BaseModel
import com.hrf.rxdemo.bean.BannerBean
import com.hrf.rxdemo.bean.HomeListBean
import io.reactivex.Observable
import retrofit2.Call

class MainModel : BaseModel() {

    fun getList(page: Int) : Observable<HomeListBean>{
        return apiService.getList(page)
    }

    fun getBannerList(): Call<BannerBean>{
        return apiService.getBannerList()
    }

    //示例
    //post提交json（对参数进行哈希排序）
    fun postMsg(v1 : String,v2 : Int) : Observable<BaseBean>{
        val map = getMap()
        map["v1"] = v1
        map["v2"] = "$v2"
        return apiService.postMsg(map.toJson().getRequestBody())
    }
}
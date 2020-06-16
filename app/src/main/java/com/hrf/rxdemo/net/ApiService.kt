package com.hrf.rxdemo.net

import com.hrf.rxdemo.base.BaseBean
import com.hrf.rxdemo.bean.BannerBean
import com.hrf.rxdemo.bean.HomeListBean
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    companion object{
        const val BASE_URL = "https://www.wanandroid.com/"
    }

    @GET("article/list/{page}/json")
    fun getList(
        @Path("page") page: Int
    ) : Observable<HomeListBean>

    @GET("banner/json")
    fun getBannerList(): Call<BannerBean>

    @POST("postxxx/xxx")
    fun postMsg(@Body json : RequestBody) : Observable<BaseBean>
}
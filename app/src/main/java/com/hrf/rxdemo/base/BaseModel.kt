package com.hrf.rxdemo.base

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hrf.rxdemo.net.ApiService
import com.hrf.rxdemo.net.RetrofitManager
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.*

open class BaseModel {

    val apiService : ApiService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        RetrofitManager.create(ApiService::class.java)
    }

    val gson : Gson by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        GsonBuilder().disableHtmlEscaping().create()
    }

    fun getMap(): TreeMap<String, String> {
        return TreeMap(Comparator { o1, o2 ->
            o1.compareTo(o2) //用正负表示大小值
        })
    }


    fun TreeMap<String,String>.toJson() : String{
        return gson.toJson(this)
    }

    fun String.getRequestBody() : RequestBody {
        return this.toRequestBody("application/json;charset=UTF-8".toMediaType())
    }


}
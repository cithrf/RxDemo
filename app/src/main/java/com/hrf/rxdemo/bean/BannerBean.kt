package com.hrf.rxdemo.bean

import com.hrf.rxdemo.base.BaseBean

data class BannerBean(
    var data: List<DataBean>?
) : BaseBean()

data class DataBean(
    var id: String?,
    var title: String?,
    var desc: String?,
    var type: String?,
    var order: String?,
    var url: String?,
    var imagePath:String?
)
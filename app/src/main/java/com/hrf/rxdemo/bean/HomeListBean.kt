package com.hrf.rxdemo.bean

import com.hrf.rxdemo.base.BaseBean


data class HomeListBean(
    var data : HomeMsgBean?
) : BaseBean()

data class HomeMsgBean(
    var datas : List<HomeBeam>?
)

data class HomeBeam(
    var title:String?,
    var link : String?,
    var id : String?,
    var niceDate : String?,
    var niceShareDate : String?,
    var shareUser : String?,
    var superChapterId : String?
)
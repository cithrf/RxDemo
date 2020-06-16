package com.hrf.rxdemo.bindattr

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter


@SuppressLint("SetTextI18n")
@BindingAdapter("toJson")
fun beanToJson(view : TextView, any: String){
    view.text = "请求结果:$any"
}
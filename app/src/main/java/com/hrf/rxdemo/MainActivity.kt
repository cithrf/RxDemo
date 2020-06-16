package com.hrf.rxdemo

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.hrf.rxdemo.base.BaseActivity
import com.hrf.rxdemo.databinding.ActivityMainBinding
import com.hrf.rxdemo.viewmodel.MainViewModle

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var modle: MainViewModle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        initData()
    }

    private fun initData(){
        modle = createVModel(MainViewModle::class.java)

        modle.liveData.observe(this, Observer {
            binding.str = Gson().toJson(it)
        })

        modle.liveData2.observe(this, Observer {
            binding.str = Gson().toJson(it)
        })

        binding.model = this@MainActivity.modle
    }
}
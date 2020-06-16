package com.hrf.rxdemo.base

import android.text.TextUtils
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

open class BaseActivity : AppCompatActivity() {

    @MainThread
    fun <VM : BaseViewModel> createVModel(clazz: Class<VM>): VM {
        val viewModel = ViewModelProvider(this)[clazz]
        lifecycle.addObserver(viewModel)
        bindErrorData(viewModel)
        return viewModel
    }

    private fun bindErrorData(vm : BaseViewModel){
        vm.errorLiveData.observe(this, Observer {
            onError(it)
        })
    }

    open fun onError(t:String){
        if (!TextUtils.isEmpty(t)){
            Toast.makeText(this,t,Toast.LENGTH_SHORT).show()
        }
    }
}
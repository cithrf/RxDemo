package com.hrf.rxdemo.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

open class BaseViewModel : ViewModel(),IViewModel {

    val errorLiveData: MutableLiveData<String> = MutableLiveData()
    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(owner: LifecycleOwner) {
        //创建
    }

    override fun onDestroy(owner: LifecycleOwner) {
        //销毁
        detachView()
        //移除生命周期监听观察者
        owner.lifecycle.removeObserver(this)
    }

    override fun onLifecycleChanged(owner: LifecycleOwner, event: Lifecycle.Event) {
        //生命周期状态改变
    }

    //泛型可以为 <T : BaseBean> ，也可以为 <T : List<BaseBean>>
    //此处为Observable的拓展函数，你也可以改为Flowable的拓展函数
    fun <T : BaseBean> Observable<T>.onResult(
        next: Consumer<T>,
        error: Consumer<Throwable> = Consumer {
            errorLiveData.postValue(it.message)
        }
    ) {
        val disposable = this.subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(next, error)
        addSubscription(disposable)
    }

    private infix fun <T : BaseBean> Observable<T>.onResult(
        next: Consumer<T>
    ) {
        this.onResult(next,Consumer {
            errorLiveData.postValue(it.message)
        })
    }

    fun <T : BaseBean> Observable<T>.onResult(
        next : (T) -> Unit
    ){
        this onResult Consumer {
            //这里进行返回判断
            /*if (!TextUtils.equals(it.errorCode,"200")){
                errorLiveData.value = it.errorMsg
                return@Consumer
            }*/
            next(it)
        }
    }


    private fun addSubscription(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private fun detachView() {
        //保证activity结束时取消所有正在执行的订阅
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }
}
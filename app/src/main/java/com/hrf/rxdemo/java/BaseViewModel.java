package com.hrf.rxdemo.java;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hrf.rxdemo.base.BaseBean;
import com.hrf.rxdemo.base.IViewModel;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

//Java版
public class BaseViewModel extends ViewModel implements IViewModel {

    public boolean finishRefreshorLoadData = true;
    public MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onCreate(@NotNull LifecycleOwner owner) {

    }

    @Override
    public void onDestroy(@NotNull LifecycleOwner owner) {
        detachView();
        owner.getLifecycle().removeObserver(this);
    }

    @Override
    public void onLifecycleChanged(@NotNull LifecycleOwner owner, @NotNull Lifecycle.Event event) {

    }

    public <T extends BaseBean> void onResult(
            Observable<T> observable,
            Consumer<T> next,
            Consumer<Throwable> error
    ){
        Disposable disposable = observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next, error);
        compositeDisposable.add(disposable);
    }

    public <T extends BaseBean> void onResult(
            Observable<T> observable,
            Consumer<T> next
    ){
        onResult(observable,next,throwable -> {
            errorLiveData.postValue(throwable);
        });
    }
    private void detachView() {
        //保证activity结束时取消所有正在执行的订阅
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }

}

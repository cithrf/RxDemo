package com.hrf.rxdemo.java;

import androidx.lifecycle.MutableLiveData;

import com.hrf.rxdemo.bean.HomeListBean;
import com.hrf.rxdemo.model.MainModel;


public class JavaViewModel extends BaseViewModel{

    private MainModel model = new MainModel();
    public MutableLiveData<HomeListBean> liveData = new MutableLiveData<>();

    public void getList(){
        onResult(model.getList(0),homeListBean -> {
            //做点其他事情
            liveData.setValue(homeListBean);
        });

        //没有其他操作，直接放回数据
        onResult(model.getList(0),liveData::setValue);
    }
}

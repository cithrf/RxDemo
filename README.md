# RxDemo
RxJava也能像协程那样优雅的请求网络

示例:

fun getList(page : Int){

        //RxJava2、Retrofit2
        
        model.getList(page).onResult {
            //网络请求回调
        }
        
    }

#MVVM
rxjava2+retrofit2+viewmodel+livedata+lifecycles

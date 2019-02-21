package com.forgetsky.wanandroid.core.rx;

import android.text.TextUtils;

import com.forgetsky.wanandroid.R;
import com.forgetsky.wanandroid.app.WanAndroidApp;
import com.forgetsky.wanandroid.base.view.IView;
import com.forgetsky.wanandroid.core.http.exception.ServerException;

import io.reactivex.observers.ResourceObserver;
import retrofit2.HttpException;


public abstract class BaseObserver<T> extends ResourceObserver<T> {

    private IView mView;
    private String mErrorMsg;
    private boolean isShowError = true;

    protected BaseObserver(IView view){
        this.mView = view;
    }

    protected BaseObserver(IView view, String errorMsg){
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    protected BaseObserver(IView view, boolean isShowError){
        this.mView = view;
        this.isShowError = isShowError;
    }

    protected BaseObserver(IView view, String errorMsg, boolean isShowError){
        this.mView = view;
        this.mErrorMsg = errorMsg;
        this.isShowError = isShowError;
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        if (mView == null) {
            return;
        }
        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
            mView.showErrorMsg(mErrorMsg);
        } else if (e instanceof ServerException) {
            mView.showErrorMsg(e.toString());
        } else if (e instanceof HttpException) {
                mView.showErrorMsg(WanAndroidApp.getContext().getString(R.string.http_error));
        } else {
            mView.showErrorMsg(WanAndroidApp.getContext().getString(R.string.unKnown_error));
//            LogHelper.d(e.toString());
        }
        if (isShowError) {
//            mView.showError();
        }
    }
}

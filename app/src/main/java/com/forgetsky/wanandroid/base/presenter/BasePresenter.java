package com.forgetsky.wanandroid.base.presenter;

import com.forgetsky.wanandroid.base.view.IView;
import com.forgetsky.wanandroid.core.DataManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<T extends IView> implements IPresenter<T> {

    protected T mView;

    @Inject
    public DataManager mDataManager;

    private CompositeDisposable compositeDisposable;

    @Override
    public void attachView(T view) {
        this.mView = view;

    }

    @Override
    public void detachView() {
        this.mView = null;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }

    }

    @Override
    public int getCurrentPage() {
//        return mDataManager.getCurrentPage();
        return 0;
    }

    protected void addSubscribe(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }
}

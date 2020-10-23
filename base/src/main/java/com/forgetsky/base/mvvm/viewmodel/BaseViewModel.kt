package com.forgetsky.base.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by ForgetSky on 20-10-21.
 */
abstract class BaseViewModel : ViewModel(){
    open fun refresh() {}

    fun loadMore() {}
}
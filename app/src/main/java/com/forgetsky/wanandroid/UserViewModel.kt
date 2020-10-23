package com.forgetsky.wanandroid

import androidx.lifecycle.MutableLiveData
import com.forgetsky.base.mvvm.viewmodel.BaseViewModel

/**
 * Created by ForgetSky on 20-10-19.
 */
class UserViewModel : BaseViewModel() {
    val userLiveData : MutableLiveData<UserBean> by lazy {
        MutableLiveData<UserBean>()
    }
    private var age = 20
    val userBean = UserBean("adfasdg", age)

    override fun refresh() {
        userBean.name = userBean.name + age + ","
        userBean.age = age++
        userLiveData.postValue(userBean)
    }
}
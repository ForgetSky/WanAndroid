package com.forgetsky.base.mvvm.view

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.forgetsky.base.mvvm.viewmodel.BaseViewModel

/**
 * Created by ForgetSky on 20-10-21.
 */
abstract class BaseMvvmActivity<V : ViewDataBinding, VM : BaseViewModel> :
    BaseActivity() {
    lateinit var viewModel: VM
    lateinit var viewDataBinding: V

    override fun initContentView() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        setContentView(getLayoutId())
    }

    override fun onViewCreated() {
        viewModel = initViewModel()
        createDataObserver()
        super.onViewCreated()
    }

    abstract fun initViewModel(): VM

    abstract fun createDataObserver()

}

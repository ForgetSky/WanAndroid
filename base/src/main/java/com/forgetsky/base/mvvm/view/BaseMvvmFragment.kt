package com.forgetsky.base.mvvm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.forgetsky.base.mvvm.viewmodel.BaseViewModel

/**
 * Created by ForgetSky on 20-10-22.
 */
abstract class BaseMvvmFragment<V : ViewDataBinding, VM : BaseViewModel> :
    BaseFragment(){
    lateinit var viewModel: VM
    lateinit var viewDataBinding: V

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        viewModel = initViewModel()
        initDataObserver()
        initData()
    }

    abstract fun initViewModel(): VM

    abstract fun initDataObserver()

}
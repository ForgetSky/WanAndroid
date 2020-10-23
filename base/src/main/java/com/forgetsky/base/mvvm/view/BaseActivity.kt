package com.forgetsky.base.mvvm.view

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by ForgetSky on 20-10-21.
 */
abstract class BaseActivity : AppCompatActivity() {
    @LayoutRes
    abstract fun getLayoutId(): Int

    protected open fun initContentView() {
        setContentView(getLayoutId())
    }

    protected open fun onViewCreated() {
        initData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContentView()
        initView()
        onViewCreated()
    }

    abstract fun initView()

    abstract fun initData()
}
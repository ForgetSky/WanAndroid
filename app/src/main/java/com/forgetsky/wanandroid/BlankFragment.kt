package com.forgetsky.wanandroid

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.forgetsky.base.mvvm.view.BaseMvvmFragment
import com.forgetsky.wanandroid.databinding.FragmentBlankBinding
import kotlinx.android.synthetic.main.fragment_blank.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BlankFragment : BaseMvvmFragment<FragmentBlankBinding, UserViewModel>() {

    override fun initViewModel(): UserViewModel {
        val userViewModel: UserViewModel by viewModels()
        return userViewModel
    }

    override fun initDataObserver() {
        val observer = Observer<UserBean>() {
//            user_name.text = it.name
//            user_age.text = it.age.toString()
            viewDataBinding.user = it
        }
        viewModel.userLiveData.observe(viewLifecycleOwner, observer)
    }

    override fun initData() {
        viewModel.refresh()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_blank
    }

    override fun initView() {
        jump.setOnClickListener {
           val action =
                BlankFragmentDirections.actionBlankFragmentToBlankFragment2()
//            this.findNavController().navigate(action)
            viewModel.refresh()
        }
    }

}
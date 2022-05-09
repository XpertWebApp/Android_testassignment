package com.c2c.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.app.peyza.R
import com.kaopiz.kprogresshud.KProgressHUD


abstract class BaseFragment<ViewBinding : ViewDataBinding> :
    Fragment() {
    @LayoutRes
    abstract fun getFragmentLayoutResId(): Int

    lateinit var vBinding: ViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<ViewBinding>(
            inflater,
            getFragmentLayoutResId(),
            container,
            false
        )
        return binding.root

    }

    private fun progressBar(): KProgressHUD {
        return KProgressHUD.create(requireContext())
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setLabel(getString(R.string.loading_please_wait))
            .setCancellable(false)
            .setAnimationSpeed(2)
            .setDimAmount(0.5f)
    }

    protected val progressBar: KProgressHUD by lazy { progressBar() }
    protected val binding: ViewBinding by lazy {
        vBinding
    }
}
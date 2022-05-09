package com.app.peyza.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.app.peyza.R
import com.app.peyza.helper.LocaleHelper
import com.kaopiz.kprogresshud.KProgressHUD


abstract class BaseActivity<ViewBinding : ViewDataBinding, ViewModel : androidx.lifecycle.ViewModel> :
    AppCompatActivity() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocaleHelper.onAttach(base))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
    }

    private fun progressBar(): KProgressHUD {
        return KProgressHUD.create(this)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setLabel(getString(R.string.loading_please_wait))
            .setCancellable(false)
            .setAnimationSpeed(2)
            .setDimAmount(0.5f)
    }

    protected val viewModel: ViewModel by lazy { ViewModelProvider(this).get(getViewModelClass()) }
    protected val progressBar: KProgressHUD by lazy { progressBar() }
    protected val binding: ViewBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            getLayoutResId()
        )
    }

    protected fun getViewModelFactory(): (() -> ViewModelProvider.Factory)? = null
    protected abstract fun getViewModelClass(): Class<ViewModel>
    protected abstract fun getLayoutResId(): Int


}
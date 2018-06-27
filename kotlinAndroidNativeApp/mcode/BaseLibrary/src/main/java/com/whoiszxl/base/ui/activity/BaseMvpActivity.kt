package com.whoiszxl.base.ui.activity

import android.os.Bundle
import com.whoiszxl.base.common.BaseApplication
import com.whoiszxl.base.injection.component.ActivityComponent
import com.whoiszxl.base.injection.component.DaggerActivityComponent
import com.whoiszxl.base.injection.module.ActivityModule
import com.whoiszxl.base.injection.module.LifecycleProviderModule
import com.whoiszxl.base.presenter.BasePresenter
import com.whoiszxl.base.presenter.view.BaseView
import javax.inject.Inject

open abstract class BaseMvpActivity<T:BasePresenter<*>>:BaseActivity(),BaseView {
    override fun showLoading() {

    }

    override fun hideLoading() {
    }

    override fun onError() {
    }

    @Inject
    lateinit var mPresenter:T

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        injectComponent()
    }

    abstract fun injectComponent()

    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent.builder()
                .appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }
}
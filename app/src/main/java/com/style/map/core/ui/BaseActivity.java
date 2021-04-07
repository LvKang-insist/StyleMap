package com.style.map.core.ui;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.gyf.immersionbar.ImmersionBar;

/**
 * base 基类
 */
public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {

    public T binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //使用 DataBinding
        binding = DataBindingUtil.setContentView(this, layout());
        immersion();
        initView();
        listener();
    }


    private void immersion() {
        ImmersionBar.with(this)
                .transparentStatusBar()
                .init();
    }


    //布局
    public abstract int layout();

    //init 初始化
    public abstract void initView();

    //事件处理
    public void listener() {

    }

}

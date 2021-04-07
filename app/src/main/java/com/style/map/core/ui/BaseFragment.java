package com.style.map.core.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

/**
 * 基类 fragment
 */
public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {

    public T binding;

    private Boolean isLazyLoad = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, layout(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isLazyLoad) {
            isLazyLoad = true;
            initView();
            listener();
        }
    }

    //布局
    public abstract int layout();

    //init初始
    public abstract void initView();

    //事件处理
    public void listener() {

    }
}

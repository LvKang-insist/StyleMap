package com.style.map.ui.launch;

import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;

import com.bumptech.glide.Glide;
import com.style.map.R;
import com.style.map.core.ui.BaseActivity;
import com.style.map.databinding.ActivityLaunchBinding;
import com.style.map.ui.main.MainActivity;


/**
 * 启动页
 */
public class LaunchActivity extends BaseActivity<ActivityLaunchBinding> {

    @Override
    public int layout() {
        return R.layout.activity_launch;
    }

    @Override
    public void initView() {
        startActivity(new Intent(LaunchActivity.this, MainActivity.class));
        finish();
    }
}
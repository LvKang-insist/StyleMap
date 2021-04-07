package com.style.map.ui.main;


import android.content.Intent;
import android.view.View;

import com.style.map.R;
import com.style.map.core.storage.FileUtils;
import com.style.map.core.ui.BaseActivity;
import com.style.map.databinding.ActivityMainBinding;
import com.style.map.ui.search.SearchActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private static final String CUSTOM_FILE_NAME_CX = "7d09ae342b1b516719ee0321c2c29f50.sty";

    @Override
    public int layout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        //设置个性皮肤
        String customStyleFilePath = FileUtils.getAssetsCacheFile(this, CUSTOM_FILE_NAME_CX);
        // 设置个性化地图样式文件的路径和加载方式
        binding.mapView.setMapCustomStylePath(customStyleFilePath);
        // 动态设置个性化地图样式是否生效
        binding.mapView.setMapCustomStyleEnable(true);
    }


    @Override
    public void listener() {
        binding.search.setOnClickListener(v -> {
            startActivityForResult(new Intent(this, SearchActivity.class), 0);
        });

        binding.ivPositioning.setOnClickListener(v -> {

        });
        binding.ivTrajectory.setOnClickListener(v -> {

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        binding.mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.mapView.onDestroy();
    }
}
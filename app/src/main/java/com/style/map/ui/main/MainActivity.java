package com.style.map.ui.main;


import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.style.map.R;
import com.style.map.core.storage.FileUtils;
import com.style.map.core.ui.BaseActivity;
import com.style.map.databinding.ActivityMainBinding;
import com.style.map.ui.search.SearchActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private static final String CUSTOM_FILE_NAME_CX = "7d09ae342b1b516719ee0321c2c29f50.sty";
    LocationClient mLocationClient = new LocationClient(this);

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
        //开头定位图层
        binding.mapView.getMap().setMyLocationEnabled(true);
        //自定义属性
        binding.mapView.getMap().setMyLocationConfiguration(new MyLocationConfiguration(
                MyLocationConfiguration.LocationMode.NORMAL, true, null, 0xff0000, 0xFFFFFF));
        dw();
    }


    @Override
    public void listener() {
        binding.search.setOnClickListener(v -> {
            startActivityForResult(new Intent(this, SearchActivity.class), 0);
        });

        binding.ivPositioning.setOnClickListener(v -> {
            dw();
        });
        binding.ivTrajectory.setOnClickListener(v -> {

        });
        //注册LocationListener监听器
        mLocationClient.registerLocationListener(new BDAbstractLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation location) {
                //mapView 销毁后不在处理新接收的位置
                if (location == null || binding == null) {
                    return;
                }
                MyLocationData locData = new MyLocationData.Builder()
                        .accuracy(location.getRadius())
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .direction(location.getDirection())
                        .latitude(location.getLatitude())
                        .longitude(location.getLongitude()).build();
                Log.e("---345--->", "成功");
                binding.mapView.getMap().setMyLocationData(locData);
            }
        });
    }

    private void dw() {
        Log.e("---345--->", "开始定位");
        LocationClientOption locationClientOption = new LocationClientOption();
        // 可选，设置定位模式，默认高精度 LocationMode.Hight_Accuracy：高精度；
        locationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);

        // 可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
        locationClientOption.setCoorType("bd09ll");
        // 设置发起连续定位请求的间隔
        locationClientOption.setScanSpan(3000);
        // 可选，设置是否需要地址信息，默认不需要
        locationClientOption.setIsNeedAddress(true);
        // 可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        locationClientOption.setIsNeedLocationPoiList(true);
        // 可选，设置是否需要设备方向结果
        locationClientOption.setNeedDeviceDirect(true);
        // 可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        locationClientOption.setIsNeedLocationDescribe(true);
        // 需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用

        // 设置定位参数
        mLocationClient.setLocOption(locationClientOption);
        // 开启定位
        mLocationClient.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            SuggestionResult.SuggestionInfo info = data.getParcelableExtra("key");
        }
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
        mLocationClient.stop();
        binding.mapView.getMap().setMyLocationEnabled(false);
        binding.mapView.onDestroy();
    }
}
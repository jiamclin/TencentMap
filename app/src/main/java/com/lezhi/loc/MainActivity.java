package com.lezhi.loc;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.lezhi.loc.R;
import com.tencent.tencentmap.mapsdk.maps.MapView;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptorFactory;
import com.tencent.tencentmap.mapsdk.maps.model.Circle;
import com.tencent.tencentmap.mapsdk.maps.model.CircleOptions;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions;

public class MainActivity extends Activity {
    private MapView mapView;
    private TencentMap tencentMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_main);
        //
        mapView = findViewById(R.id.map);
        tencentMap = mapView.getMap();
        try {
            LatLng latLng = tencentMap.getCameraPosition().target;
            Circle accuracy = tencentMap.addCircle(new CircleOptions().center(latLng).//accuracy的中心点在latLng上
                    radius(DeviceUtil.dp2px(this,300)).
                    fillColor(0x22F68677).strokeColor(0x00ffffff).strokeWidth(0f));

            Marker myLocation = tencentMap.addMarker(new MarkerOptions().position(latLng).//myLocation的中心点在latLng上
                    icon(BitmapDescriptorFactory.fromResource(R.mipmap.transparent)).//transparent.png的尺寸是50*50
                    anchor(0.5f, 0.5f));
            myLocation.setPosition(latLng);
            myLocation.setInfoWindowAnchor(0.5f,0.5f);
            myLocation.setInfoWindowOffset(DeviceUtil.dp2px(this,6),DeviceUtil.dp2px(this,6));//这里如何设置才能使得custInfoWindow的中心点与accuracy的中心点一致？

            TencentMap.InfoWindowAdapter custInfoWindow = new TencentMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {
//                                LogUtil.i(LogUtil.DEBUG, "marker:" + marker.getTitle());
                    View v = View.inflate(MainActivity.this,R.layout.map_current_loc,null);
                    RelativeLayout rl_out = v.findViewById(R.id.rl_out);
                    GradientDrawable gd = ImgUtil.getSolidCircle(0xffffffff);
                    ApiUtil.setBackgroundDrawable(rl_out,gd);
                    //
                    View v_inner = v.findViewById(R.id.v_inner);
                    gd = ImgUtil.getSolidCircle(0xffFF7E71);
                    ApiUtil.setBackgroundDrawable(v_inner,gd);
                    return v;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    return null;
                }

            };
            tencentMap.setInfoWindowAdapter(custInfoWindow);
            myLocation.showInfoWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //
        if(mapView==null){
            mapView.onDestroy();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        //
        mapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //
        mapView.onStop();
    }
}

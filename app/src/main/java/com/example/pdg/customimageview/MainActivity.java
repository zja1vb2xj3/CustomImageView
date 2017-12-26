package com.example.pdg.customimageview;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends Activity {
    private MapView mapView;
    private boolean value = false;
    private Button button;
    private PagerFragment pagerFragment;
    private ArrayList<Bitmap> bitmaps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titlebarInit();
        bitmaps = new ArrayList<>();
        pagerFragment = new PagerFragment();

        for(int i=0; i<5; i++){
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
            bitmaps.add(bitmap);
        }

        pagerFragment.setBitmaps(bitmaps);
    }

    private void titlebarInit(){
        View titlebar = (View) findViewById(R.id.titlebar);

        TextView zoomView = (TextView) titlebar.findViewById(R.id.zoomView);
        zoomView.setOnClickListener(v -> selectedZoomView());
        TextView viewPager = (TextView) titlebar.findViewById(R.id.viewPager);
        viewPager.setOnClickListener(v -> selectedViewPager());
    }

    private void selectedZoomView() {
        loadFragment(new ZoomFragment());
    }

    private void selectedViewPager() {

        loadFragment(pagerFragment);
    }

    private void loadFragment(Fragment fragment){
        FragmentManager fm = getFragmentManager() ;

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }



}

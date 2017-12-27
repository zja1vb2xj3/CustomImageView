package com.example.pdg.customimageview;


import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

/**
 * Created by pdg on 2017-12-26.
 */

public class PagerFragment extends Fragment {
    private View view;
    private ArrayList<Bitmap> bitmaps;
    private PagerAdapter pagerAdapter;

    public PagerFragment() {
        pagerAdapter = new PagerAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_pager, container, false);
            holder = new ViewHolder();

            holder.viewPager = (ViewPager) view.findViewById(R.id.viewPager);
            holder.tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);

            for (int i = 0; i < pagerAdapter.getCount(); i++) {
                if (i == 0) {
                    holder.tabLayout.addTab(holder.tabLayout.newTab().setIcon(R.mipmap.circle_red));
                } else {
                    holder.tabLayout.addTab(holder.tabLayout.newTab().setIcon(R.mipmap.circle_black));
                }
            }

            holder.viewPager.setAdapter(pagerAdapter);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(holder.tabLayout));
        holder.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i("tab : ", "onTabSelected");
                tab.setIcon(R.mipmap.circle_red);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.i("tab : ", "onTabUnselected");
                tab.setIcon(R.mipmap.circle_black);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.i("tab : ", "onTabReselected");
            }
        });

        return view;
    }

    public void setBitmaps(ArrayList<Bitmap> bitmaps) {
        this.bitmaps = bitmaps;
    }

    private class ViewHolder {
        private ViewPager viewPager;
        private TabLayout tabLayout;
    }

    private class PagerAdapter extends android.support.v4.view.PagerAdapter {
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LinearLayout adapterLayout = new LinearLayout(container.getContext());
            adapterLayout.setOrientation(LinearLayout.VERTICAL);
            PhotoView photoView = new PhotoView(container.getContext());
            photoView.setImageBitmap(bitmaps.get(position));

            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            photoView.setLayoutParams(layoutParams);

            container.addView(photoView);

            return photoView;
        }

        @Override
        public int getCount() {
            return bitmaps.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ImageView) object);
        }
    }
}

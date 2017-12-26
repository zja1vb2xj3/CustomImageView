package com.example.pdg.customimageview;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by pdg on 2017-12-26.
 */

public class ZoomFragment extends Fragment {
    private View view;
    private boolean mapViewSign = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewHolder holder;

        if(view == null){
            view = inflater.inflate(R.layout.fragment_zoom, container, false);
            holder = new ViewHolder();

            holder.mapView = (MapView) view.findViewById(R.id.mapView);
            holder.button = (Button) view.findViewById(R.id.button);

            view.setTag(holder);
        }
        else{
            holder = (ViewHolder)view.getTag();
        }

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "버튼 클릭", Toast.LENGTH_SHORT).show();
                mapViewSign = !mapViewSign;
                holder.mapView.setMarkerSign(mapViewSign);
            }
        });

        return view;
    }

    private class ViewHolder{
        private MapView mapView;
        private Button button;
    }
}

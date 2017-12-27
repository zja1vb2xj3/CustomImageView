package com.example.pdg.customimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;

/**
 * Created by pdg on 2017-12-22.
 */

public class MapView extends android.support.v7.widget.AppCompatImageView {

    private Bitmap redMarker;
    private Bitmap blueMarker;
    private boolean markerSign = false;

    private int normalPinSize = 50;
    private final String CLASSNAME = getClass().getSimpleName();

    private int viewWidth;
    private int viewHeight;


    public void setMarkerSign(boolean markerSign) {
        this.markerSign = markerSign;
        invalidate();
    }

    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);

        Bitmap redMarker = BitmapFactory.decodeResource(getResources(), R.mipmap.red_marker);
        Bitmap blueMarker = BitmapFactory.decodeResource(getResources(), R.mipmap.blue_marker);

        this.redMarker = resizeBitmapImage(redMarker, normalPinSize);
        this.blueMarker = resizeBitmapImage(blueMarker, normalPinSize);
    }

    public Bitmap resizeBitmapImage(Bitmap pin, int pinSize) {
        return Bitmap.createScaledBitmap(pin, pinSize, pinSize, true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = MeasureSpec.getSize(widthMeasureSpec);
        viewHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(CLASSNAME, "onDraw");

        if(markerSign == false){
            canvas.drawBitmap(blueMarker, viewWidth/2, viewHeight/2, null);
        }
        else{
            canvas.drawBitmap(redMarker, viewWidth/4, viewHeight/4, null);
        }
    }


}

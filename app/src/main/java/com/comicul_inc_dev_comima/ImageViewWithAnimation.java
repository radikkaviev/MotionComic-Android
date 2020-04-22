package com.comicul_inc_dev_comima;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageViewWithAnimation extends androidx.appcompat.widget.AppCompatImageView {

    private ArrayList<Bitmap> bitmapArray;
    private int interval = 1000;
    private int currentPosition;

    public Thread animationThread;
    public Context mContext;
    private Handler mHandler;

    public boolean stopRunnable = false ;

    //　■□■□■□■□■□■□■□■□■□■□■□
    //　Constructors
    //　■□■□■□■□■□■□■□■□■□■□■□


    public ImageViewWithAnimation(final Context context,ArrayList<Bitmap> bitmapArrayList) {
        super(context);
        this.mContext=context;
        mHandler = new Handler();
        bitmapArray = new ArrayList<Bitmap>();
        bitmapArray.addAll(bitmapArrayList);
    }


    /*
     * set interval in Milliseconds between images flip
     */
    public void setInterval(int ms){
        this.interval=ms;
    }

    /*
     * Add multiples images at once
     */
    public void addImages(Bitmap... bm){
        for(Bitmap b : bm)
            this.bitmapArray.add(b);
    }

    /*
     * Add one image
     */
    public void addImage(Bitmap bm){
        this.bitmapArray.add(bm);
    }

    /*
     * Start animation
     */
    public void launchAnimation(){

        if(bitmapArray.size()>1){
            mStatusChecker.run();
        }else if(bitmapArray.size()==1){
            setImageBitmap(bitmapArray.get(0));
        }else{
            setVisibility(View.GONE);
        }
    }

    /*
     *  Runnable for update image
     */
    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            if(!stopRunnable){
                setImageBitmap(bitmapArray.get(getNextImagePosition()));
                mHandler.postDelayed(mStatusChecker, interval);
            }
        }
    };


    /*
     * get next image position to display
     */
    public int getNextImagePosition(){
        if(bitmapArray.size()> currentPosition+1)
            currentPosition++;
        else
            currentPosition=0;
        return currentPosition;
    }

    /*
     * Stop image animation
     */
    public void stopImage(){
        stopRunnable=true;
        for(Bitmap bm: bitmapArray){
            bm.recycle();
        }

    }
}

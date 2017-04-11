package com.mindlot.firebase.codelab.mindlot.code;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ViewFlipper;


/**
 * Created by user on 8/w1/2016.
 */
public class contact extends Activity {

    private ViewFlipper mViewFlipper,mViewFlipper2;
    private GestureDetector mGestureDetector;
    int[] resources={
            com.mindlot.firebase.codelab.mindlot.R.drawable.logo, com.mindlot.firebase.codelab.mindlot.R.drawable.w1, com.mindlot.firebase.codelab.mindlot.R.drawable.w2, com.mindlot.firebase.codelab.mindlot.R.drawable.w3, com.mindlot.firebase.codelab.mindlot.R.drawable.do1, com.mindlot.firebase.codelab.mindlot.R.drawable.do2, com.mindlot.firebase.codelab.mindlot.R.drawable.do3};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.mindlot.firebase.codelab.mindlot.R.layout.contact_mains);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, com.mindlot.firebase.codelab.mindlot.R.color.black));
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        CustomGestureDetector customGestureDetector = new CustomGestureDetector();
        mGestureDetector = new GestureDetector(this, customGestureDetector);
        mViewFlipper = (ViewFlipper) findViewById(com.mindlot.firebase.codelab.mindlot.R.id.vi);


        mViewFlipper.setInAnimation(this, android.R.anim.fade_in);
        mViewFlipper.setOutAnimation(this, android.R.anim.fade_out);
        mViewFlipper.setAutoStart(true);
        mViewFlipper.setFlipInterval(2000);


        // Add all the images to the ViewFlipper
        for (int i = 0; i < resources.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(resources[i]);
            mViewFlipper.addView(imageView);


        }








    }
    class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            // Swipe left (next)
            if (e1.getX() > e2.getX()) {
                mViewFlipper.setInAnimation(contact.this, com.mindlot.firebase.codelab.mindlot.R.anim.left_in);
                mViewFlipper.setOutAnimation(contact.this, com.mindlot.firebase.codelab.mindlot.R.anim.left_out);

                mViewFlipper.showNext();
            }

            // Swipe right (previous)
            if (e1.getX() < e2.getX()) {
                mViewFlipper.setInAnimation(contact.this, com.mindlot.firebase.codelab.mindlot.R.anim.right_in);
                mViewFlipper.setOutAnimation(contact.this, com.mindlot.firebase.codelab.mindlot.R.anim.right_out);

                mViewFlipper.showPrevious();
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }

    }

    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }
}

package com.mindlot.firebase.codelab.mindlot.code;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.mindlot.firebase.codelab.mindlot.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {
    private ViewFlipper mViewFlipper,mViewFlipper2;
    private GestureDetector mGestureDetector;
    int[] resources={
            R.drawable.logo,R.drawable.w1,R.drawable.w2,R.drawable.w3,R.drawable.do1,R.drawable.do2,R.drawable.do3};


    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_favorite, container, false);

        CustomGestureDetector customGestureDetector = new CustomGestureDetector();
        GestureDetector mGestureDetector = new GestureDetector(getActivity(), customGestureDetector);
        mViewFlipper = (ViewFlipper) v.findViewById(R.id.vi);


        mViewFlipper.setInAnimation(getActivity(), android.R.anim.fade_in);
        mViewFlipper.setOutAnimation(getActivity(), android.R.anim.fade_out);
        mViewFlipper.setAutoStart(true);
        mViewFlipper.setFlipInterval(2000);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.fab3_show);
        mViewFlipper.startAnimation(myFadeInAnimation);

        // Add all the images to the ViewFlipper
        for (int i = 0; i < resources.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(resources[i]);
            mViewFlipper.addView(imageView);
        }


        return v;

    }
    class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            // Swipe left (next)
            if (e1.getX() > e2.getX()) {
                mViewFlipper.setInAnimation(getActivity(), R.anim.left_in);
                mViewFlipper.setOutAnimation(getActivity(), R.anim.left_out);

                mViewFlipper.showNext();
            }

            // Swipe right (previous)
            if (e1.getX() < e2.getX()) {
                mViewFlipper.setInAnimation(getActivity(), R.anim.right_in);
                mViewFlipper.setOutAnimation(getActivity(), R.anim.right_out);

                mViewFlipper.showPrevious();
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }

    }



}
package com.mindlot.firebase.codelab.mindlot.code;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;


/**
 * Created by real on 6/5/2016.
 */
public class Splash extends AppCompatActivity {
    SessionManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(com.mindlot.firebase.codelab.mindlot.R.layout.activity_splash);
        manager=new SessionManager();




        // METHOD w1

        /****** Create Thread that will sleep for w3 seconds *************/
        Thread background = new Thread() {
            public void run() {

                try {
                    // Thread will sleep for w3 seconds
                    sleep(1*300);

                    // After 5 seconds redirect to another intent
                    String status=manager.getPreferences(Splash.this,"status");
                    Log.d("status",status);
                    if (status.equals("1")){
                        Intent i=new Intent(Splash.this,mains.class);
                        startActivity(i);
                    }else{
                        Intent i=new Intent(Splash.this,mains.class);
                        startActivity(i);
                    }


                    //Remove activity
                    finish();

                } catch (Exception e) {

                }
            }
        };

        // start thread
        background.start();

//METHOD w2

        /*
        new Handler().postDelayed(new Runnable() {

            // Using handler with postDelayed called runnable run method

            @Override
            public void run() {
              if (status=="w1"){
                        Intent i=new Intent(Splash.this,home.class);
                        startActivity(i);
                    }else{
                        Intent i=new Intent(Splash.this,Login.class);
                        startActivity(i);
                    }

                // close this activity
                finish();
            }
        }, w3*1000); // wait for w3 seconds
        */
    }


}

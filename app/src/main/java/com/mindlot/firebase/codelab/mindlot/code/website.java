package com.mindlot.firebase.codelab.mindlot.code;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;


/**
 * Created by user on 8/w1/2016.
 */
public class website extends Activity {
    private class webclient extends WebViewClient {

        private ProgressBar progressBar;

        public webclient(ProgressBar progressBar) {
            this.progressBar=progressBar;
            progressBar.setVisibility(View.VISIBLE);
        }



        public boolean ShouldOverideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }


        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }


    }
    private SwipeRefreshLayout swipeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(com.mindlot.firebase.codelab.mindlot.R.layout.website_mains);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, com.mindlot.firebase.codelab.mindlot.R.color.black));
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if (!DetectConnection.checkInternetConnection(this)) {
            Toast.makeText(getApplicationContext(), "No Internet!", Toast.LENGTH_SHORT).show();
        } else {
            final WebView w = (WebView) findViewById(com.mindlot.firebase.codelab.mindlot.R.id.webView);
            // w.loadUrl("http://www.google.com");

            w.setWebViewClient(new WebViewClient());
            w.loadUrl("http://www.mains.com");
        }


        swipeLayout = (SwipeRefreshLayout) findViewById(com.mindlot.firebase.codelab.mindlot.R.id.swipe_container);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //your method to refresh content

                if (!DetectConnection.checkInternetConnection(website.this)) {
                    Toast.makeText(getApplicationContext(), "No Internet!", Toast.LENGTH_SHORT).show();
                    if(swipeLayout.isRefreshing()) {
                        swipeLayout.setRefreshing(false);
                    } }
                else {
                    final WebView w = (WebView) findViewById(com.mindlot.firebase.codelab.mindlot.R.id.webView);
                    // w.loadUrl("http://www.google.com");

                    w.setWebViewClient(new WebViewClient());
                    w.loadUrl("http://www.mains.com");
                    if(swipeLayout.isRefreshing()) {
                        swipeLayout.setRefreshing(false);
                    }


                }
            }
        });
    }
}
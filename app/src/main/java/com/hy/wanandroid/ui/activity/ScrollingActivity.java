package com.hy.wanandroid.ui.activity;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.hy.wanandroid.ui.R;

public class ScrollingActivity extends AppCompatActivity {
    private String mtitle;
    private WebView webView;
    private ProgressBar webProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mtitle=getIntent().getStringExtra("title");
        final CollapsingToolbarLayout collapsingToolbar=(CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        collapsingToolbar.setTitle(mtitle);
        String url=getIntent().getStringExtra("url");
        webView=findViewById(R.id.web_view);
        webProgress=findViewById(R.id.web_progress);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(chromeClient);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Thanks.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }
    WebChromeClient chromeClient=new WebChromeClient(){
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            webProgress.setProgress(newProgress);
            if (newProgress==100){
                webProgress.setVisibility(View.GONE);
            }
        }
    };
}

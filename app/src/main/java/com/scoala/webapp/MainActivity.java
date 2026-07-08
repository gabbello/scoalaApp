package com.scoala.webapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MainActivity extends AppCompatActivity {

    private static final String START_URL = "https://www.scoala.ro/";

    private WebView webView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent chromeIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(START_URL));
        chromeIntent.addCategory(Intent.CATEGORY_BROWSABLE);
        chromeIntent.setPackage("com.android.chrome");
        chromeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try {
            if (chromeIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(chromeIntent);
                finish();
                return;
            }
        } catch (Exception e) {
            // Fall through to the in-app WebView fallback.
        }

        webView = findViewById(R.id.webview);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);

        webView.setWebViewClient(new WebViewClient());
        swipeRefreshLayout.setOnRefreshListener(() -> webView.reload());
        webView.setWebChromeClient(new android.webkit.WebChromeClient());

        Toast.makeText(this, "Chrome not available. Running in app mode.", Toast.LENGTH_LONG).show();
        webView.loadUrl(START_URL);
    }
}

package com.scoala.webapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String START_URL = "https://www.scoala.ro/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent chromeIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(START_URL));
        chromeIntent.addCategory(Intent.CATEGORY_BROWSABLE);
        chromeIntent.setPackage("com.android.chrome");
        chromeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        chromeIntent.putExtra("com.android.chrome.extra.TABS", true);
        chromeIntent.putExtra("create_new_tab", false);
        chromeIntent.putExtra("from_app", true);

        try {
            startActivity(chromeIntent);
        } catch (Exception e) {
            Intent fallbackIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(START_URL));
            fallbackIntent.addCategory(Intent.CATEGORY_BROWSABLE);
            fallbackIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(fallbackIntent);
        }

        finish();
    }
}

package com.scoala.webapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

public class MainActivity extends AppCompatActivity {

    private static final String START_URL = "https://www.scoala.ro/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder()
                .setShowTitle(false)
                .setUrlBarHidingEnabled(true)
                .setShareState(CustomTabsIntent.SHARE_STATE_OFF)
                .build();
        customTabsIntent.intent.setPackage("com.android.chrome");
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try {
            if (customTabsIntent.intent.resolveActivity(getPackageManager()) != null) {
                customTabsIntent.launchUrl(this, Uri.parse(START_URL));
                finish();
                return;
            }
        } catch (Exception e) {
            // Fall through to generic browser fallback.
        }

        Intent fallbackIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(START_URL));
        fallbackIntent.addCategory(Intent.CATEGORY_BROWSABLE);
        fallbackIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        if (fallbackIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(fallbackIntent);
            finish();
            return;
        }

        Toast.makeText(this, "No compatible browser found on this device.", Toast.LENGTH_LONG).show();
    }
}

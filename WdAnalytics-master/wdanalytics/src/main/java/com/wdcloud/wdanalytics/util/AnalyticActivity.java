package com.wdcloud.wdanalytics.util;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wdcloud.wdanalytics.R;
import com.wdcloud.wdanalytics.service.AnalyticsService;

public class AnalyticActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytic);
        Intent serviceintent = new Intent(AnalyticActivity.this, AnalyticsService.class);
        startService(serviceintent);
        finish();
    }
}

package com.rmathur.cumtdbusappapp;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";
    private String[] cumtdWords = {"cumtd", "bus", "transit", "illinois"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final PackageManager pm = getPackageManager();
        //get a list of installed apps.
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        List<ApplicationInfo> cumtdPackages = new ArrayList<ApplicationInfo>();

        for (ApplicationInfo packageInfo : packages) {
            if(stringContainsItemFromList(packageInfo.packageName, cumtdWords)) {
                cumtdPackages.add(packageInfo);
            }
        }

        if(!cumtdPackages.isEmpty()) {
            Random randomGenerator = new Random();
            int index = randomGenerator.nextInt(cumtdPackages.size());
            ApplicationInfo packageInfo = cumtdPackages.get(index);
            startActivity(pm.getLaunchIntentForPackage(packageInfo.packageName));
        } else {
            setContentView(R.layout.activity_main);
        }
    }

    public boolean stringContainsItemFromList(String inputString, String[] items) {
        for(int i =0; i < items.length; i++) {
            if(inputString.contains(items[i])) {
                return true;
            }
        }
        return false;
    }
}

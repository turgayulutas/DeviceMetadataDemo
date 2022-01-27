package com.cashful.deviceinformation.datausages;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AppOpsManager;
import android.app.usage.NetworkStats;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import android.widget.Toast;

import com.cashful.deviceinformation.R;
import com.cashful.devicemetadata.datausages.DataUsagesData;
import com.cashful.devicemetadata.datausages.DataUsagesInformation;

public class DataUsagesActivity extends AppCompatActivity {

    private TextView tv_mobile_data_upload;
    private TextView tv_mobile_data_download;
    private TextView tv_wifi_data_upload;
    private TextView tv_wifi_data_download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_usages);

        tv_mobile_data_upload = findViewById(R.id.tv_mobile_data_upload);
        tv_mobile_data_download = findViewById(R.id.tv_mobile_data_download);
        tv_wifi_data_upload = findViewById(R.id.tv_wifi_data_upload);
        tv_wifi_data_download = findViewById(R.id.tv_wifi_data_download);

        if (checkUserStatePermission()) {
            startActivityForResult(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS), 1);

        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED

            ) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE
                }, 2);
            } else getInternetUsage();
        }
    }

    private void getInternetUsage() {
        DataUsagesInformation dataUsagesInformation = new DataUsagesInformation(this);
        DataUsagesData dataUsagesData = dataUsagesInformation.getInternetUsage(1642694075000L);

        tv_mobile_data_upload.setText(dataUsagesData.getMobileDataUpload() / (1024f * 1024f) + " MB");
        tv_mobile_data_download.setText(dataUsagesData.getMobileDataDownload() / (1024f * 1024f) + " MB");

        tv_wifi_data_upload.setText(dataUsagesData.getWifiDataUpload() / (1024f * 1024f) + " MB");
        tv_wifi_data_download.setText(dataUsagesData.getWifiDataDownload() / (1024f * 1024f) + " MB");
    }


    private boolean checkUserStatePermission() {
        AppOpsManager appOps = (AppOpsManager)
                getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow("android:get_usage_stats",
                android.os.Process.myUid(), getPackageName());
        return mode != AppOpsManager.MODE_ALLOWED;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (checkUserStatePermission()) {
                Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                getInternetUsage();
            }

        }
    }

}
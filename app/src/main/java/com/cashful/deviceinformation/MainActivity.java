package com.cashful.deviceinformation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cashful.deviceinformation.apps.InstalledAppDataActivity;
import com.cashful.deviceinformation.call.CallActivity;
import com.cashful.deviceinformation.datausages.DataUsagesActivity;
import com.cashful.deviceinformation.device.DeviceDataActivity;
import com.cashful.deviceinformation.location.LocationActivity;
import com.cashful.deviceinformation.sms.SmsActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getCallLogs(View view) {
        startActivity(new Intent(this, CallActivity.class));
    }

    public void getSmsLogs(View view) {
        startActivity(new Intent(this, SmsActivity.class));
    }

    public void getDeviceData(View view) {
        startActivity(new Intent(this, DeviceDataActivity.class));
    }

    public void getLastLocation(View view) {
        startActivity(new Intent(this, LocationActivity.class));
    }

    public void getInstalledAppData(View view) {
        startActivity(new Intent(this, InstalledAppDataActivity.class));
    }

    public void getDataUsagesWeekly(View view) {
        startActivity(new Intent(this, DataUsagesActivity.class));
    }
}
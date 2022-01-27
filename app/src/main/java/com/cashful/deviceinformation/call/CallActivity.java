package com.cashful.deviceinformation.call;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cashful.devicemetadata.call.CallInformation;
import com.cashful.deviceinformation.R;

import java.util.ArrayList;
import java.util.List;

public class CallActivity extends AppCompatActivity {

    private RecyclerView rv_call_logs;
    private CallLogAdapter callLogAdapter;
    // Request code. It can be any number > 0.
    private static final int PERMISSIONS_REQUEST_CODE = 2;

    String[] appPermissions = {
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.PROCESS_OUTGOING_CALLS,
            Manifest.permission.READ_PHONE_STATE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calls);

        initUi();
        if (CheckAndRequestPermission()) {
            getCallData();
        }
    }

    public void getCallData() {
        CallInformation callInformation = new CallInformation(this);
        callLogAdapter = new CallLogAdapter(this, callInformation.getAllCalls());
        rv_call_logs.setAdapter(callLogAdapter);
    }

    private void initUi() {
        rv_call_logs = findViewById(R.id.call_log_list);
        rv_call_logs.setHasFixedSize(true);
        rv_call_logs.setLayoutManager(new LinearLayoutManager(this));
    }

    public boolean CheckAndRequestPermission() {
        //checking which permissions are granted
        List<String> listPermissionNeeded = new ArrayList<>();
        for (String item : appPermissions) {
            if (ContextCompat.checkSelfPermission(this, item) != PackageManager.PERMISSION_GRANTED)
                listPermissionNeeded.add(item);
        }

        //Ask for non-granted permissions
        if (!listPermissionNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionNeeded.toArray(new String[listPermissionNeeded.size()]),
                    PERMISSIONS_REQUEST_CODE);
            return false;
        }
        //App has all permissions. Proceed ahead
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (PERMISSIONS_REQUEST_CODE == requestCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCallData();
            } else {
                Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
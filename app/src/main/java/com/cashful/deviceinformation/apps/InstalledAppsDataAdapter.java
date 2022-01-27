package com.cashful.deviceinformation.apps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cashful.devicemetadata.apps.AppData;
import com.cashful.deviceinformation.R;

import java.util.ArrayList;

public class InstalledAppsDataAdapter extends RecyclerView.Adapter<InstalledAppsDataAdapter.MyViewHolder> {
    Context context;
    ArrayList<AppData> appDataArrayList;

    public InstalledAppsDataAdapter(Context context, ArrayList<AppData> appDataArrayList) {
        this.context = context;
        this.appDataArrayList = appDataArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_installed_apps_data, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AppData currentLog = appDataArrayList.get(position);
        holder.tv_appName.setText(currentLog.getAppName());
        holder.tv_appPackageName.setText(currentLog.getPackageName());
        holder.tv_appVersion.setText(currentLog.getVersion());
    }

    @Override
    public int getItemCount() {
        return appDataArrayList == null ? 0 : appDataArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_appName, tv_appPackageName, tv_appVersion;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_appName = itemView.findViewById(R.id.layout_installed_apps_name);
            tv_appPackageName = itemView.findViewById(R.id.layout_installed_apps_package_name);
            tv_appVersion = itemView.findViewById(R.id.layout_installed_apps_version);
        }
    }
}
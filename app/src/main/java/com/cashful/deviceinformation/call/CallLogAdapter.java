package com.cashful.deviceinformation.call;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cashful.devicemetadata.call.CallData;
import com.cashful.deviceinformation.R;

import java.util.ArrayList;

public class CallLogAdapter extends RecyclerView.Adapter<CallLogAdapter.MyViewHolder> {
    private int px;
    Context context;
    ArrayList<CallData> callLogModelArrayList;

    public CallLogAdapter(Context context, ArrayList<CallData> callLogModelArrayList) {
        this.context = context;
        this.callLogModelArrayList = callLogModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Resources r = parent.getResources();
        px = Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 8, r.getDisplayMetrics()));
        View v = LayoutInflater.from(context).inflate(R.layout.item_call_data, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CallData currentLog = callLogModelArrayList.get(position);
        try {
            holder.tv_ph_num.setText(currentLog.getAddress().substring(0, 6) + "***** (hidden for demo)");
            holder.tv_contact_name.setText(currentLog.getContactName().substring(0, 4) + "***** (hidden for demo)");
        } catch (StringIndexOutOfBoundsException e) {
            holder.tv_ph_num.setText(currentLog.getAddress().substring(0, 3) + "***** (hidden for demo)");
            holder.tv_contact_name.setText("***** (hidden for demo)");
        }
        holder.tv_call_type.setText(currentLog.getCallType());
        holder.tv_call_date.setText(currentLog.getCallDate());
        holder.tv_call_time.setText(currentLog.getCallTime());
        holder.tv_call_duration.setText(currentLog.getCallDuration());
    }

    @Override
    public int getItemCount() {
        return callLogModelArrayList == null ? 0 : callLogModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tv_ph_num, tv_contact_name, tv_call_type, tv_call_date, tv_call_time, tv_call_duration;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_ph_num = itemView.findViewById(R.id.layout_call_log_ph_no);
            tv_contact_name = itemView.findViewById(R.id.layout_call_log_contact_name);
            tv_call_type = itemView.findViewById(R.id.layout_call_log_type);
            tv_call_date = itemView.findViewById(R.id.layout_call_log_date);
            tv_call_time = itemView.findViewById(R.id.layout_call_log_time);
            tv_call_duration = itemView.findViewById(R.id.layout_call_log_duration);
            cardView = itemView.findViewById(R.id.layout_call_log_cardview);
        }
    }
}
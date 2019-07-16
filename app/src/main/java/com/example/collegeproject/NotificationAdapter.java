package com.example.collegeproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    ArrayList<Notificationn> notificationns;

   public NotificationAdapter(ArrayList<Notificationn> notificationns){
       this.notificationns=notificationns;
   }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notification_item,viewGroup,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

       viewHolder.dateTv.setText(notificationns.get(i).getTime());
       viewHolder.titleTv.setText(notificationns.get(i).getTitle());
       viewHolder.contentTv.setText(notificationns.get(i).getContent());
    }

    @Override
    public int getItemCount() {
     return notificationns.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

       TextView titleTv,contentTv,dateTv;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            titleTv=itemView.findViewById(R.id.title);
            contentTv=itemView.findViewById(R.id.content);
            dateTv=itemView.findViewById(R.id.date);
        }
    }
}

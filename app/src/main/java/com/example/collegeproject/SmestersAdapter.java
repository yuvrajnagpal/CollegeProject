package com.example.collegeproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SmestersAdapter extends RecyclerView.Adapter<SmestersAdapter.ViewHolder> {

    ArrayList<String> semestersList;
   public SmestersAdapter(ArrayList<String> semestersList){

       this.semestersList=semestersList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

       View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.semester_item,viewGroup,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

       viewHolder.semestertext.setText(semestersList.get(i));

    }

    @Override
    public int getItemCount() {
        return semestersList.size() ;
    }


    class ViewHolder extends RecyclerView.ViewHolder{

       TextView semestertext;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            semestertext=itemView.findViewById(R.id.semester_text);
        }
    }
}

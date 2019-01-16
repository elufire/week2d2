package com.example.rickb.week2d2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.rickb.week2d2.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    ArrayList<Student> studentsArrayList;

    public RecyclerViewAdapter(ArrayList<Student> studentsArrayList) {
        this.studentsArrayList = studentsArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int position) {
        Student student = studentsArrayList.get(position);

        if(student != null) {
            String ssn = student.getSSN();
            String name = student.getName();
            String major = student.getMajor();
            String minor = student.getMinor();
            String gpa = student.getGPA();
            String dob = student.getDOB();
            String homeCity = student.getHomeCity();
            String homeState = student.getHomeState();


            viewHolder.ssn.setText(ssn);
            viewHolder.name.setText(name);
            viewHolder.major.setText(major);
            viewHolder.minor.setText(minor);
            viewHolder.gpa.setText(gpa);
            viewHolder.dob.setText(dob);
            viewHolder.homeCity.setText(homeCity);
            viewHolder.homeState.setText(homeState);
        }
    }

    @Override
    public int getItemCount() {
        return studentsArrayList != null ? studentsArrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgImage;
        TextView ssn;
        TextView name;
        TextView major;
        TextView minor;
        TextView gpa;
        TextView dob;
        TextView homeCity;
        TextView homeState;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgImage = itemView.findViewById(R.id.imgViewImage);
            ssn = itemView.findViewById(R.id.ssn);
            name = itemView.findViewById(R.id.name);
            major = itemView.findViewById(R.id.major);
            minor = itemView.findViewById(R.id.minor);
            gpa = itemView.findViewById(R.id.gpa);
            dob = itemView.findViewById(R.id.dob);
            homeCity = itemView.findViewById(R.id.homeCity);
            homeState = itemView.findViewById(R.id.homeState);

        }
    }
}

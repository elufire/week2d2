package com.example.rickb.week2d2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView tvDisplay;
    RecyclerView recyclerView;
    public static final String TAG = "tag_one";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tvDisplay = findViewById(R.id.tvDisplay);
        Log.d(TAG, "Before Database create!");

        MySqlDatabaseHelper mySqlDatabaseHelper = new MySqlDatabaseHelper(this);
        Student student = new Student("247", "l", "e", "t", "s",
                "g", "o", "!");
        Student studentTwo = new Student("600", "l", "e", "t", "s",
                "g", "o", "!");
        mySqlDatabaseHelper.insertStudent(student);
        mySqlDatabaseHelper.insertStudent(studentTwo);
        Student letsGo = mySqlDatabaseHelper.getStudent("247");
        Log.d(TAG, "Created: " + letsGo.getName());
//        tvDisplay.append(letsGo.getName());
//        tvDisplay.append(letsGo.getMajor());
//        tvDisplay.append(letsGo.getMinor());
        //letsGo.setSSN("600");
        //mySqlDatabaseHelper.updateStudent(letsGo);
        ;
        //Integer.toString(mySqlDatabaseHelper.deleteStudent("247"))
//        ArrayList<Student> allStudents = mySqlDatabaseHelper.getAllStudents();
//
//        for(Student stud: allStudents){
//            tvDisplay.append(stud.getSSN());

        recyclerView = findViewById(R.id.rvMainRecyclerView);
        RecyclerViewAdapter rvAdapter = new RecyclerViewAdapter(listOfStudents());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(rvAdapter);

//        }

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.editTable:
                Intent intent = new Intent(this, Main2Activity.class);
                startActivity(intent);
        }
    }

    private ArrayList<Student> listOfStudents() {
        MySqlDatabaseHelper mySqlDatabaseHelper = new MySqlDatabaseHelper(this);
        ArrayList<Student> studentsArrayList = new ArrayList<>();
        studentsArrayList = mySqlDatabaseHelper.getAllStudents();
        return studentsArrayList;
    }

}
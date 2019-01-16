package com.example.rickb.week2d2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    EditText studentSsn;
    EditText studentName;
    EditText studentMajor;
    EditText studentMinor;
    EditText studentGpa;
    EditText studentDob;
    EditText studentHomeCity;
    EditText studentHomeState;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        studentSsn = findViewById(R.id.studentSsn);
        studentName = findViewById(R.id.studentName);
        studentMajor = findViewById(R.id.studentMajor);
        studentMinor = findViewById(R.id.studentMinor);
        studentGpa = findViewById(R.id.studentGpa);
        studentDob = findViewById(R.id.studentDob);
        studentHomeCity = findViewById(R.id.studentHomeCity);
        studentHomeState = findViewById(R.id.studentHomeState);



    }

    public void onClick(View view){
        Intent intent;
        MySqlDatabaseHelper mySqlDatabaseHelper = new MySqlDatabaseHelper(this);
        Student student = new Student(studentSsn.getText().toString(), studentName.getText().toString(),
                studentMajor.getText().toString(),
                studentMinor.getText().toString(), studentGpa.getText().toString(),
                studentDob.getText().toString(), studentHomeCity.getText().toString(),
                studentHomeState.getText().toString());
        switch (view.getId()){
            case R.id.addStudent:

                mySqlDatabaseHelper.insertStudent(student);
                Toast.makeText(getBaseContext(), "Student Added SuccessfullY!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.deleteStudent:
                if(!student.getSSN().isEmpty()){
                    mySqlDatabaseHelper.deleteStudent(student.getSSN());
                    Toast.makeText(getBaseContext(), "Student Deleted Successfully!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.goBack:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.updateStudent:
                if(!student.getSSN().isEmpty()){
                    mySqlDatabaseHelper.updateStudent(student);
                    Toast.makeText(getBaseContext(), "Student Deleted Successfully!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.getoneStudent:
                if(!student.getSSN().isEmpty()){
                    student = mySqlDatabaseHelper.getStudent(student.getSSN());
                    if(student != null){
                        studentName.setText(student.getName());
                        studentMajor.setText(student.getMajor());
                        studentMinor.setText(student.getMinor());
                        studentGpa.setText(student.getGPA());
                        studentDob.setText(student.getDOB());
                        studentHomeCity.setText(student.getHomeCity());
                        studentHomeState.setText(student.getHomeState());
                    }
                    else{
                        Toast.makeText(getBaseContext(), "Student does not Exist!", Toast.LENGTH_SHORT).show();
                    }

                }
                break;

        }
    }
}

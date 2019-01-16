package com.example.rickb.week2d2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import static com.example.rickb.week2d2.DatabaseConstraints.DATABASE_NAME;
import static com.example.rickb.week2d2.DatabaseConstraints.DATABASE_VERSION;
import static com.example.rickb.week2d2.DatabaseConstraints.FIELD_DOB;
import static com.example.rickb.week2d2.DatabaseConstraints.FIELD_GPA;
import static com.example.rickb.week2d2.DatabaseConstraints.FIELD_HOMECITY;
import static com.example.rickb.week2d2.DatabaseConstraints.FIELD_HOMESTATE;
import static com.example.rickb.week2d2.DatabaseConstraints.FIELD_MAJOR;
import static com.example.rickb.week2d2.DatabaseConstraints.FIELD_MINOR;
import static com.example.rickb.week2d2.DatabaseConstraints.FIELD_NAME;
import static com.example.rickb.week2d2.DatabaseConstraints.FIELD_SSN;
import static com.example.rickb.week2d2.DatabaseConstraints.TABLE_NAME;



public class MySqlDatabaseHelper extends SQLiteOpenHelper {

    public MySqlDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "Create Table " + TABLE_NAME + "("
                + FIELD_SSN + " TEXT PRIMARY KEY, "
                + FIELD_NAME + " TEXT, "
                + FIELD_MAJOR + " TEXT, "
                + FIELD_MINOR + " TEXT, "
                + FIELD_GPA + " TEXT, "
                + FIELD_DOB + " TEXT, "
                + FIELD_HOMECITY + " TEXT, "
                + FIELD_HOMESTATE + " TEXT)";

        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void insertStudent(Student student){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        if( student != null){
            contentValues.put(FIELD_SSN, student.getSSN());
            contentValues.put(FIELD_NAME, student.getName());
            contentValues.put(FIELD_MAJOR, student.getMajor());
            contentValues.put(FIELD_MINOR, student.getMinor());
            contentValues.put(FIELD_GPA, student.getGPA());
            contentValues.put(FIELD_DOB, student.getDOB());
            contentValues.put(FIELD_MAJOR, student.getMajor());
            contentValues.put(FIELD_HOMECITY, student.getHomeCity());
            contentValues.put(FIELD_HOMESTATE, student.getHomeState());


            database.insert(TABLE_NAME, null, contentValues);
        }

    }

    public ArrayList<Student> getAllStudents(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME ;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if(cursor.moveToFirst()){
            ArrayList<Student> arrayList = new ArrayList<>();
            do{
                String ssn = cursor.getString(cursor.getColumnIndex(FIELD_SSN));
                String name = cursor.getString(cursor.getColumnIndex(FIELD_NAME));
                String major = cursor.getString(cursor.getColumnIndex(FIELD_MAJOR));
                String minor = cursor.getString(cursor.getColumnIndex(FIELD_MINOR));
                String gpa = cursor.getString(cursor.getColumnIndex(FIELD_GPA));
                String dob = cursor.getString(cursor.getColumnIndex(FIELD_DOB));
                String homeCity = cursor.getString(cursor.getColumnIndex(FIELD_HOMECITY));
                String homeState = cursor.getString(cursor.getColumnIndex(FIELD_HOMESTATE));
                arrayList.add(new Student(ssn, name, major, minor, gpa, dob, homeCity, homeState));
            }while(cursor.moveToNext());
            return arrayList;
        }else{
            return null;
        }

    }

    public Student getStudent(String passedSsn){
        Student returnStudent = null;
        if(passedSsn != null && !passedSsn.isEmpty()){
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " +
                    FIELD_SSN + " = \"" +passedSsn + "\"";
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            if(cursor.moveToFirst()){
                String ssn = cursor.getString(cursor.getColumnIndex(FIELD_SSN));
                String name = cursor.getString(cursor.getColumnIndex(FIELD_NAME));
                String major = cursor.getString(cursor.getColumnIndex(FIELD_MAJOR));
                String minor = cursor.getString(cursor.getColumnIndex(FIELD_MINOR));
                String gpa = cursor.getString(cursor.getColumnIndex(FIELD_GPA));
                String dob = cursor.getString(cursor.getColumnIndex(FIELD_DOB));
                String homeCity = cursor.getString(cursor.getColumnIndex(FIELD_HOMECITY));
                String homeState = cursor.getString(cursor.getColumnIndex(FIELD_HOMESTATE));
                returnStudent = new Student(ssn, name, major, minor, gpa, dob, homeCity, homeState);
            }
            cursor.close();
        }

        return returnStudent;
    }

    public int deleteStudent(String passedSsn){
        String whereClause = FIELD_SSN + "=" + "\"" +  passedSsn +"\"";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, whereClause , null);
    }

    public int updateStudent(Student student){
        if(student != null){
            String whereClause = FIELD_SSN + " = \"" + student.getSSN() + "\"";
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(FIELD_SSN, student.getSSN());
            contentValues.put(FIELD_NAME, student.getName());
            contentValues.put(FIELD_MAJOR, student.getMajor());
            contentValues.put(FIELD_MINOR, student.getMinor());
            contentValues.put(FIELD_GPA, student.getGPA());
            contentValues.put(FIELD_DOB, student.getDOB());
            contentValues.put(FIELD_HOMECITY, student.getHomeCity());
            contentValues.put(FIELD_HOMESTATE, student.getHomeState());
            return sqLiteDatabase.update(TABLE_NAME, contentValues, whereClause , null);
        }
        return 0;
    }

}
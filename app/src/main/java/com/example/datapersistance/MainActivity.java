package com.example.datapersistance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public static final String TAG="MainActivity";
    public static final String SHARED_PREF="name";
    public static final String KEY_NAME="key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //there is a class named shared preferences
        SharedPreferences sharedPreferences=this.getSharedPreferences(SHARED_PREF,MODE_PRIVATE);

        //write into shared preferences
      //  sharedPreferences.edit().putString(KEY_NAME,"Evneet ").apply();

        //read from shared preferences
        String name=sharedPreferences.getString(KEY_NAME,"Kulvir");

        Log.i(TAG,"On Create " +name);


        //save an array
        ArrayList<String> names=new ArrayList<>(Arrays.asList("Evneet","Kulvir","Mohammad","Mohan"));
//        sharedPreferences.edit().putStringSet("names",new HashSet<String>(names)).apply();
//
//        //retrieve data
//        Set<String> recvNames=sharedPreferences.getStringSet("names",new HashSet<String>());
//        Log.i(TAG,"On Create " +recvNames.toString());

        try {
            sharedPreferences.edit().putString("names", ObjectSerializer.serialize(names)).apply();
            Log.i(TAG,"On Create " +name);

        }catch (IOException e){
            e.printStackTrace();
        }

        List<String> recNames=new ArrayList<>();
        try{
            recNames=(ArrayList) ObjectSerializer.deserialize(sharedPreferences.getString("names",ObjectSerializer.serialize(new ArrayList<>())));
        }catch (IOException e) {
            e.printStackTrace();


        }
        Log.i(TAG,"On Create " +recNames.toString());
    }
}

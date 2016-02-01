package com.example.daniel_li.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.HashMap;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    private HashMap<String, Double> exerciseMap = new HashMap();
    private HashSet<String>  reps = new HashSet();
    private HashSet<String> minutes = new HashSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    private void setHashMap() {
        exerciseMap.put("Pushup", 100.0/350.0);
        exerciseMap.put("Situp", 100.0/200.0);
        exerciseMap.put("Squats", 100.0/225);
        exerciseMap.put("Leg-lift", 100.0/25);
        exerciseMap.put("Plank", 100.0/25);
        exerciseMap.put("Jumping Jacks", 100.0/10);
        exerciseMap.put("Pullup", 100.0/100);
        exerciseMap.put("Cycling", 100.0/12);
        exerciseMap.put("Walking", 100.0/20);
        exerciseMap.put("Jogging", 100.0/12);
        exerciseMap.put("Swimming", 100.0/13);
        exerciseMap.put("Stair-climbing", 100.0/15);

    }

    private void setHashSetReps() {
        reps.add("Pushup");
        reps.add("Situp");
        reps.add("Squats");
        reps.add("Pullup");

    }

    private void setHashSetminutes() {
        minutes.add("Leg-lift");
        minutes.add("Plank");
        minutes.add("Jumping Jacks");
        minutes.add("Cycling");
        minutes.add("Walking");
        minutes.add("Jogging");
        minutes.add("Swimming");
        minutes.add("Stair-climbing");
    }
}


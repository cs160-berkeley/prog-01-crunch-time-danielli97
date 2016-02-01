//credit where credit is due, thanks to Eric for helping me with design
//and for showing me how to use android studio for 3 hours
package com.example.daniel_li.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;


import java.util.HashMap;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private HashMap<String, Double> exerciseMap = new HashMap();
    private HashSet<String>  reps = new HashSet();
    private HashSet<String> minutes = new HashSet();
    private Spinner exercise1, exercise2;
    private TextView input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (TextView) findViewById(R.id.exerciseInput);
        exercise1 = (Spinner) findViewById(R.id.exerciseSpinner1);
        exercise2 = (Spinner) findViewById(R.id.exerciseSpinner2);

        //update spinner
        exercise1.setOnItemSelectedListener(this);
        exercise2.setOnItemSelectedListener(this);
        //add exercises to spinner
        spinnerGenerate();

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String exerciseType = (String) parent.getItemAtPosition(pos);

    }

    public void onNothingSelected(AdapterView<?> parent) {}

    public void spinnerGenerate() {
        //http://stackoverflow.com/questions/5241660/how-can-i-add-items-to-a-spinner-in-android
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.exerciseSelection1, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        exercise1.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.exerciseSelection2, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        exercise2.setAdapter(adapter2);
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
        exerciseMap.put("Swimming", 100.0 / 13);
        exerciseMap.put("Stair-climbing", 100.0 / 15);

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

    private double conversion(String exercise, Double amount) {
        double conversionMetric = exerciseMap.get(exercise);
        double retValue = conversionMetric * amount;
        return retValue;
    }
}


//credit where credit is due, thanks to Eric for helping me with design
//and for showing me how to use android studio for 3 hours
package com.example.daniel_li.myapplication;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;


import java.util.HashMap;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private HashMap<String, Double> exerciseMap = new HashMap();
    private HashSet<String>  reps = new HashSet();
    private HashSet<String> minutes = new HashSet();
    private Spinner exercise1, exercise2;
    private ImageView image1, image2;
    private EditText inputNumber;
    private TextView calories, outputTop, outputBottom, conversion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputNumber = (EditText) findViewById(R.id.exerciseInput);
        calories = (TextView) findViewById(R.id.calNumber);
        outputTop = (TextView) findViewById(R.id.outputType1);
        outputBottom = (TextView) findViewById(R.id.outputType2);
        conversion = (TextView) findViewById(R.id.conversion);
        exercise1 = (Spinner) findViewById(R.id.exerciseSpinner1);
        exercise2 = (Spinner) findViewById(R.id.exerciseSpinner2);
        image1 = (ImageView) findViewById(R.id.photo_from1);
        image2 = (ImageView) findViewById(R.id.photo_from2);

        //set hashmaps|hashsets
        setHashMap();
        setHashSetReps();
        //update spinner
        exercise1.setOnItemSelectedListener(this);
        exercise2.setOnItemSelectedListener(this);
        //dynamic memes
        inputNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                showConversion();
            }
        });
        //add exercises to spinner
        spinnerGenerate();
        //actual action
        showConversion();

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String exerciseType = (String) parent.getItemAtPosition(pos);
        showConversion();
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
        exerciseMap.put("Walking", 100.0 / 20);
        exerciseMap.put("Jogging", 100.0 / 12);
        exerciseMap.put("Swimming", 100.0 / 13);
        exerciseMap.put("Stair-climbing", 100.0 / 15);

    }

    private void setHashSetReps() {

        reps.add("Pushup");
        reps.add("Situp");
        reps.add("Squats");
        reps.add("Pullup");

    }

    private double conversion(String exercise, Double amount) {
        double conversionMetric = exerciseMap.get(exercise);
        double retValue = conversionMetric * amount;
        return Math.round(retValue* 100) / 100.0;
    }

    private void pictureUpdate() {
        String exerciseInputTop = exercise1.getSelectedItem().toString().toLowerCase().replaceAll("\\W","").replaceAll("\\s","");
        String exerciseInputBottom = exercise2.getSelectedItem().toString().toLowerCase().replaceAll("\\W","").replaceAll("\\s","");
        int res1 = getResources().getIdentifier(exerciseInputTop, "drawable", this.getPackageName());
        int res2 = getResources().getIdentifier(exerciseInputBottom, "drawable", this.getPackageName());
        image1.setImageResource(res1);
        image2.setImageResource(res2);
    }
    private void showConversion() {
        Double userInputNumber;
        //try {
            //Log.d("UNIT", "showConversion");
            //userInputNumber = 0.0;
            if (inputNumber.getText().toString().matches("")) {
                userInputNumber = 0.0;
            } else {
                userInputNumber = Double.parseDouble(inputNumber.getText().toString());
            }
            String exerciseInputTop = exercise1.getSelectedItem().toString();
            //Log.d("UNIT", exerciseInputTop);
            String exerciseInputBottom = exercise2.getSelectedItem().toString();
            //Log.d("UNIT", exerciseInputBottom);
            String rep = "Reps";
            String minutes = "Minutes";
            //set the correct units
            if (reps.contains(exerciseInputTop)) {
                //Log.d("UNIT", "reps contains exerciseInputTop");
                outputTop.setText(rep);
            } else {
                //Log.d("UNIT", "minutes contains exerciseInputTop");
                outputTop.setText(minutes);
            }
            if (reps.contains(exerciseInputBottom)) {
                //Log.d("UNIT", "reps contains exerciseInputBottom");
                outputBottom.setText(rep);
            } else {
                outputBottom.setText(minutes);
                //Log.d("UNIT", "min contains exerciseInputBottom");
            }
            //update picture
            pictureUpdate();
            //set correct calories burned
            Double converted = conversion(exerciseInputTop, userInputNumber);
            String caloriesBurned = converted.toString();
            calories.setText(caloriesBurned);
            //set correct exercise equivalent
            int equivalentConverted = (int) (converted / exerciseMap.get(exerciseInputBottom));
            String equivalentExercise = "" + equivalentConverted;
            conversion.setText(equivalentExercise);

        //} catch (Exception e) {
            //return;
        //}
    }
}


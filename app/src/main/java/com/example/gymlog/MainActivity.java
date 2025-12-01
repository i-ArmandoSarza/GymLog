package com.example.gymlog;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gymlog.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private static final String TAG = "DAC_GYMLOG";
    String mExercise = "";
    double mWeight = 0.0;
    int mReps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        // Allows us to reference our information.
        setContentView(binding.getRoot());

        // Allows us to scroll through the log display screen
        binding.logDisplayTextView.setMovementMethod(new ScrollingMovementMethod());

        // This handles the LOG button function
        binding.logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInformationFromDisplay();    // Get information first
                updateDisplay();                // Update and output onto display
            }
        });
    }

    /**
     * This method allows us to update the information
     * we retrieve from the user to the display.
     */
    private void updateDisplay(){
        // Retrieve value from "Hello World"
        String currentInfo = binding.logDisplayTextView.getText().toString();
        Log.d(TAG, "current info: "+currentInfo);
        String newDisplay = String.format(Locale.US,"Exercise:%s%nWeight:%.2f%nReps:%d%n=-=-=-=%n%s", mExercise, mWeight, mReps, currentInfo);
        binding.logDisplayTextView.setText(newDisplay);
    }

    /**
     * This method grabs the input information entered on the lines of
     * Exercise, Weights and Reps and placed them in our global variables.
     */
    private void getInformationFromDisplay(){
        // Take value from the user input line of "Exercise"
        mExercise = binding.exerciseInputEditText.getText().toString();
        // Take value from input line of Weight (Need to parse to Double)
        try {
            mWeight = Double.parseDouble(binding.weightInputEditText.getText().toString());
        }catch (NumberFormatException e){
            Log.d(TAG, "Error reading value from Weight edit text.");
        }
        // Take value from input line of Weight (Need to parse to Integer)
        try {
            mReps = Integer.parseInt(binding.repInputEditText.getText().toString());
        }catch (NumberFormatException e){
            Log.d(TAG, "Error reading value from Reps edit text.");
        }


    }
}
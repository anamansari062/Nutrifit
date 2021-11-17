package com.example.nutritionapp.ui.Dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import com.example.nutritionapp.Food.FoodViewModel;
import com.example.nutritionapp.Activity.MainActivity;
import com.example.nutritionapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


public class DashboardActivity extends AppCompatActivity {

    Button startTrackingButton;
    RelativeLayout noMealLayout,foodAnalysisLayout;
    CardView  cardTodayDashboard;
    FoodViewModel foodViewModel;
    ProgressBar progressTotalCaloriesBar, progressTotalProteinBar, progressTotalFatsBar, progressTotalCarbsBar, progressTotalFibreBar, progressTotalSodiumBar, progressTotalPotassiumBar, progressTotalSugarBar, progressTotalCholesterolBar, progressTotalFatSaturatedBar;
    TextView dashboardCalorieIntakePercentageText, dashboardCalorieIntakeGoalShow, dashboardProteinIntake, dashboardCarbsIntake, dashboardFatsIntake, dashboardFibreIntake, dashboardSodiumIntake, dashboardPotassiumIntake, dashboardSugarIntake, dashboardCholesterolIntake, dashboardFatSaturatedIntake;
    Date currentDate;
    SimpleDateFormat dateFormat;
    DatabaseReference databaseReference;
    String dateOnly;
    Float calorieGoal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);
        noMealLayout = findViewById(R.id.relative_no_meal_dashboard);
        foodAnalysisLayout = findViewById(R.id.dashboard_food_analysis_layout);
        cardTodayDashboard = findViewById(R.id.card_today_dashboard);
        foodViewModel = new ViewModelProvider(this).get(FoodViewModel.class);

        dashboardCalorieIntakePercentageText = findViewById(R.id.dashboard_calorie_intake_percentage);
        dashboardCalorieIntakeGoalShow = findViewById(R.id.dashboard_calorie_intake_goal_show);
        dashboardProteinIntake = findViewById(R.id.dashboard_protein_intake);
        dashboardCarbsIntake = findViewById(R.id.dashboard_carbs_intake);
        dashboardFatsIntake = findViewById(R.id.dashboard_fats_intake);
        dashboardFibreIntake = findViewById(R.id.dashboard_fibre_intake);
        dashboardSodiumIntake = findViewById(R.id.dashboard_sodium_intake);
        dashboardPotassiumIntake = findViewById(R.id.dashboard_potassium_intake);
        dashboardSugarIntake = findViewById(R.id.dashboard_sugar_intake);
        dashboardCholesterolIntake = findViewById(R.id.dashboard_cholesterol_intake);
        dashboardFatSaturatedIntake = findViewById(R.id.dashboard_fat_saturated_intake);



        currentDate = new Date();
        dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        dateOnly = dateFormat.format(currentDate);

        startTrackingButton = findViewById(R.id.start_tracking_meal_button);
        startTrackingButton.setOnClickListener(view -> {
            Intent intent = new Intent(DashboardActivity.this, MainActivity.class); intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); finish();

        });
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference("USERS");
        databaseReference.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, String> map= (Map<String, String>) snapshot.getValue();
                calorieGoal =  Float.parseFloat(map.get("calories"));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        progressTotalCaloriesBar = (ProgressBar) findViewById(R.id.progress_total_calories_horizontal);
        foodViewModel.getTotalTodayCalories(dateOnly).observe(this, Float -> updateTotalCaloriesProgressBar(Float));

        progressTotalProteinBar = (ProgressBar) findViewById(R.id.progress_protein_horizontal);
        foodViewModel.getTotalProtein(dateOnly).observe(this, Float -> updateTotalProteinProgressBar(Float));

        progressTotalCarbsBar = (ProgressBar) findViewById(R.id.progress_carbs_horizontal);
        foodViewModel.getTotalCarb(dateOnly).observe(this, Float -> updateTotalCarbsProgressBar(Float));

        progressTotalFatsBar = (ProgressBar) findViewById(R.id.progress_fats_horizontal);
        foodViewModel.getTotalFat(dateOnly).observe(this, Float -> updateTotalFatsProgressBar(Float));

        progressTotalFibreBar = (ProgressBar) findViewById(R.id.progress_fibre_horizontal);
        foodViewModel.getTotalFiber(dateOnly).observe(this, Float -> updateTotalFibreProgressBar(Float));

        progressTotalSodiumBar = (ProgressBar) findViewById(R.id.progress_sodium_horizontal);
        foodViewModel.getTotalSodium(dateOnly).observe(this, Float -> updateTotalSodiumProgressBar(Float));

        progressTotalPotassiumBar = (ProgressBar) findViewById(R.id.progress_potassium_horizontal);
        foodViewModel.getTotalPotassium(dateOnly).observe(this, Float -> updateTotalPotassiumProgressBar(Float));

        progressTotalSugarBar = (ProgressBar) findViewById(R.id.progress_sugar_horizontal);
        foodViewModel.getTotalSugar(dateOnly).observe(this, Float -> updateTotalSugarProgressBar(Float));

        progressTotalCholesterolBar = (ProgressBar) findViewById(R.id.progress_cholesterol_horizontal);
        foodViewModel.getTotalCholesterol(dateOnly).observe(this, Float -> updateTotalCholesterolProgressBar(Float));

        progressTotalFatSaturatedBar = (ProgressBar) findViewById(R.id.progress_fat_saturated_horizontal);
        foodViewModel.getTotalFatSat(dateOnly).observe(this, Float -> updateTotalFatSaturationProgressBar(Float));

        


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void updateTotalCaloriesProgressBar(Object calories) {
        if (calories != null) {


            float totalCaloriesEaten = (Float) calories;
//            calorieGoal= 800;
            int dashboardCalorieIntakePercentage = (int) ((totalCaloriesEaten / calorieGoal) * 100);
            dashboardCalorieIntakePercentageText.setText(dashboardCalorieIntakePercentage + "% done");

            dashboardCalorieIntakeGoalShow.setText(String.valueOf(round(totalCaloriesEaten, 1)) + "/" + calorieGoal);
            if (calorieGoal > totalCaloriesEaten)
                progressTotalCaloriesBar.setProgress(dashboardCalorieIntakePercentage);
            else
                progressTotalCaloriesBar.setProgress(100);
        }
        else{
            hideLayout();
            progressTotalCaloriesBar.setProgress(0);
            dashboardCalorieIntakePercentageText.setText("0% done");
            dashboardCalorieIntakeGoalShow.setText("0Cal");
        }
    }
    private void updateTotalProteinProgressBar(Object calories) {
        if (calories != null) {
            float totalCaloriesEaten = (Float) calories;
            int calorieGoal = 50;
            dashboardProteinIntake.setText(String.valueOf(round(totalCaloriesEaten, 1)) + "gm");

            if (calorieGoal > totalCaloriesEaten)
                progressTotalProteinBar.setProgress((int) ((totalCaloriesEaten / calorieGoal) * 100));
            else
                progressTotalProteinBar.setProgress(100);
        }
        else{
            progressTotalProteinBar.setProgress(0);
            dashboardProteinIntake.setText("0gm");
        }
    }
    private void updateTotalCarbsProgressBar(Object calories) {
        if (calories != null) {
            float totalCaloriesEaten = (Float) calories;
            int calorieGoal = 50;
            dashboardCarbsIntake.setText(String.valueOf(round(totalCaloriesEaten, 1)) + "gm");

            if (calorieGoal > totalCaloriesEaten)
                progressTotalCarbsBar.setProgress((int) ((totalCaloriesEaten / calorieGoal) * 100));
            else
                progressTotalCarbsBar.setProgress(100);
        }
        else{
            progressTotalCarbsBar.setProgress(0);
            dashboardCarbsIntake.setText("0gm");
        }
    }
    private void updateTotalFatsProgressBar(Object calories) {
        if (calories != null) {
            float totalCaloriesEaten = (Float) calories;
            int calorieGoal = 50;
            dashboardFatsIntake.setText(String.valueOf(round(totalCaloriesEaten, 1)) + "gm");

            if (calorieGoal > totalCaloriesEaten)
                progressTotalFatsBar.setProgress((int) ((totalCaloriesEaten / calorieGoal) * 100));
            else
                progressTotalFatsBar.setProgress(100);
        }
        else{
            progressTotalFatsBar.setProgress(0);
            dashboardFatsIntake.setText("0gm");
        }
    }
    private void updateTotalFibreProgressBar(Object calories) {
        if (calories != null) {
            float totalCaloriesEaten = (Float) calories;
            int calorieGoal = 50;
            dashboardFibreIntake.setText(String.valueOf(round(totalCaloriesEaten, 1)) + "gm");

            if (calorieGoal > totalCaloriesEaten)
                progressTotalFibreBar.setProgress((int) ((totalCaloriesEaten / calorieGoal) * 100));
            else
                progressTotalFibreBar.setProgress(100);
        }
        else{
            progressTotalFibreBar.setProgress(0);
            dashboardFibreIntake.setText("0gm");
        }
    }

    private void updateTotalSodiumProgressBar(Object calories) {
        if (calories != null) {
            float totalCaloriesEaten = (Float) calories;
            int calorieGoal = 50;
            dashboardSodiumIntake.setText(String.valueOf(round(totalCaloriesEaten, 1)) + "gm");

            if (calorieGoal > totalCaloriesEaten)
                progressTotalSodiumBar.setProgress((int) ((totalCaloriesEaten / calorieGoal) * 100));
            else
                progressTotalSodiumBar.setProgress(100);
        }
        else{
            progressTotalSodiumBar.setProgress(0);
            dashboardSodiumIntake.setText("0mg");
        }
    }

    private void updateTotalPotassiumProgressBar(Object calories) {
        if (calories != null) {
            float totalCaloriesEaten = (Float) calories;
            int calorieGoal = 50;
            dashboardPotassiumIntake.setText(String.valueOf(round(totalCaloriesEaten, 1)) + "gm");

            if (calorieGoal > totalCaloriesEaten)
                progressTotalPotassiumBar.setProgress((int) ((totalCaloriesEaten / calorieGoal) * 100));
            else
                progressTotalPotassiumBar.setProgress(100);
        }
        else{
            progressTotalPotassiumBar.setProgress(0);
            dashboardPotassiumIntake.setText("0mg");
        }
    }

    private void updateTotalSugarProgressBar(Object calories) {
        if (calories != null) {
            float totalCaloriesEaten = (Float) calories;
            int calorieGoal = 50;
            dashboardSugarIntake.setText(String.valueOf(round(totalCaloriesEaten, 1)) + "gm");

            if (calorieGoal > totalCaloriesEaten)
                progressTotalSugarBar.setProgress((int) ((totalCaloriesEaten / calorieGoal) * 100));
            else
                progressTotalSugarBar.setProgress(100);
        }
        else{
            progressTotalSugarBar.setProgress(0);
            dashboardSugarIntake.setText("0gm");
        }
    }

    private void updateTotalCholesterolProgressBar(Object calories) {
        if (calories != null) {
            float totalCaloriesEaten = (Float) calories;
            int calorieGoal = 50;
            dashboardCholesterolIntake.setText(String.valueOf(round(totalCaloriesEaten, 1)) + "gm");

            if (calorieGoal > totalCaloriesEaten)
                progressTotalCholesterolBar.setProgress((int) ((totalCaloriesEaten / calorieGoal) * 100));
            else
                progressTotalCholesterolBar.setProgress(100);
        }
        else{
            progressTotalCholesterolBar.setProgress(0);
            dashboardCholesterolIntake.setText("0mg");
        }
    }

    private void updateTotalFatSaturationProgressBar(Object calories) {
        if (calories != null) {
            float totalCaloriesEaten = (Float) calories;
            int calorieGoal = 50;
            dashboardFatSaturatedIntake.setText(String.valueOf(round(totalCaloriesEaten, 1)) + "gm");

            if (calorieGoal > totalCaloriesEaten)
                progressTotalFatSaturatedBar.setProgress((int) ((totalCaloriesEaten / calorieGoal) * 100));
            else
                progressTotalFatSaturatedBar.setProgress(100);
        }
        else{
            progressTotalFatSaturatedBar.setProgress(0);
            dashboardFatSaturatedIntake.setText("0gm");
        }
    }

    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    private void hideLayout()
    {

            noMealLayout.setVisibility(View.VISIBLE);
            foodAnalysisLayout.setVisibility(View.INVISIBLE);
            ViewGroup.MarginLayoutParams cardTodayDashboardParams = (ViewGroup.MarginLayoutParams) cardTodayDashboard.getLayoutParams();
            cardTodayDashboardParams.topMargin= 500;
    }
}

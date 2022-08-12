package com.example.iiitmtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    public ConstraintLayout activity_home_bg;
    public ImageView search_btn;
    public ImageView places_btn;
    public ImageView faculty_btn;
    public ImageView register_btn;
    public ImageView toggle_dark_btn;
    public TextView search_tv;
    public TextView schedule_tv;
    public TextView mess_tv;
    public TextView register_tv;
    public TextView faculty_tv;
    public TextView places_tv;
    public ImageView time_table_btn;
    public ImageView mess_btn;
    public FirebaseAuth mAuth;
    public FirebaseUser mUser;
    public boolean isDarkModeOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        activity_home_bg = findViewById(R.id.activity_home_bg);
        search_btn = findViewById(R.id.search_btn);
        places_btn = findViewById(R.id.places_btn);
        faculty_btn = findViewById(R.id.faculty_btn);
        register_btn = findViewById(R.id.register_btn);
        search_tv = findViewById(R.id.search_tv);
        register_tv = findViewById(R.id.register_tv);
        faculty_tv = findViewById(R.id.faculty_tv);
        places_tv = findViewById(R.id.places_tv);
        toggle_dark_btn = findViewById(R.id.toggle_dark_btn);
        time_table_btn = findViewById(R.id.time_table_btn);
        mess_btn = findViewById(R.id.mess_btn);
        schedule_tv = findViewById(R.id.time_table_tv);
        mess_tv = findViewById(R.id.mess_tv);

        // Initialize firebase auth
        mAuth= FirebaseAuth.getInstance();

        // Initialize firebase user
        mUser=mAuth.getCurrentUser();

        if(mUser!=null) {
            Drawable d = getResources().getDrawable(R.drawable.edit_icon);
            register_btn.setImageDrawable(d);
            register_tv.setText("Edit");
        }


        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(intent);

            }
        });

        places_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, PlacesActivity.class);
                startActivity(intent);
            }
        });

        faculty_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, FacultyActivity.class);
                startActivity(intent);
            }
        });


        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(HomeActivity.this, RegisterActivity.class);
                    startActivity(intent);

            }
        });

        mess_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MessActivity.class);
                startActivity(intent);
            }
        });

        time_table_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ScheduleActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);

        if (isDarkModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            toggle_dark_btn.setImageDrawable(getResources().getDrawable(R.drawable.moon_icon_filled));
            toggle_dark_btn.setColorFilter(Color.argb(255, 255, 255, 255));
            toggleViews(isDarkModeOn);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            toggle_dark_btn.setImageDrawable(getResources().getDrawable(R.drawable.moon_icon));
            toggleViews(isDarkModeOn);
        }

        toggle_dark_btn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view)
                    {
                        // When user taps the enable/disable
                        // dark mode button
                        if (isDarkModeOn) {

                            // if dark mode is on it
                            // will turn it off
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            // it will set isDarkModeOn
                            // boolean to false
                            editor.putBoolean("isDarkModeOn", false);
                            editor.apply();

                            // change text of Button
                            toggle_dark_btn.setImageDrawable(getResources().getDrawable(R.drawable.moon_icon));
                            toggleViews(isDarkModeOn);

                        }
                        else {

                            // if dark mode is off
                            // it will turn it on
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                            // it will set isDarkModeOn
                            // boolean to true
                            editor.putBoolean("isDarkModeOn", true);
                            editor.apply();

                            // change text of Button
                            toggle_dark_btn.setImageDrawable(getResources().getDrawable(R.drawable.moon_icon_filled));
                            toggle_dark_btn.setColorFilter(Color.argb(255, 255, 255, 255));
                            toggleViews(isDarkModeOn);
                        }
                    }
                });

    }

    public void toggleViews(boolean isDarkModeOn) {
        if(isDarkModeOn) {
            activity_home_bg.setBackgroundResource(R.drawable.background_2);
            search_btn.setColorFilter(Color.argb(255, 255, 255, 255));
            register_btn.setColorFilter(Color.argb(255, 255, 255, 255));
            faculty_btn.setColorFilter(Color.argb(255, 255, 255, 255));
            places_btn.setColorFilter(Color.argb(255, 255, 255, 255));
            time_table_btn.setColorFilter(Color.argb(255, 255, 255, 255));
            mess_btn.setColorFilter(Color.argb(255, 255, 255, 255));
            search_tv.setTextColor(Color.parseColor("#FFFFFF"));
            schedule_tv.setTextColor(Color.parseColor("#FFFFFF"));
            mess_tv.setTextColor(Color.parseColor("#FFFFFF"));
            register_tv.setTextColor(Color.parseColor("#FFFFFF"));
            faculty_tv.setTextColor(Color.parseColor("#FFFFFF"));
            places_tv.setTextColor(Color.parseColor("#FFFFFF"));
//            more_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttonlight));
        }
        else {
            activity_home_bg.setBackgroundResource(R.drawable.background_3);
            search_btn.setColorFilter(Color.argb(0, 0, 0, 0));
            register_btn.setColorFilter(Color.argb(0, 0, 0, 0));
            faculty_btn.setColorFilter(Color.argb(0, 0, 0, 0));
            places_btn.setColorFilter(Color.argb(0, 0, 0, 0));
            time_table_btn.setColorFilter(Color.argb(0, 0, 0, 0));
            mess_btn.setColorFilter(Color.argb(0, 0, 0, 0));
            search_tv.setTextColor(Color.parseColor("#000000"));
            schedule_tv.setTextColor(Color.parseColor("#000000"));
            mess_tv.setTextColor(Color.parseColor("#000000"));
            register_tv.setTextColor(Color.parseColor("#000000"));
            faculty_tv.setTextColor(Color.parseColor("#000000"));
            places_tv.setTextColor(Color.parseColor("#000000"));
//            more_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttondark));
        }
    }
}

package com.example.iiitmtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ScheduleActivity extends AppCompatActivity {

    public ConstraintLayout schedule_activity_bg;
    public FirebaseFirestore db;
    public boolean isDarkModeOn;
    public Button imt2018_btn;
    public Button img2018_btn;
    public Button img2019_btn;
    public Button imt2019_btn;
    public Button bcs2019_btn;
    public Button bcs2020_btn;
    public Button imt2020_btn;
    public Button img2020_btn;
    public Button img2021_btn;
    public Button imt2021_btn;
    public Button bcs2021_btn;
    public Button global_btn;
    public Button detect_btn;
    public TextView y2018_tv;
    public TextView y2019_tv;
    public TextView y2020_tv;
    public TextView y2021_tv;
    public FirebaseAuth mAuth;
    public FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        getSupportActionBar().hide();
        schedule_activity_bg = findViewById(R.id.schedule_activity_bg);
        imt2018_btn = findViewById(R.id.imt2018_btn);
        imt2019_btn = findViewById(R.id.imt2019_btn);
        imt2020_btn = findViewById(R.id.imt2020_btn);
        imt2021_btn = findViewById(R.id.imt2021_btn);
        img2021_btn = findViewById(R.id.img2021_btn);
        img2020_btn = findViewById(R.id.img2020_btn);
        img2019_btn = findViewById(R.id.img2019_btn);
        img2018_btn = findViewById(R.id.img2018_btn);
        bcs2019_btn = findViewById(R.id.bcs2019_btn);
        bcs2020_btn = findViewById(R.id.bcs2020_btn);
        bcs2021_btn = findViewById(R.id.bcs2021_btn);
        detect_btn = findViewById(R.id.detect_btn);
        global_btn = findViewById(R.id.global_btn);
        y2018_tv = findViewById(R.id.y2018_tv);
        y2019_tv = findViewById(R.id.y2019_tv);
        y2020_tv = findViewById(R.id.y2020_tv);
        y2021_tv = findViewById(R.id.y2021_tv);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);
        toggleDarkMode(isDarkModeOn);

        imt2018_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("timetables").document("2018IMT").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String url = documentSnapshot.get("url").toString();
                        if(!url.equalsIgnoreCase("")) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(browserIntent);
                        }
                        else {
                            Toast.makeText(ScheduleActivity.this, "Document does not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        imt2019_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("timetables").document("2019IMT").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String url = documentSnapshot.get("url").toString();
                        if(!url.equalsIgnoreCase("")) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(browserIntent);
                        }
                        else {
                            Toast.makeText(ScheduleActivity.this, "Document does not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        imt2020_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("timetables").document("2020IMT").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String url = documentSnapshot.get("url").toString();
                        if(!url.equalsIgnoreCase("")) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(browserIntent);
                        }
                        else {
                            Toast.makeText(ScheduleActivity.this, "Document does not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        imt2021_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("timetables").document("2021IMT").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String url = documentSnapshot.get("url").toString();
                        if(!url.equalsIgnoreCase("")) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(browserIntent);
                        }
                        else {
                            Toast.makeText(ScheduleActivity.this, "Document does not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        img2018_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("timetables").document("2018IMG").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String url = documentSnapshot.get("url").toString();
                        if(!url.equalsIgnoreCase("")) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(browserIntent);
                        }
                        else {
                            Toast.makeText(ScheduleActivity.this, "Document does not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        img2019_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("timetables").document("2019IMG").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String url = documentSnapshot.get("url").toString();
                        if(!url.equalsIgnoreCase("")) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(browserIntent);
                        }
                        else {
                            Toast.makeText(ScheduleActivity.this, "Document does not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        img2020_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("timetables").document("2020IMG").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String url = documentSnapshot.get("url").toString();
                        if(!url.equalsIgnoreCase("")) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(browserIntent);
                        }
                        else {
                            Toast.makeText(ScheduleActivity.this, "Document does not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        img2021_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("timetables").document("2021IMG").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String url = documentSnapshot.get("url").toString();
                        if(!url.equalsIgnoreCase("")) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(browserIntent);
                        }
                        else {
                            Toast.makeText(ScheduleActivity.this, "Document does not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


        bcs2019_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("timetables").document("2019BCS").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String url = documentSnapshot.get("url").toString();
                        if(!url.equalsIgnoreCase("")) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(browserIntent);
                        }
                        else {
                            Toast.makeText(ScheduleActivity.this, "Document does not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        bcs2020_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("timetables").document("2020BCS").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String url = documentSnapshot.get("url").toString();
                        if(!url.equalsIgnoreCase("")) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(browserIntent);
                        }
                        else {
                            Toast.makeText(ScheduleActivity.this, "Document does not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        bcs2021_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("timetables").document("2021BCS").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String url = documentSnapshot.get("url").toString();
                        if(!url.equalsIgnoreCase("")) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(browserIntent);
                        }
                        else {
                            Toast.makeText(ScheduleActivity.this, "Document does not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        global_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("docs").document("timetable").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String url = documentSnapshot.get("url").toString();
                        if(!url.equalsIgnoreCase("")) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(browserIntent);
                        }
                        else {
                            Toast.makeText(ScheduleActivity.this, "Document does not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        detect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("students").document(mUser.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String batch = documentSnapshot.get("batch").toString();
                        if(!batch.equalsIgnoreCase("")) {
                            db.collection("timetables").document(batch).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    Toast.makeText(ScheduleActivity.this, batch, Toast.LENGTH_SHORT).show();
                                    String url = documentSnapshot.get("url").toString();
                                    if(!url.equalsIgnoreCase("")) {
                                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                        startActivity(browserIntent);
                                    }
                                    else {
                                        Toast.makeText(ScheduleActivity.this, "Document does not exist!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(ScheduleActivity.this, "Couldn't detect batch", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });




    }

    public void toggleDarkMode(boolean isDarkModeOn) {
        if(isDarkModeOn) {
            schedule_activity_bg.setBackgroundResource(R.drawable.background_2);
            bcs2019_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttondark));
            bcs2020_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttondark));
            bcs2021_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttondark));
            imt2019_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttondark));
            imt2018_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttondark));
            imt2020_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttondark));
            imt2021_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttondark));
            img2019_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttondark));
            img2018_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttondark));
            img2020_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttondark));
            img2021_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttondark));
            detect_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttondark));
            global_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttondark));
            y2018_tv.setTextColor(Color.parseColor("#FFFFFF"));
            y2019_tv.setTextColor(Color.parseColor("#FFFFFF"));
            y2020_tv.setTextColor(Color.parseColor("#FFFFFF"));
            y2021_tv.setTextColor(Color.parseColor("#FFFFFF"));

        }
        else {
            schedule_activity_bg.setBackgroundResource(R.drawable.background_3);
            bcs2019_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttonlight));
            bcs2020_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttonlight));
            bcs2021_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttonlight));
            imt2019_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttonlight));
            imt2018_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttonlight));
            imt2020_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttonlight));
            imt2021_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttonlight));
            img2019_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttonlight));
            img2018_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttonlight));
            img2020_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttonlight));
            img2021_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttonlight));
            detect_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttonlight));
            global_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttonlight));
            y2018_tv.setTextColor(Color.parseColor("#000000"));
            y2019_tv.setTextColor(Color.parseColor("#000000"));
            y2020_tv.setTextColor(Color.parseColor("#000000"));
            y2021_tv.setTextColor(Color.parseColor("#000000"));

        }
    }
}
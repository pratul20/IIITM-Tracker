package com.example.iiitmtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MoreActivity extends AppCompatActivity {

    public ConstraintLayout more_activity_bg;
    public Button tt_btn;
    public Button bh1_btn;
    public Button bh2_btn;
    public Button bh3_btn;
    public Button gh_btn;
    public Boolean isDarkModeOn;
    public FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        getSupportActionBar().hide();
        more_activity_bg = findViewById(R.id.more_activity_bg);
        tt_btn = findViewById(R.id.tt_btn);
        bh1_btn = findViewById(R.id.bh1_btn);
        bh2_btn = findViewById(R.id.bh2_btn);
        bh3_btn = findViewById(R.id.bh3_btn);
        gh_btn = findViewById(R.id.gh_btn);
        db = FirebaseFirestore.getInstance();

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);
        toggleDarkMode(isDarkModeOn);

        tt_btn.setOnClickListener(new View.OnClickListener() {
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
                            Toast.makeText(MoreActivity.this, "Document does not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        bh1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("docs").document("bh1messmenu").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String url = documentSnapshot.get("url").toString();
                        if(!url.equalsIgnoreCase("")) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(browserIntent);
                        }
                        else {
                            Toast.makeText(MoreActivity.this, "Document does not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        bh2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("docs").document("bh2messmenu").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String url = documentSnapshot.get("url").toString();
                        if(!url.equalsIgnoreCase("")) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(browserIntent);
                        }
                        else {
                            Toast.makeText(MoreActivity.this, "Document does not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        bh3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("docs").document("bh3messmenu").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String url = documentSnapshot.get("url").toString();
                        if(!url.equalsIgnoreCase("")) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(browserIntent);
                        }
                        else {
                            Toast.makeText(MoreActivity.this, "Document does not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        gh_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("docs").document("ghmessmenu").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String url = documentSnapshot.get("url").toString();
                        if(!url.equalsIgnoreCase("")) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(browserIntent);
                        }
                        else {
                            Toast.makeText(MoreActivity.this, "Document does not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    public void toggleDarkMode(Boolean isDarkModeOn) {
        if(isDarkModeOn) {
            more_activity_bg.setBackgroundResource(R.drawable.background_2);
            tt_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttondark));
            gh_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttondark));
            bh1_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttondark));
            bh2_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttondark));
            bh3_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttondark));
        }
        else {
            more_activity_bg.setBackgroundResource(R.drawable.background_3);
            tt_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttonlight));
            gh_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttonlight));
            bh1_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttonlight));
            bh2_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttonlight));
            bh3_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttonlight));

        }
    }
}
package com.example.iiitmtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.iiitmtracker.adapter.displayFaculty;
import com.example.iiitmtracker.model.Faculty;
import com.example.iiitmtracker.model.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentSkipListMap;

public class DisplayStudent extends AppCompatActivity {

    public boolean isDarkModeOn;
    public ConstraintLayout display_student_bg;
    public TextView student_name_tv;
    public ImageView student_image_iv;
    public TextView RollNo;
    public TextView roll_no_tv;
    public TextView Mobile;
    public TextView mobile_tv;
    public ImageView whatsapp_iv;
    public TextView RoomNo;
    public TextView room_no_tv;
    public TextView Email;
    public RatingBar rating_bar;
    public TextView rating_tv;
    public TextView ProfileViews;
    public TextView profile_views_tv;
    public TextView email_tv;
    public TextView TechInterests;
    public TextView tech_interests_tv;
    public TextView LinkedinUrl;
    public TextView linkedin_url_tv;
    public String id;
    public FirebaseFirestore db;
    public int views;
//    public Dialog myDialog;
//    public ImageView popup_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_student);
        getSupportActionBar().hide();
        student_name_tv = findViewById(R.id.student_name_tv);
        student_image_iv = findViewById(R.id.place_image_iv);
        RollNo = findViewById(R.id.RollNo);
        Mobile = findViewById(R.id.Mobile);
        RoomNo = findViewById(R.id.RoomNo);
        Email = findViewById(R.id.Email);
        TechInterests = findViewById(R.id.TechInterests);
        roll_no_tv = findViewById(R.id.roll_no_tv);
        mobile_tv = findViewById(R.id.mobile_tv);
        whatsapp_iv = findViewById(R.id.whatsapp_iv);
        room_no_tv = findViewById(R.id.room_no_tv);
        email_tv = findViewById(R.id.email_tv);
        tech_interests_tv = findViewById(R.id.tech_interests_tv);
        rating_bar = findViewById(R.id.rating_bar);
        rating_tv = findViewById(R.id.rating_tv);
        ProfileViews = findViewById(R.id.ProfileViews);
        profile_views_tv = findViewById(R.id.profile_views_tv);
        LinkedinUrl = findViewById(R.id.LinkedinUrl);
        linkedin_url_tv = findViewById(R.id.linkedin_url_tv);
        db = FirebaseFirestore.getInstance();
//        myDialog = new Dialog(this);
//        myDialog.setContentView(R.layout.imagepopup);
//        popup_iv = myDialog.findViewById(R.id.popup_iv);

        Bundle data = getIntent().getExtras();
        Student student = (Student) data.getParcelable("student");

        Map<String, Object> tbu = new HashMap<>();
        views = Integer.parseInt(student.getViews());
        views+=1;
        tbu.put("views",Integer.toString(views));
        db.collection("students").document(student.getId()).update(tbu).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Log.d("firestore","document added");
                    profile_views_tv.setText(Integer.toString(views));
                }
                else {
                    Log.d("firestore","couldn't add document");
                    profile_views_tv.setText(Integer.toString(views));
                }
            }
        });




        if(!student.getImage_link().isEmpty()) {
            Glide.with(this).load(student.getImage_link()).into(student_image_iv);
        }
        else {
            Drawable d = getResources().getDrawable(R.drawable.user_icon);
            student_image_iv.setImageDrawable(d);
        }

//        student_image_iv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                myDialog.show();
//            }
//        });
        rating_tv.setText(student.getRating()+"/5");

        rating_bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                Log.d("rating","rating is: "+student.getRating());
                float currRating = Float.parseFloat(student.getRating());
                int people_rated = Integer.parseInt(student.getNo_of_people_rated());
                people_rated+=1;
                float newRating = ((currRating * (people_rated-1)) + rating) / (people_rated);
                Map<String, Object> user = new HashMap<>();
                user.put("rating",Float.toString(newRating));
                user.put("no_of_people_rated", Integer.toString(people_rated));
                db.collection("students").document(student.getId())
                        .update(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Log.d("firestore","document added");
                            Toast.makeText(DisplayStudent.this, "Rated "+rating+"/5", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Log.d("firestore","couldn't add document");
                            Toast.makeText(DisplayStudent.this, "Please check your internet", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                rating_tv.setText(String.valueOf(newRating)+"/5");
            }
        });

        if(student.getLinkedin_url().equalsIgnoreCase("")) {
            linkedin_url_tv.setText("N/A");
        }
        else {
            linkedin_url_tv.setText(student.getLinkedin_url());
            linkedin_url_tv.setTextColor(Color.parseColor("#005FFF"));
            if(isDarkModeOn) {
                linkedin_url_tv.setTextColor(Color.parseColor("#0127FF"));
            }
            linkedin_url_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = student.getLinkedin_url();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(browserIntent);
                }
            });
        }

        profile_views_tv.setText(student.getViews());

        student_name_tv.setText(student.getName());
        if(!student.getRoll_no().isEmpty()) {
            roll_no_tv.setText(student.getRoll_no());
        }
        else {
            roll_no_tv.setText("N/A");
        }
        if(student.getMobile_no().isEmpty()) {
            mobile_tv.setText("N/A");
            whatsapp_iv.setVisibility(View.GONE);
            ViewGroup.LayoutParams layoutParams = Mobile.getLayoutParams();
            layoutParams.width = 530;
            Mobile.setLayoutParams(layoutParams);

        }
        else {
            mobile_tv.setText(student.getMobile_no());
            mobile_tv.setTextColor(Color.parseColor("#005FFF"));
            mobile_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String number = student.getMobile_no().toString();
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));// Initiates the Intent
                    startActivity(intent);
                }
            });
            mobile_tv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    String number = student.getMobile_no().toString();
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Phone number copied!", number);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(DisplayStudent.this , "Phone number copied!", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
            whatsapp_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = "https://wa.me/"+student.getMobile_no();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(browserIntent);
                }
            });
        }
        if(!student.getRoom_no().isEmpty()) {
            room_no_tv.setText(student.getRoom_no());
        }
        else {
            room_no_tv.setText("N/A");
        }
        email_tv.setText(student.getEmail());
        email_tv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String email = student.getEmail().toString();
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Email copied!", email);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(DisplayStudent.this , "Email copied!", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        if(!student.getInterests().isEmpty()) {
            String interests = student.getInterests();
            StringTokenizer st = new StringTokenizer(interests,",");
            ArrayList<String> tokens= new ArrayList<>();
            while (st.hasMoreTokens()) {
                tokens.add(st.nextToken());
            }
            String tbp="";
            for(String token: tokens) {
                tbp += "• " + token + "\n\n";

            }
            tech_interests_tv.setText(tbp);
        }
        else {
            tech_interests_tv.setText("N/A");
        }
        if(!student.getImage_link().isEmpty()) {
            Glide.with(this).load(student.getImage_link()).into(student_image_iv);
//            Glide.with(this).load(student.getImage_link()).into(popup_iv);
        }
        else {
            Drawable d = getResources().getDrawable(R.drawable.user_icon);
            student_image_iv.setImageDrawable(d);
        }

        display_student_bg = findViewById(R.id.display_student_bg);
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);
        toggleDarkMode(isDarkModeOn);
    }

    public void toggleDarkMode(@NonNull Boolean isDarkModeOn) {
        if(isDarkModeOn) {
            display_student_bg.setBackgroundResource(R.drawable.background_2);
            student_name_tv.setTextColor(Color.parseColor("#FFFFFF"));
            RollNo.setTextColor(Color.parseColor("#FFFFFF"));
            RoomNo.setTextColor(Color.parseColor("#FFFFFF"));
            Mobile.setTextColor(Color.parseColor("#FFFFFF"));
            Email.setTextColor(Color.parseColor("#FFFFFF"));
            TechInterests.setTextColor(Color.parseColor("#FFFFFF"));
            roll_no_tv.setTextColor(Color.parseColor("#FFFFFF"));
            room_no_tv.setTextColor(Color.parseColor("#FFFFFF"));
            mobile_tv.setTextColor(Color.parseColor("#FFFFFF"));
            email_tv.setTextColor(Color.parseColor("#FFFFFF"));
            tech_interests_tv.setTextColor(Color.parseColor("#FFFFFF"));
            linkedin_url_tv.setTextColor(Color.parseColor("#FFFFFF"));
            LinkedinUrl.setTextColor(Color.parseColor("#FFFFFF"));
        }
        else{
            display_student_bg.setBackgroundResource(R.drawable.background_3);
            student_name_tv.setTextColor(Color.parseColor("#000000"));
            RollNo.setTextColor(Color.parseColor("#000000"));
            RoomNo.setTextColor(Color.parseColor("#000000"));
            Mobile.setTextColor(Color.parseColor("#000000"));
            Email.setTextColor(Color.parseColor("#000000"));
            TechInterests.setTextColor(Color.parseColor("#000000"));
            roll_no_tv.setTextColor(Color.parseColor("#000000"));
            room_no_tv.setTextColor(Color.parseColor("#000000"));
            mobile_tv.setTextColor(Color.parseColor("#000000"));
            email_tv.setTextColor(Color.parseColor("#000000"));
            tech_interests_tv.setTextColor(Color.parseColor("#000000"));
            linkedin_url_tv.setTextColor(Color.parseColor("#000000"));
            LinkedinUrl.setTextColor(Color.parseColor("#000000"));
        }
    }
}
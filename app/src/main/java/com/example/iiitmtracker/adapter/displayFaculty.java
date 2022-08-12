package com.example.iiitmtracker.adapter;

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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iiitmtracker.R;
import com.example.iiitmtracker.helper.FacImage;
import com.example.iiitmtracker.model.Faculty;

public class displayFaculty extends AppCompatActivity {

    public ConstraintLayout display_faculty_bg;
    public TextView faculty_name_tv;
    public TextView designation_tv;
    public TextView honour_tv;
    public TextView office_phone_tv;
    public TextView residence_phone_tv;
    public TextView mobile_tv;
    public TextView email_tv;
    public TextView address_tv;
    public TextView Mobile;
    public TextView Designation;
    public TextView OfficePhone;
    public TextView ResidencePhone;
    public TextView Address;
    public TextView Honour;
    public TextView Email;
    public ImageView faculty_image_iv;
    public ImageView directions_iv;
    public ImageView website_iv;
    public ImageView whatsapp_iv;
    public Dialog myDialog;
    public ImageView popup_iv;
    public int resource_id;
    public boolean isDarkModeOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_faculty);
        getSupportActionBar().hide();

        display_faculty_bg = findViewById(R.id.display_faculty_bg);
        faculty_name_tv = findViewById(R.id.student_name_tv);
        designation_tv = findViewById(R.id.roll_no_tv);
        honour_tv = findViewById(R.id.honour_tv);
        office_phone_tv = findViewById(R.id.room_no_tv);
        residence_phone_tv = findViewById(R.id.tech_interests_tv);
        mobile_tv = findViewById(R.id.mobile_tv);
        Mobile = findViewById(R.id.Mobile);
        Designation = findViewById(R.id.RollNo);
        OfficePhone = findViewById(R.id.RoomNo);
        ResidencePhone = findViewById(R.id.TechInterests);
        Address = findViewById(R.id.Address);
        Email = findViewById(R.id.Email);
        Honour = findViewById(R.id.Honour);
        email_tv = findViewById(R.id.email_tv);
        address_tv = findViewById(R.id.address_tv);
        faculty_image_iv = findViewById(R.id.place_image_iv);
        directions_iv = findViewById(R.id.directions_iv);
        website_iv = findViewById(R.id.website_iv);
        whatsapp_iv = findViewById(R.id.whatsapp_iv);
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.imagepopup);
        popup_iv = myDialog.findViewById(R.id.popup_iv);

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);
        toggleDarkMode(isDarkModeOn);

        Bundle data = getIntent().getExtras();
        Faculty faculty = (Faculty) data.getParcelable("faculty");

        website_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(displayFaculty.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });

        faculty_image_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();
            }
        });

//        resource_id = Integer.parseInt(faculty.getImage_link());
//        Toast.makeText(this, "Name: "+faculty.getName()+"\nHonour: "+faculty.getHonour(), Toast.LENGTH_SHORT).show();
        FacImage facImage = new FacImage(getApplicationContext());
        Drawable d = facImage.getFacultyImage(faculty.getName());
        popup_iv.setImageDrawable(d);
        faculty_image_iv.setImageDrawable(d);
//        faculty_image_iv.setImageDrawable(resource_id);
        faculty_name_tv.setText(faculty.getName());
        designation_tv.setText(faculty.getDesignation());
        honour_tv.setText(faculty.getHonour());
        office_phone_tv.setText(faculty.getOffice_phone());
        office_phone_tv.setTextColor(Color.parseColor("#005FFF"));
        if(isDarkModeOn) {
            office_phone_tv.setTextColor(Color.parseColor("#0127FF"));
        }
        if(faculty.getResidence_phone().isEmpty()) {
            residence_phone_tv.setText("N/A");
        }
        else {
            residence_phone_tv.setText(faculty.getResidence_phone());
            residence_phone_tv.setTextColor(Color.parseColor("#005FFF"));
            if(isDarkModeOn) {
                residence_phone_tv.setTextColor(Color.parseColor("#0127FF"));
            }
            residence_phone_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String number = faculty.getResidence_phone().toString();
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));// Initiates the Intent
                    startActivity(intent);
                }
            });
            residence_phone_tv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    String number = faculty.getResidence_phone().toString();
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Phone number copied!", number);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(displayFaculty.this , "Phone number copied!", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }
        if(faculty.getMobile().isEmpty()) {
            mobile_tv.setText("N/A");
            whatsapp_iv.setVisibility(View.GONE);
            ViewGroup.LayoutParams layoutParams = Mobile.getLayoutParams();
            layoutParams.width = 530;
            Mobile.setLayoutParams(layoutParams);

        }
        else {
            mobile_tv.setText(faculty.getMobile());
            mobile_tv.setTextColor(Color.parseColor("#005FFF"));
            if(isDarkModeOn) {
                mobile_tv.setTextColor(Color.parseColor("#0127FF"));
            }
            mobile_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String number = faculty.getMobile().toString();
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));// Initiates the Intent
                    startActivity(intent);
                }
            });
            mobile_tv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    String number = faculty.getMobile().toString();
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Phone number copied!", number);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(displayFaculty.this , "Phone number copied!", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
            whatsapp_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = "https://wa.me/"+faculty.getMobile();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(browserIntent);
                }
            });
        }
        if(faculty.getAddress().isEmpty()) {
            address_tv.setText("N/A");
        }
        else {
            address_tv.setText(faculty.getAddress());
        }
        email_tv.setText(faculty.getEmail());
        email_tv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String email = faculty.getEmail().toString();
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Email copied!", email);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(displayFaculty.this , "Email copied!", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        office_phone_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = faculty.getOffice_phone().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));// Initiates the Intent
                startActivity(intent);
            }
        });
        office_phone_tv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String number = faculty.getOffice_phone().toString();
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Phone number copied!", number);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(displayFaculty.this , "Phone number copied!", Toast.LENGTH_SHORT).show();
                return true;
            }
        });



        directions_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!faculty.getLongitude().isEmpty() && !faculty.getLatitude().isEmpty()) {
                    String url = "http://maps.google.com/maps?daddr=" + faculty.getLatitude() + "," + faculty.getLongitude();
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,Uri.parse(url));
                    startActivity(intent);
                }
                else {
                    Toast.makeText(displayFaculty.this, "Location not Found", Toast.LENGTH_SHORT).show();
                }

            }
        });

        website_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(faculty.getWebsite()));
                startActivity(browserIntent);
            }
        });

    }

    public void toggleDarkMode(boolean isDarkModeOn) {
        if(isDarkModeOn) {
            display_faculty_bg.setBackgroundResource(R.drawable.background_2);
            website_iv.setColorFilter(Color.argb(255, 37, 187, 255));
            directions_iv.setColorFilter(Color.argb(255, 37, 187, 255));
            faculty_name_tv.setTextColor(Color.parseColor("#FFFFFF"));
            Designation.setTextColor(Color.parseColor("#FFFFFF"));
            Honour.setTextColor(Color.parseColor("#FFFFFF"));
            OfficePhone.setTextColor(Color.parseColor("#FFFFFF"));
            ResidencePhone.setTextColor(Color.parseColor("#FFFFFF"));
            Mobile.setTextColor(Color.parseColor("#FFFFFF"));
            Email.setTextColor(Color.parseColor("#FFFFFF"));
            Address.setTextColor(Color.parseColor("#FFFFFF"));

        }
        else {
            display_faculty_bg.setBackgroundResource(R.drawable.background_3);
            website_iv.setColorFilter(Color.argb(255, 9, 85, 143));
            directions_iv.setColorFilter(Color.argb(255, 9, 85, 143));
            faculty_name_tv.setTextColor(Color.parseColor("#000000"));
            Designation.setTextColor(Color.parseColor("#000000"));
            Honour.setTextColor(Color.parseColor("#000000"));
            OfficePhone.setTextColor(Color.parseColor("#000000"));
            ResidencePhone.setTextColor(Color.parseColor("#000000"));
            Mobile.setTextColor(Color.parseColor("#000000"));
            Email.setTextColor(Color.parseColor("#000000"));
            Address.setTextColor(Color.parseColor("#000000"));

        }
    }


}
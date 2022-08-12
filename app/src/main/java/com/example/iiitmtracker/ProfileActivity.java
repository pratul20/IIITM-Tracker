package com.example.iiitmtracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.iiitmtracker.model.Student;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class ProfileActivity extends AppCompatActivity {
    public ImageView student_iv;
    public ImageView edit_img_iv;
    public ConstraintLayout profile_activity_bg;
    public Button delete_btn;
    public Button save_btn;
    public TextView Name;
    public TextView RollNo;
    public TextView Mobile;
    public TextView RoomNo;
    public TextView Email;
    public TextView TechInterests;
    public EditText name_et;
    public TextView roll_no_et;
    public EditText mobile_et;
    public EditText room_no_et;
    public TextView email_et;
    public String no_of_poeple_rated;
    public String profile_views;
    public String rating;
    public EditText interests_et;
    public TextView LinkedinURL;
    public EditText linkedin_url_et;
    public FirebaseAuth mAuth;
    public String image_link;
    public FirebaseUser mUser;
    public GoogleSignInClient googleSignInClient;
    public FirebaseFirestore db;
    public FirebaseStorage storage;
    public StorageReference storageReference;
    public ChipGroup chip_group;
    public final int PICK_IMAGE = 1;
    public boolean isDarkModeOn;
    public Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().hide();

        // Assign variable
        student_iv = findViewById(R.id.place_image_iv);
        edit_img_iv = findViewById(R.id.edit_img_iv);
        delete_btn=findViewById(R.id.delete_btn);
        save_btn = findViewById(R.id.save_btn);
        Name = findViewById(R.id.Name);
        RollNo = findViewById(R.id.RollNo);
        RoomNo = findViewById(R.id.RoomNo);
        Mobile = findViewById(R.id.Mobile);
        Email = findViewById(R.id.Email);
        TechInterests = findViewById(R.id.TechInterests);
        name_et = findViewById(R.id.name_et);
        roll_no_et = findViewById(R.id.roll_no_et);
        mobile_et = findViewById(R.id.mobile_et);
        room_no_et = findViewById(R.id.room_no_et);
        email_et = findViewById(R.id.email_et);
        LinkedinURL = findViewById(R.id.LinkedinUrl);
        linkedin_url_et = findViewById(R.id.linkedin_url_et);
//        interests_et = findViewById(R.id.interests_et);
        profile_activity_bg = findViewById(R.id.profile_activity_bg);
        chip_group = findViewById(R.id.filter_chip_group);
        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        no_of_poeple_rated = "0";
        profile_views = "0";

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);

//        email_et.setE

        toggleDarkMode(isDarkModeOn);

        // Initialize firebase auth
        mAuth=FirebaseAuth.getInstance();

        // Initialize firebase user
        mUser=mAuth.getCurrentUser();

        // Check condition
        if(mUser!=null)
        {
            // When firebase user is not equal to null
            // Set image on image view
            Glide.with(ProfileActivity.this)
                    .load(mUser.getPhotoUrl())
                    .into(student_iv);
            image_link = mUser.getPhotoUrl().toString();
//             set name on text view
            name_et.setText(mUser.getDisplayName());
            String email = mUser.getEmail();
            email_et.setText(email);
            String domain = email.substring(email.length()-11);
            if(domain.equalsIgnoreCase( "iiitm.ac.in")) {
                String roll_no = email.substring(4,8) + email.substring(0,3).toUpperCase() + "-" + email.substring(8,11);
                roll_no_et.setText(roll_no);
            }

            if(!mUser.getUid().isEmpty()) {
                db.collection("students").document(mUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()) {
                                Student student = document.toObject(Student.class);
                                if(student!=null) {
                                    profile_views = student.getViews();
                                    no_of_poeple_rated = student.getNo_of_people_rated();
                                    rating = student.getRating();
                                    if(!student.getMobile_no().isEmpty()) {
                                        mobile_et.setText(student.getMobile_no());
                                    }
                                    if(!student.getInterests().isEmpty()) {
                                        String interests = student.getInterests();
                                        StringTokenizer st = new StringTokenizer(interests,",");
                                        ArrayList<String> tokens= new ArrayList<>();
                                        while (st.hasMoreTokens()) {
                                            tokens.add(st.nextToken());
                                        }
                                        for(String token:tokens) {
                                            Log.d("token",token+"\n");
                                        }
                                        for(int i=0;i<chip_group.getChildCount();i++) {
                                            Chip chip = (Chip)chip_group.getChildAt(i);
                                            for(String token: tokens) {
                                                if(chip.getText().toString().equalsIgnoreCase(token)) {
                                                    chip.setChecked(true);
                                                }
                                            }
                                        }
                                    }
                                    if(!student.getRoom_no().isEmpty()) {
                                        room_no_et.setText(student.getRoom_no());
                                    }
                                    if(!student.getRoll_no().isEmpty()) {
                                        roll_no_et.setText(student.getRoll_no());
                                    }
                                    if(!student.getImage_link().isEmpty()) {
                                        Glide.with(getApplicationContext()).load(student.getImage_link()).into(student_iv);
                                        image_link=student.getImage_link();
                                    }
                                    if(!student.getLinkedin_url().isEmpty()) {
                                        linkedin_url_et.setText(student.getLinkedin_url());
                                    }

                                }
                            }
                            else {
                                Log.d("firstore","No such document exists");
                            }
                        }
                        else {
                            Log.d("firestore",task.getException().toString());
                        }
                    }
                });
            }
        }

        // Initialize sign in client
        googleSignInClient= GoogleSignIn.getClient(ProfileActivity.this
                , GoogleSignInOptions.DEFAULT_SIGN_IN);

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                db.collection("students").document(mUser.getUid())
//                .delete()
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            Log.d("firestore", "DocumentSnapshot successfully deleted!");
//                            Toast.makeText(ProfileActivity.this, "Profile Deleted", Toast.LENGTH_SHORT).show();
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Log.w("firestore", "Error deleting document", e);
//                            Toast.makeText(ProfileActivity.this, "Cannot delete profile!", Toast.LENGTH_SHORT).show();
//                        }
//                    });

                googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            mAuth.signOut();
                            Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
                }
        });

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String interests = "";
                for(int i=0;i<chip_group.getChildCount();i++) {
                    Chip chip = (Chip)chip_group.getChildAt(i);
                    if(chip.isChecked()) {
                        interests+=chip.getText().toString();
                        interests+=",";
                    }
                }
                if(interests.length()!=0) {
                    interests = interests.substring(0, interests.length()-1);
//                    Log.d("interests",interests)
                }

                if(room_no_et.getText().toString().equalsIgnoreCase("") || linkedin_url_et.getText().toString().equalsIgnoreCase("") || mobile_et.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(ProfileActivity.this, "All fields are mandatory!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d("debugging", "size: "+mobile_et.getText().toString().length());
                if(!(mobile_et.getText().toString().length()==10 || mobile_et.getText().toString().length()==14)) {
                    Toast.makeText(ProfileActivity.this, "Please Enter valid mobile number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!linkedin_url_et.getText().toString().contains("linkedin.com/in")) {
                    Toast.makeText(ProfileActivity.this, "Please Enter valid linkedin URL", Toast.LENGTH_SHORT).show();
                    return;
                }
//                Log.d("my",mobile_et.getText())
                if(!mobile_et.getText().toString().equals("")) {
                    if(mobile_et.getText().toString().charAt(0)!='+') {
                        mobile_et.setText("+91-"+mobile_et.getText().toString());
                    }
                }



                Map<String, Object> user = new HashMap<>();

                if(!no_of_poeple_rated.equalsIgnoreCase("0")) {
                    user.put("no_of_people_rated", no_of_poeple_rated);
                }
                else {
                    user.put("no_of_people_rated", "0");
                }

                if(!profile_views.equalsIgnoreCase("0")) {
                    user.put("views", profile_views);
                }
                else {
                    user.put("views", "0");
                }
                if(!rating.equalsIgnoreCase("3")) {
                    user.put("rating", rating);
                }
                else {
                    user.put("rating","3");
                }


                user.put("id",mUser.getUid());
                user.put("name", name_et.getText().toString());
                user.put("email", email_et.getText().toString());
                user.put("roll_no", roll_no_et.getText().toString());
                user.put("interests", interests);
                user.put("room_no", room_no_et.getText().toString());
                user.put("mobile_no", mobile_et.getText().toString());
                user.put("batch",roll_no_et.getText().toString().substring(0,7));
                user.put("linkedin_url", linkedin_url_et.getText().toString());
                if(mUser.getPhotoUrl().toString().equalsIgnoreCase(image_link)) {
                    user.put("image_link",mUser.getPhotoUrl().toString());
                }
                else {
                    user.put("image_link",image_link);
                }

                // Add a new document with a generated ID
                db.collection("students").document(mUser.getUid())
                        .set(user, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Log.d("firestore","document added");
                            Toast.makeText(ProfileActivity.this, "Profile Registered", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ProfileActivity.this,HomeActivity.class);
                            startActivity(intent);

                        }
                        else {
                            Log.d("firestore","couldn't add document");
                            Toast.makeText(ProfileActivity.this, "Please check your internet", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                            @Override
//                            public void onSuccess(DocumentReference documentReference) {
//                                Log.d("firestore", "DocumentSnapshot added with ID: " + documentReference.getId());
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Log.w("firestore", "Error adding document", e);
//                            }
//                        });
            }
        });

        edit_img_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

    }



    public void selectImage() {

        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==PICK_IMAGE && resultCode==RESULT_OK && data!=null)
        {
            CropImage.activity(data.getData()).setGuidelines(CropImageView.Guidelines.ON).start(this);
        }
//        if (requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            CropImage.activity(data.getData()).setGuidelines(CropImageView.Guidelines.ON).start(this);
//        }

        Log.d("mylogs","requestCode:"+requestCode+"\nRESULT_OK:"+RESULT_OK+"\nresult_code:"+resultCode);
//        if(requestCode == PICK_IMAGE)
//        {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK)
            {

                filePath = result.getUri();
                student_iv.setImageURI(filePath);
                uploadImage();
            }
//        }

    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        super.onActivityResult(requestCode,resultCode,data);
//
//        // checking request code and result code
//        // if request code is PICK_IMAGE_REQUEST and
//        // resultCode is RESULT_OK
//        // then set image in the image view
//        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
//
//            // Get the Uri of data
//            filePath = data.getData();
//            uploadImage();
//            try {
//                // Setting image on image view using Bitmap
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
//                student_iv.setImageBitmap(bitmap);
//            }
//
//            catch (IOException e) {
//                // Log the exception
//                e.printStackTrace();
//            }
//        }
//    }

    public void uploadImage()
    {
        if (filePath != null) {
            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.setMessage("Please Wait...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref = storageReference.child("profile_pictures").child(mUser.getUid()+".jpg");

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                {
                    // Image uploaded successfully
                    // Dismiss dialog
                    progressDialog.dismiss();
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Uri downloadUrl = uri;
                            image_link= downloadUrl.toString();
                            DocumentReference ref = db.collection("students").document(mUser.getUid());
                            ref.update(
                                    "image_link", downloadUrl
                            );

                        }
                    });
                    Toast.makeText(ProfileActivity.this,"Image Uploaded!!",Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("firestore","exception: "+e.getMessage().toString());
                    if(progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                }
            });
        }
    }

    public void toggleDarkMode(boolean isDarkModeOn) {
        if(isDarkModeOn) {
            profile_activity_bg.setBackgroundResource(R.drawable.background_2);
            Name.setTextColor(Color.parseColor("#FFFFFF"));
            RollNo.setTextColor(Color.parseColor("#FFFFFF"));
            Mobile.setTextColor(Color.parseColor("#FFFFFF"));
            RoomNo.setTextColor(Color.parseColor("#FFFFFF"));
            Email.setTextColor(Color.parseColor("#FFFFFF"));
            TechInterests.setTextColor(Color.parseColor("#FFFFFF"));
            email_et.setTextColor(Color.parseColor("#FFFFFF"));
            roll_no_et.setTextColor(Color.parseColor("#FFFFFF"));
            LinkedinURL.setTextColor(Color.parseColor("#FFFFFF"));
            name_et.setBackgroundTintList(getResources().getColorStateList(R.color.mycolorswhite));
            linkedin_url_et.setBackgroundTintList(getResources().getColorStateList(R.color.mycolorswhite));
//            roll_no_et.setBackgroundTintList(getResources().getColorStateList(R.color.mycolorswhite));
            mobile_et.setBackgroundTintList(getResources().getColorStateList(R.color.mycolorswhite));
            room_no_et.setBackgroundTintList(getResources().getColorStateList(R.color.mycolorswhite));
            delete_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttonlight));
            save_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttonlight));
//            email_et.setBackgroundTintList(getResources().getColorStateList(R.color.mycolorswhite));
//            interests_et.setBackgroundTintList(getResources().getColorStateList(R.color.mycolorswhite));

        }
        else {
            profile_activity_bg.setBackgroundResource(R.drawable.background_3);
            Name.setTextColor(Color.parseColor("#000000"));
            RollNo.setTextColor(Color.parseColor("#000000"));
            Mobile.setTextColor(Color.parseColor("#000000"));
            RoomNo.setTextColor(Color.parseColor("#000000"));
            Email.setTextColor(Color.parseColor("#000000"));
            TechInterests.setTextColor(Color.parseColor("#000000"));
            email_et.setTextColor(Color.parseColor("#000000"));
            roll_no_et.setTextColor(Color.parseColor("#000000"));
            LinkedinURL.setTextColor(Color.parseColor("#000000"));
            name_et.setBackgroundTintList(getResources().getColorStateList(R.color.mycolorblack));
            linkedin_url_et.setBackgroundTintList(getResources().getColorStateList(R.color.mycolorblack));
//            roll_no_et.setBackgroundTintList(getResources().getColorStateList(R.color.mycolorblack));
            mobile_et.setBackgroundTintList(getResources().getColorStateList(R.color.mycolorblack));
            room_no_et.setBackgroundTintList(getResources().getColorStateList(R.color.mycolorblack));
            delete_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttondark));
            save_btn.setBackgroundTintList(getResources().getColorStateList(R.color.buttondark));
//            email_et.setBackgroundTintList(getResources().getColorStateList(R.color.mycolorblack));
//            interests_et.setBackgroundTintList(getResources().getColorStateList(R.color.mycolorblack));
        }
    }

}
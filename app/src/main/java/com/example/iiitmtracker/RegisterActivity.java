package com.example.iiitmtracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class RegisterActivity extends AppCompatActivity {

    public boolean isDarkModeOn;
    public LottieAnimationView register_anim;
    public ConstraintLayout google_login_btn;
    public ConstraintLayout register_bg;
    public TextView sign_in_tv;
    public FirebaseAuth mAuth;
    public FirebaseUser mUser;
    public GoogleSignInOptions googleSignInOptions;
    public GoogleSignInClient googleSignInClient;
    public SignInClient oneTapClient;
    public BeginSignInRequest signInRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        google_login_btn = findViewById(R.id.google_login_btn);
        register_anim = findViewById(R.id.register_anim);
        sign_in_tv = findViewById(R.id.sign_in_tv);
        register_bg = findViewById(R.id.register_bg);
        register_anim.playAnimation();
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);
        toggleDarkMode(isDarkModeOn);

        if(isInternetAvailable(RegisterActivity.this)) {
            googleSignInOptions=new GoogleSignInOptions.Builder(
                    GoogleSignInOptions.DEFAULT_SIGN_IN
            ).requestIdToken("473973983584-rpt7li80o9dlf7kbab0fhbhmkujiifgc.apps.googleusercontent.com")
                    .requestEmail()
                    .build();
            googleSignInClient= GoogleSignIn.getClient(RegisterActivity.this
                    ,googleSignInOptions);

        }


        google_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isInternetAvailable(RegisterActivity.this)) {
                    // Initialize sign in intent
                    Intent intent=googleSignInClient.getSignInIntent();
                    // Start activity for result
//                    startActivityIfNeeded(intent,100);
                    startActivityForResult(intent,100);
                }
                else {
                    displayToast("Internet not available");
                }
            }
        });

        // Initialize firebase auth
        mAuth=FirebaseAuth.getInstance();
        // Initialize firebase user
        mUser=mAuth.getCurrentUser();
        // Check condition
        if(mUser!=null)
        {
            // When user already sign in
            // redirect to profile activity
            startActivity(new Intent(RegisterActivity.this,ProfileActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check condition
        if(requestCode==100)
        {
            // When request code is equal to 100
            // Initialize task
            Task<GoogleSignInAccount> signInAccountTask=GoogleSignIn
                    .getSignedInAccountFromIntent(data);
            Log.d("myauth","here1");

            // check condition
            if(signInAccountTask.isSuccessful())
            {
                // When google sign in successful
                // Initialize string
//                String s="Signin in...";
//                Log.d("myauth","here2");
//                // Display Toast
//                displayToast(s);
                // Initialize sign in account
                try {
                    // Initialize sign in account
                    GoogleSignInAccount googleSignInAccount=signInAccountTask
                            .getResult(ApiException.class);
                    // Check condition
                    if(googleSignInAccount!=null)
                    {
                        if(!googleSignInAccount.getEmail().toString().substring(googleSignInAccount.getEmail().toString().length()-11).equalsIgnoreCase("iiitm.ac.in")){
                            Toast.makeText(this, "Please Log in with your institute id.", Toast.LENGTH_SHORT).show();
                            googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        mAuth.signOut();
//                                        Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                }
                            });
                        }
                        else {
                            // When sign in account is not equal to null
                            // Initialize auth credential
                            Toast.makeText(this, "Sign in successful!", Toast.LENGTH_SHORT).show();
                            AuthCredential authCredential= GoogleAuthProvider
                                    .getCredential(googleSignInAccount.getIdToken()
                                            ,null);
                            // Check credential

                            mAuth.signInWithCredential(authCredential)
                                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            // Check condition
                                            if(task.isSuccessful())
                                            {
                                                // When task is successful
                                                // Redirect to profile activity
                                                startActivity(new Intent(RegisterActivity.this
                                                        ,ProfileActivity.class)
                                                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                                // Display Toast
//                                                displayToast("Firebase authentication successful");
                                            }
                                            else
                                            {
                                                // When task is unsuccessful
                                                // Display Toast
                                                displayToast("Authentication Failed :"+task.getException()
                                                        .getMessage());
                                            }
                                        }
                                    });

                        }

                    }
                }
                catch (ApiException e)
                {
                    e.printStackTrace();
                }
            }
            else {
                Log.d("myauth", signInAccountTask.toString());
            }
        }
    }

    private void displayToast(String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }

    public void toggleDarkMode(Boolean isDarkModeOn) {
        if(isDarkModeOn){
            register_bg.setBackgroundResource(R.drawable.background_2);
            sign_in_tv.setTextColor(Color.parseColor("#FFFFFF"));

        }
        else {
            register_bg.setBackgroundResource(R.drawable.background_3);
            sign_in_tv.setTextColor(Color.parseColor("#000000"));

        }
    }

    public boolean isInternetAvailable(Context context)
    {
        NetworkInfo info = (NetworkInfo) ((ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (info == null)
        {
//            Toast.makeText(RegisterActivity.this, "no internet connection",Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            if(info.isConnected())
            {
//                Toast.makeText(RegisterActivity.this, "connection established",Toast.LENGTH_SHORT).show();
                return true;
            }
            else
            {
//                Toast.makeText(RegisterActivity.this, "internet connection",Toast.LENGTH_SHORT).show();
                return true;
            }

        }
    }
}
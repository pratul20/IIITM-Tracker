package com.example.iiitmtracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.iiitmtracker.adapter.StudentRecyclerViewAdapter;
import com.example.iiitmtracker.model.Student;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.StringTokenizer;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchActivity extends AppCompatActivity {

    public Boolean isDarkModeOn;
    public RecyclerView student_rv;
    public ConstraintLayout student_bg;
    public StudentRecyclerViewAdapter studentRecyclerViewAdapter;
    public FirestoreRecyclerAdapter adapter;
    public ArrayList<Student> studentArrayList;
    public LinearLayoutManager linearLayoutManager;
    public ArrayAdapter<String> arrayAdapter;
    public ActionBar actionBar;
    public ImageView filter_iv;
    public Dialog myDialog;
    public ChipGroup filter_chip_group;
    public Button apply_btn;
    public FirebaseFirestore db;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);
        myDialog = new Dialog(this);
        actionBar = getSupportActionBar();
        student_rv = findViewById(R.id.student_rv);
        student_bg = findViewById(R.id.student_bg);
        filter_iv = findViewById(R.id.filter_iv);
        myDialog.setContentView(R.layout.filter_popup);
        filter_chip_group = myDialog.findViewById(R.id.filter_chip_group);
        apply_btn = myDialog.findViewById(R.id.apply_btn);
        toggleDarkMode(isDarkModeOn);
        studentArrayList = new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        studentRecyclerViewAdapter = new StudentRecyclerViewAdapter(SearchActivity.this,studentArrayList, isDarkModeOn);
        student_rv.setAdapter(studentRecyclerViewAdapter);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        student_rv.setLayoutManager(linearLayoutManager);
        db = FirebaseFirestore.getInstance();
        filter_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();
            }
        });

        apply_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> selected = new ArrayList<>();
                for(int i=0;i<filter_chip_group.getChildCount();i++) {
                    Chip chip = (Chip)filter_chip_group.getChildAt(i);
                    if(chip.isChecked()) {
                        selected.add(chip.getText().toString());
                    }
                }
                ArrayList<Student> filtered = new ArrayList<>();
                for(Student student: studentArrayList) {
                    String interests_string = student.getInterests();
                    ArrayList<String> interests = new ArrayList<>();
                    StringTokenizer st = new StringTokenizer(interests_string,",");
                    while (st.hasMoreTokens()) {
                        interests.add(st.nextToken());
                    }
                    int count=0;
                    for(String select: selected) {
                        for(String interest: interests) {
                            if(select.equalsIgnoreCase(interest)) {
                                count++;
                            }
                        }
                    }
                    if(count==selected.size()) {
                        filtered.add(student);
                    }

                    studentRecyclerViewAdapter.filterList(filtered);
                    myDialog.dismiss();
                }
            }
        });
        if(isInternetAvailable(this)) {
            EventChangeListener();
        }
        else {
            Toast.makeText(this, "Internet not available!", Toast.LENGTH_SHORT).show();
            if(progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }


        if(studentArrayList.size()==0) {
            if(progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }

    }

    public void EventChangeListener(){
        db.collection("students")
                .orderBy("name", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null) {
                    if(progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Log.d("firestore",error.getMessage().toString());
                    return;
                }

                for(DocumentChange dc: value.getDocumentChanges()) {
                    if(dc.getType() == DocumentChange.Type.ADDED) {
                        studentArrayList.add(dc.getDocument().toObject(Student.class));
                    }
                    studentRecyclerViewAdapter.notifyDataSetChanged();
                    if(progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // below line is to get our inflater
        MenuInflater inflater = getMenuInflater();

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.menu, menu);

        // below line is to get our menu item.
        MenuItem searchItem = menu.findItem(R.id.actionSearch);

        // getting search view of our item.
        SearchView searchView = (SearchView) searchItem.getActionView();

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText);
                return false;
            }
        });
        return true;
    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<Student> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (Student item : studentArrayList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            studentRecyclerViewAdapter.filterList(filteredlist);
        }
    }

    public void toggleDarkMode(Boolean isDarkModeOn) {
        if(isDarkModeOn){
            student_bg.setBackgroundResource(R.drawable.background_2);
            ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#217398"));
            actionBar.setBackgroundDrawable(colorDrawable);
            filter_iv.setColorFilter(Color.argb(255, 255, 255, 255));
        }
        else {
            student_bg.setBackgroundResource(R.drawable.background_3);
            ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0086C3"));
            actionBar.setBackgroundDrawable(colorDrawable);
            filter_iv.setColorFilter(Color.argb(0, 0, 0, 0));
        }
    }

    public boolean isInternetAvailable(Context context)
    {
        NetworkInfo info = (NetworkInfo) ((ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (info == null)
        {
            return false;
        }
        else
        {
            if(info.isConnected())
            {
                return true;
            }
            else
            {
                return true;
            }

        }
    }

}
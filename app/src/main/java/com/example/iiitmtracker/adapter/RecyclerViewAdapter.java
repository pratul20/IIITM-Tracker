package com.example.iiitmtracker.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iiitmtracker.R;
import com.example.iiitmtracker.helper.FacImage;
import com.example.iiitmtracker.model.Faculty;
import com.example.iiitmtracker.model.Place;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public Context context;
    public ArrayList<Faculty> facultyArrayList;
    public boolean isDarkModeOn;

    public RecyclerViewAdapter(Context context, ArrayList<Faculty> facultyArrayList,boolean isDarkModeOn) {
        this.context = context;
        this.facultyArrayList = facultyArrayList;
        this.isDarkModeOn = isDarkModeOn;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faculty_card, parent, false);
        return new ViewHolder(view);
    }

    public void filterList(ArrayList<Faculty> filterlist) {
        // below line is to add our filtered
        // list in our course array list.
        facultyArrayList = filterlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Faculty faculty = facultyArrayList.get(position);

//        holder.address_tv.setText(faculty.getAddress);
        holder.faculty_card_address_tv.setText(faculty.getAddress());
        holder.faculty_card_name_tv.setText(faculty.getName());
        FacImage facImage = new FacImage(context.getApplicationContext());
        Drawable d = facImage.getFacultyImage(faculty.getName());
        holder.faculty_card_image_iv.setImageDrawable(d);
        if(isDarkModeOn) {
            holder.faculty_card_address_tv.setTextColor(Color.parseColor("#FFFFFF"));
            holder.faculty_card_name_tv.setTextColor(Color.parseColor("#FFFFFF"));
            holder.direction_card_iv.setColorFilter(Color.argb(255, 37, 187, 255));
        }
        else{
            holder.faculty_card_address_tv.setTextColor(Color.parseColor("#000000"));
            holder.faculty_card_name_tv.setTextColor(Color.parseColor("#000000"));
            holder.direction_card_iv.setColorFilter(Color.argb(255, 9, 85, 143));
        }
        holder.popup_iv.setImageDrawable(d);
//        int resource_id = Integer.parseInt(faculty.getImage_link());
//        holder.faculty_card_image_iv.setImageDrawable(holder.itemView.getResources().getDrawable(resource_id));
    }

    @Override
    public int getItemCount() {
        return facultyArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        public TextView faculty_card_address_tv;
        public TextView faculty_card_name_tv;
        public ImageView faculty_card_image_iv;
        public ImageView call_card_iv;
        public ImageView direction_card_iv;
        public Dialog myDialog;
        public ImageView popup_iv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            myDialog = new Dialog(context);

            faculty_card_address_tv = itemView.findViewById(R.id.faculty_card_address_tv);
            faculty_card_name_tv = itemView.findViewById(R.id.place_name_tv);
            faculty_card_image_iv = itemView.findViewById(R.id.place_image_iv);
            call_card_iv = itemView.findViewById(R.id.directions_iv);
            direction_card_iv = itemView.findViewById(R.id.direction_card_iv);
            myDialog.setContentView(R.layout.imagepopup);
            popup_iv = myDialog.findViewById(R.id.popup_iv);

            direction_card_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Uri gmmIntentUri = Uri.parse("google.streetview:cbll=26.250747,78.173377");
//
//                    // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
//                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                    // Make the Intent explicit by setting the Google Maps package
//                    mapIntent.setPackage("com.google.android.apps.maps");
//
//                    // Attempt to start an activity that can handle the Intent
//                    context.startActivity(mapIntent);

                    int position = getAbsoluteAdapterPosition();
                    Faculty faculty = facultyArrayList.get(position);
                    if(faculty.getLatitude().isEmpty() || faculty.getLongitude().isEmpty()) {
                        Toast.makeText(context.getApplicationContext(), "Location not available!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        
                        String url = "http://maps.google.com/maps?daddr="+faculty.getLatitude()+","+faculty.getLongitude();
    
                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                Uri.parse(url));
                        context.startActivity(intent);
                    }
                }
            });

            faculty_card_image_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    myDialog.show();
                }
            });
        }

        @Override
        public void onClick(View view) {
            int position = this.getAbsoluteAdapterPosition();
            Faculty faculty = facultyArrayList.get(position);
            Intent intent = new Intent(context, displayFaculty.class);
            intent.putExtra("faculty",faculty);
            context.startActivity(intent);
        }

    }


}

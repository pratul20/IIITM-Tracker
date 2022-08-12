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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iiitmtracker.R;
import com.example.iiitmtracker.helper.PlaceImage;
import com.example.iiitmtracker.model.Faculty;
import com.example.iiitmtracker.model.Place;

import java.util.ArrayList;

public class PlaceRecyclerViewAdapter extends RecyclerView.Adapter<PlaceRecyclerViewAdapter.ViewHolder>{

    public Context context;
    public ArrayList<Place> placeArrayList;
    public boolean isDarkModeOn;

    public PlaceRecyclerViewAdapter(Context context, ArrayList<Place> placeArrayList, boolean isDarkModeOn) {
        this.context = context;
        this.placeArrayList = placeArrayList;
        this.isDarkModeOn = isDarkModeOn;
    }

    @NonNull
    @Override
    public PlaceRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_card, parent, false);
        return new PlaceRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceRecyclerViewAdapter.ViewHolder holder, int position) {
        Place place = placeArrayList.get(position);
        holder.place_name_tv.setText(place.getName());
        PlaceImage placeImage = new PlaceImage(context.getApplicationContext());
        Drawable d = placeImage.getPlaceImage(place.getName());
        holder.place_image_iv.setImageDrawable(d);
        if(isDarkModeOn) {
            holder.place_name_tv.setTextColor(Color.parseColor("#FFFFFF"));
            holder.direction_card_iv.setColorFilter(Color.argb(255, 37, 187, 255));
        }
        else{
            holder.place_name_tv.setTextColor(Color.parseColor("#000000"));
            holder.direction_card_iv.setColorFilter(Color.argb(255, 9, 85, 143));
        }
        holder.popup_iv.setImageDrawable(d);
    }

    public void filterList(ArrayList<Place> filterlist) {
        // below line is to add our filtered
        // list in our course array list.
        placeArrayList = filterlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return placeArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        public ImageView place_image_iv;
        public TextView place_name_tv;
        public ImageView direction_card_iv;
        public Dialog myDialog;
        public ImageView popup_iv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            myDialog = new Dialog(context);

            place_image_iv = itemView.findViewById(R.id.place_image_iv);
            place_name_tv = itemView.findViewById(R.id.place_name_tv);
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
                    Place place = placeArrayList.get(position);
                    String url = "http://maps.google.com/maps?daddr="+place.getLatitude()+","+place.getLongitude();

                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse(url));
                    context.startActivity(intent);
                }
            });

            place_image_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    myDialog.show();
                }
            });
        }

        @Override
        public void onClick(View view) {
//            int position = this.getAbsoluteAdapterPosition();
//            Faculty faculty = facultyArrayList.get(position);
//            Intent intent = new Intent(context, displayFaculty.class);
//            intent.putExtra("faculty",faculty);
//            context.startActivity(intent);
        }

    }
}

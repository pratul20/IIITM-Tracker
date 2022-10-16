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

import com.bumptech.glide.Glide;
import com.example.iiitmtracker.DisplayStudent;
import com.example.iiitmtracker.R;
import com.example.iiitmtracker.SearchActivity;
import com.example.iiitmtracker.model.Faculty;
import com.example.iiitmtracker.model.Student;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StudentRecyclerViewAdapter extends RecyclerView.Adapter<StudentRecyclerViewAdapter.ViewHolder>{

    public ArrayList<Student> studentArrayList;
    public Context context;
    public Boolean isDarkModeOn;

    public StudentRecyclerViewAdapter(Context context, ArrayList<Student> studentArrayList,boolean isDarkModeOn) {
        this.context = context;
        this.studentArrayList = studentArrayList;
        this.isDarkModeOn = isDarkModeOn;
    }


    public void filterList(ArrayList<Student> filterlist) {
        // below line is to add our filtered
        // list in our course array list.
        studentArrayList = filterlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    public void setNewList(ArrayList<Student> studentArrayList) {
        this.studentArrayList = studentArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_card, parent, false);
        return new StudentRecyclerViewAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull StudentRecyclerViewAdapter.ViewHolder holder, int position) {
        Student student = studentArrayList.get(position);
        holder.student_card_name_tv.setText(student.getName());
        holder.student_roll_no_tv.setText(student.getRoll_no());
        if(!student.getImage_link().isEmpty()) {
            Glide.with(context).load(student.getImage_link()).into(holder.student_card_image_iv);
//            Glide.with(context).load(student.getImage_link()).into(holder.popup_iv);
        }
        else {
            Drawable d = context.getResources().getDrawable(R.drawable.user_icon);
            holder.student_card_image_iv.setImageDrawable(d);
        }
        if(isDarkModeOn) {
            holder.student_roll_no_tv.setTextColor(Color.parseColor("#FFFFFF"));
            holder.student_card_name_tv.setTextColor(Color.parseColor("#FFFFFF"));
            holder.call_iv.setColorFilter(Color.argb(255, 37, 187, 255));
            holder.whatsapp_iv.setColorFilter(Color.argb(255, 37, 187, 255));
        }
        else {
            holder.student_roll_no_tv.setTextColor(Color.parseColor("#000000"));
            holder.student_card_name_tv.setTextColor(Color.parseColor("#000000"));
            holder.call_iv.setColorFilter(Color.argb(255, 9, 85, 143));
            holder.whatsapp_iv.setColorFilter(Color.argb(255, 9, 85, 143));
        }
    }

    @Override
    public int getItemCount() {
        return studentArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        public TextView student_roll_no_tv;
        public TextView student_card_name_tv;
        public ImageView student_card_image_iv;
        public ImageView call_iv;
        public ImageView whatsapp_iv;
        public Dialog myDialog;
        public ImageView popup_iv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            myDialog = new Dialog(context);

            student_roll_no_tv = itemView.findViewById(R.id.roll_no_tv);
            student_card_name_tv = itemView.findViewById(R.id.place_name_tv);
            student_card_image_iv = itemView.findViewById(R.id.place_image_iv);
            call_iv = itemView.findViewById(R.id.call_iv);
            whatsapp_iv = itemView.findViewById(R.id.whatsapp_iv);
            myDialog.setContentView(R.layout.imagepopup);
            popup_iv = myDialog.findViewById(R.id.popup_iv);

            call_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAbsoluteAdapterPosition();
                    Student student = studentArrayList.get(position);
                    if(student.getMobile_no().isEmpty()) {
                        Toast.makeText(context.getApplicationContext(), "Number not available!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String number = student.getMobile_no().toString();
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));// Initiates the Intent
                        context.startActivity(intent);
                    }
                }
            });

            whatsapp_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAbsoluteAdapterPosition();
                    Student student = studentArrayList.get(position);
                    if(student.getMobile_no().isEmpty()) {
                        Toast.makeText(context.getApplicationContext(), "Number not available!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String number = student.getMobile_no();
                        if(number.charAt(0)!='+') {
                            number = "+91"+number;
                        }
                        String url = "https://wa.me/"+number;
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        context.startActivity(browserIntent);
                    }

                }
            });

            student_card_image_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position =getAbsoluteAdapterPosition();
                    Student student = studentArrayList.get(position);
                    if(student.getImage_link().isEmpty()){
                        Toast.makeText(context.getApplicationContext(), "Image not available!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Picasso.get().load(student.getImage_link()).into(popup_iv);
                        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                        myDialog.show();
                        String d=student.getImage_link();
                    }
                }
            });
        }

        @Override
        public void onClick(View view) {
            int position = this.getAbsoluteAdapterPosition();
            Student student = studentArrayList.get(position);
            Intent intent = new Intent(context, DisplayStudent.class);
            intent.putExtra("student",student);
            context.startActivity(intent);
        }

    }

}

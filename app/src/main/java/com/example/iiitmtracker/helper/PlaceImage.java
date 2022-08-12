package com.example.iiitmtracker.helper;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.iiitmtracker.R;

public class PlaceImage {
    public static Context context;
    public PlaceImage(Context context) {
        this.context = context;
    }

    public static Drawable getPlaceImage(String s) {
        Drawable d;

        if(s.equalsIgnoreCase("BH-3 (Shivalik)")) {
            d = context.getResources().getDrawable(R.drawable.bh_3);
            return d;
        }
        if(s.equalsIgnoreCase("BH-2 (Nilgiri)")) {
            d = context.getResources().getDrawable(R.drawable.bh_2);
            return d;
        }
        if(s.equalsIgnoreCase("BH-1 (Aravali)")) {
            d = context.getResources().getDrawable(R.drawable.bh_1);
            return d;
        }
        if(s.equalsIgnoreCase("GH (Gangotri)")) {
            d = context.getResources().getDrawable(R.drawable.gh);
            return d;
        }
        if(s.equalsIgnoreCase("Power House")) {
            d = context.getResources().getDrawable(R.drawable.power_house);
            return d;
        }
        if(s.equalsIgnoreCase("Generator Room")) {
            d = context.getResources().getDrawable(R.drawable.generator_room);
            return d;
        }
        if(s.equalsIgnoreCase("Learning Resource Center (LRC)")) {
            d = context.getResources().getDrawable(R.drawable.lrc);
            return d;
        }
        if(s.equalsIgnoreCase("Admin Block")) {
            d = context.getResources().getDrawable(R.drawable.admin_block);
            return d;
        }
        if(s.equalsIgnoreCase("New Auditorium")) {
            d = context.getResources().getDrawable(R.drawable.new_auditorium);
            return d;
        }
        if(s.equalsIgnoreCase("Basketball Court")) {
            d = context.getResources().getDrawable(R.drawable.basketball_ground);
            return d;
        }
        if(s.equalsIgnoreCase("International Visitor's Hostel (IVH)")) {
            d = context.getResources().getDrawable(R.drawable.ivh);
            return d;
        }
        if(s.equalsIgnoreCase("Control Room")) {
            d = context.getResources().getDrawable(R.drawable.control_room);
            return d;
        }
        if(s.equalsIgnoreCase("Bio-diversity Park")) {
            d = context.getResources().getDrawable(R.drawable.bio_diversity);
            return d;
        }
        if(s.equalsIgnoreCase("Director's Bungalow")) {
            d = context.getResources().getDrawable(R.drawable.directors_residence);
            return d;
        }
        if(s.equalsIgnoreCase("Sports Complex")) {
            d = context.getResources().getDrawable(R.drawable.sports_complex);
            return d;
        }
        if(s.equalsIgnoreCase("Badminton Court")) {
            d = context.getResources().getDrawable(R.drawable.badminton_hall);
            return d;
        }
        if(s.equalsIgnoreCase("Music Room")) {
            d = context.getResources().getDrawable(R.drawable.music_room);
            return d;
        }
        if(s.equalsIgnoreCase("Yoga Hall")) {
            d = context.getResources().getDrawable(R.drawable.iiitm_logo);
            return d;
        }
        if(s.equalsIgnoreCase("Cricket Ground")) {
            d = context.getResources().getDrawable(R.drawable.cricket_ground);
            return d;
        }
        if(s.equalsIgnoreCase("Swimming Pool")) {
            d = context.getResources().getDrawable(R.drawable.swimming_pool);
            return d;
        }
        if(s.equalsIgnoreCase("Snooker Room")) {
            d = context.getResources().getDrawable(R.drawable.snooker_room);
            return d;
        }
        if(s.equalsIgnoreCase("Squash Room")) {
            d = context.getResources().getDrawable(R.drawable.squash_room);
            return d;
        }
        if(s.equalsIgnoreCase("Table Tennis Hall")) {
            d = context.getResources().getDrawable(R.drawable.table_tennis);
            return d;
        }
        if(s.equalsIgnoreCase("Open Air Theatre")) {
            d = context.getResources().getDrawable(R.drawable.oat);
            return d;
        }
        if(s.equalsIgnoreCase("Tennis Ground")) {
            d = context.getResources().getDrawable(R.drawable.tennis_court);
            return d;
        }
        if(s.equalsIgnoreCase("Volleyball Ground")) {
            d = context.getResources().getDrawable(R.drawable.volleyball_ground);
            return d;
        }
        if(s.equalsIgnoreCase("Health Resource Center")) {
            d = context.getResources().getDrawable(R.drawable.health_center);
            return d;
        }
        if(s.equalsIgnoreCase("Training and Placement Cell")) {
            d = context.getResources().getDrawable(R.drawable.iiitm_logo);
            return d;
        }
        if(s.equalsIgnoreCase("Butterfly Conservatory")) {
            d = context.getResources().getDrawable(R.drawable.butterfly_conservatory);
            return d;
        }
        if(s.equalsIgnoreCase("Gate No. 2")) {
            d = context.getResources().getDrawable(R.drawable.gate_no_2);
            return d;
        }
        if(s.equalsIgnoreCase("Seminar Hall (Auditorium)")) {
            d = context.getResources().getDrawable(R.drawable.seminar_hall);
            return d;
        }
        if(s.equalsIgnoreCase("Director's Office")) {
            d = context.getResources().getDrawable(R.drawable.directors_office);
            return d;
        }
        if(s.equalsIgnoreCase("Saraswati Chauraha")) {
            d = context.getResources().getDrawable(R.drawable.saraswati_chauraha);
            return d;
        }
        if(s.equalsIgnoreCase("Juice Shop")) {
            d = context.getResources().getDrawable(R.drawable.juice_shop);
            return d;
        }
        if(s.equalsIgnoreCase("LT-1 (B-Block)")) {
            d = context.getResources().getDrawable(R.drawable.lt_1);
            return d;
        }
        if(s.equalsIgnoreCase("LT-2")) {
            d = context.getResources().getDrawable(R.drawable.lt_2);
            return d;
        }
        if(s.equalsIgnoreCase("Block 1(I) (E-Block)")) {
            d = context.getResources().getDrawable(R.drawable.block_1);
            return d;
        }
        if(s.equalsIgnoreCase("Block 5(V) (F-Block)")) {
            d = context.getResources().getDrawable(R.drawable.block_5);
            return d;
        }
        if(s.equalsIgnoreCase("Block 4(IV) (D-Block)")) {
            d = context.getResources().getDrawable(R.drawable.block_4);
            return d;
        }
        if(s.equalsIgnoreCase("Block 2(II) (A-Block)")) {
            d = context.getResources().getDrawable(R.drawable.block_2);
            return d;
        }
        if(s.equalsIgnoreCase("Block 3(III) (C-Block)")) {
            d = context.getResources().getDrawable(R.drawable.block_3);
            return d;
        }

        if(s.equalsIgnoreCase("Cafeteria")) {
            d = context.getResources().getDrawable(R.drawable.cafeteria);
            return d;
        }
        if(s.equalsIgnoreCase("Block 6(VI) (Computer Room)")) {
            d = context.getResources().getDrawable(R.drawable.block_6);
            return d;
        }
        else {
            return context.getResources().getDrawable(R.drawable.iiitm_logo);
        }
    }
}

package com.example.iiitmtracker.helper;
import com.example.iiitmtracker.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class FacImage {
    public static Context context;
    public FacImage(Context context){
        this.context=context;
    }

    public static Drawable getFacultyImage(String s) {
        Drawable d;
        if(s.equalsIgnoreCase("Prof. Sri Niwas Singh")) {
            d = context.getResources().getDrawable(R.drawable.sri_nivas_img);
            return d;
        }

        else if(s.equalsIgnoreCase("Prof. Gopal Krishan Sharma")) {
            d = context.getResources().getDrawable(R.drawable.gopal_krishan_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Prof. Rajendra Sahu")) {
            d = context.getResources().getDrawable(R.drawable.rajendra_sahu_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Prof. Shashikala Tapaswi")) {
            d = context.getResources().getDrawable(R.drawable.shashikala_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Prof. Aditya Trivedi")) {
            d = context.getResources().getDrawable(R.drawable.aditya_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Prof. Mahua Bhattacharya")) {
            d = context.getResources().getDrawable(R.drawable.mahua_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Prof. Karm Veer Arya")) {
            d = context.getResources().getDrawable(R.drawable.karm_veer_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Prof. Pramod Kumar Singh")) {
            d = context.getResources().getDrawable(R.drawable.pramod_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Prof. Joydip Dhar")) {
            d = context.getResources().getDrawable(R.drawable.jdhar);
            return d;
        }
        else if(s.equalsIgnoreCase("Prof. Manoj Patwardhan")) {
            d = context.getResources().getDrawable(R.drawable.manoj_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Prof. Anurag Srivastava")) {
            d = context.getResources().getDrawable(R.drawable.anurag_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Prof. Pankaj Srivastava")) {
            d = context.getResources().getDrawable(R.drawable.pankaj_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Prof. Naval Bajpai")) {
            d = context.getResources().getDrawable(R.drawable.naval_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Prof. Manisha Pattanaik")) {
            d = context.getResources().getDrawable(R.drawable.manisha_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Dr. Gaurav Agrawal")) {
            d = context.getResources().getDrawable(R.drawable.gaurav_agrawal_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Dr. Ritu Tiwari")) {
            d = context.getResources().getDrawable(R.drawable.ritu_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Prof. Gyan Prakash")) {
            d = context.getResources().getDrawable(R.drawable.gyan_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Dr. Manoj Kumar Dash")) {
            d = context.getResources().getDrawable(R.drawable.manoj_kumar_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Dr. KK Pattanaik")) {
            d = context.getResources().getDrawable(R.drawable.kk_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Dr. Ajay Kumar")) {
            d = context.getResources().getDrawable(R.drawable.ajay_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Dr. Vinay Singh")) {
            d = context.getResources().getDrawable(R.drawable.vinay_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Dr. Vishal Vyas")) {
            d = context.getResources().getDrawable(R.drawable.vishal_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Dr. W Wilfred Godfrey")) {
            d = context.getResources().getDrawable(R.drawable.wilfred_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Dr. Anuraj Singh")) {
            d = context.getResources().getDrawable(R.drawable.anuraj_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Dr. Saumya Bhadauria")) {
            d = context.getResources().getDrawable(R.drawable.saumya_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Dr. Gaurav Kaushal")) {
            d = context.getResources().getDrawable(R.drawable.gaurav_kaushal_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Dr. Santosh Singh Rathore")) {
            d = context.getResources().getDrawable(R.drawable.santosh_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Dr. Pinku Ranjan")) {
            d = context.getResources().getDrawable(R.drawable.pinku_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Dr. Somesh Kumar")) {
            d = context.getResources().getDrawable(R.drawable.somesh_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Dr.Sunil Kumar")) {
            d = context.getResources().getDrawable(R.drawable.sunil_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Dr. Debanjan Sadhya")) {
            d = context.getResources().getDrawable(R.drawable.debanjan_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Dr. Binod Prasad")) {
            d = context.getResources().getDrawable(R.drawable.binod_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Dr. Arun Kumar")) {
            d = context.getResources().getDrawable(R.drawable.arun_kumar_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Dr. Rajesh Rajagopal")) {
            d = context.getResources().getDrawable(R.drawable.rajesh_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Dr. Vinal Patel")) {
            d = context.getResources().getDrawable(R.drawable.vinal_img);
            return d;
        }
        else if(s.equalsIgnoreCase("Dr. Jeevaraj Singh")) {
            d = context.getResources().getDrawable(R.drawable.jeevraj_img);
            return d;
        }
        d = context.getResources().getDrawable(R.drawable.professor_icon);
        return d;
    }
}

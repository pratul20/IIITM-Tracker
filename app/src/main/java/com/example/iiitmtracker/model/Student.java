package com.example.iiitmtracker.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable{
    public String id;
    public String name;
    public String email;
    public String batch;
    public String roll_no;
    public String mobile_no;
    public String room_no;
    public String interests;
    public String image_link;
    public String rating;
    public String views;
    public String no_of_people_rated;
    public String linkedin_url;

    public Student() {

    }

    public Student(String name, String email, String batch, String roll_no) {
        this.name = name;
        this.email = email;
        this.batch = batch;
        this.roll_no = roll_no;
    }

    public Student(String name, String email, String batch, String roll_no, String image_link) {
        this.name = name;
        this.email = email;
        this.batch = batch;
        this.roll_no = roll_no;
        this.image_link = image_link;
    }

    public Student(String name, String email, String batch, String roll_no, String mobile_no, String interests, String image_link) {
        this.name = name;
        this.email = email;
        this.batch = batch;
        this.roll_no = roll_no;
        this.mobile_no = mobile_no;
        this.interests = interests;
        this.image_link = image_link;
    }

    public Student(String name, String email, String batch, String roll_no, String mobile_no, String room_no, String interests, String image_link) {
        this.name = name;
        this.email = email;
        this.batch = batch;
        this.roll_no = roll_no;
        this.mobile_no = mobile_no;
        this.room_no = room_no;
        this.interests = interests;
        this.image_link = image_link;
    }

    public String getLinkedin_url() {
        return linkedin_url;
    }

    public void setLinkedin_url(String linkedin_url) {
        this.linkedin_url = linkedin_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo_of_people_rated() {
        return no_of_people_rated;
    }

    public void setNo_of_people_rated(String no_of_people_rated) {
        this.no_of_people_rated = no_of_people_rated;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(String roll_no) {
        this.roll_no = roll_no;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getRoom_no() {
        return room_no;
    }

    public void setRoom_no(String room_no) {
        this.room_no = room_no;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public Student(Parcel in){
        String[] data = new String[12];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method
        this.name = data[0];
        this.email = data[1];
        this.roll_no = data[2];
        this.mobile_no = data[3];
        this.room_no = data[4];
        this.interests = data[5];
        this.image_link = data[6];
        this.rating = data[7];
        this.views = data[8];
        this.id = data[9];
        this.no_of_people_rated = data[10];
        this.linkedin_url = data[11];
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {
            this.name,
            this.email,
            this.roll_no,
            this.mobile_no,
            this.room_no,
            this.interests,
            this.image_link,
            this.rating,
            this.views,
            this.id,
            this.no_of_people_rated,
            this.linkedin_url
        });
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}

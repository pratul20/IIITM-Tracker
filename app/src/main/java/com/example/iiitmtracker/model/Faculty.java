package com.example.iiitmtracker.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

public class Faculty implements Parcelable {
    public String image_link;
    public String address;
    public String name;
    public String mobile;
    public String office_phone;
    public String residence_phone;
    public String honour;
    public String designation;
    public String email;
    public String website;
    public String Latitude;
    public String Longitude;

    public Faculty(String address, String name, String mobile, String office_phone, String residence_phone, String honour, String designation, String email, String website) {
        this.address = address;
        this.name = name;
        this.mobile = mobile;
        this.office_phone = office_phone;
        this.residence_phone = residence_phone;
        this.honour = honour;
        this.designation = designation;
        this.email = email;
        this.website = website;
    }

    public Faculty(String address, String name, String mobile, String office_phone, String residence_phone, String honour, String designation, String email, String website, String latitude, String longitude) {
        this.address = address;
        this.name = name;
        this.mobile = mobile;
        this.office_phone = office_phone;
        this.residence_phone = residence_phone;
        this.honour = honour;
        this.designation = designation;
        this.email = email;
        this.website = website;
        Latitude = latitude;
        Longitude = longitude;
    }

    public Faculty(String image_link, String address, String name, String mobile, String office_phone, String residence_phone, String honour, String designation, String email, String website) {
        this.image_link = image_link;
        this.address = address;
        this.name = name;
        this.mobile = mobile;
        this.office_phone = office_phone;
        this.residence_phone = residence_phone;
        this.honour = honour;
        this.designation = designation;
        this.email = email;
        this.website = website;
    }

    public Faculty(String image_link, String address, String name, String mobile, String office_phone, String residence_phone, String honour, String designation, String email, String website, String latitude, String longitude) {
        this.image_link = image_link;
        this.address = address;
        this.name = name;
        this.mobile = mobile;
        this.office_phone = office_phone;
        this.residence_phone = residence_phone;
        this.honour = honour;
        this.designation = designation;
        this.email = email;
        this.website = website;
        Latitude = latitude;
        Longitude = longitude;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOffice_phone() {
        return office_phone;
    }

    public void setOffice_phone(String office_phone) {
        this.office_phone = office_phone;
    }

    public String getResidence_phone() {
        return residence_phone;
    }

    public void setResidence_phone(String residence_phone) {
        this.residence_phone = residence_phone;
    }

    public String getHonour() {
        return honour;
    }

    public void setHonour(String honour) {
        this.honour = honour;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public Faculty(Parcel in){
        String[] data = new String[12];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method
        this.image_link = data[0];
        this.address = data[1];
        this.name = data[2];
        this.mobile = data[3];
        this.office_phone = data[4];
        this.residence_phone = data[5];
        this.honour = data[6];
        this.designation = data[7];
        this.email = data[8];
        this.website = data[9];
        this.Latitude = data[10];
        this.Longitude = data[11];
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.image_link,
                this.address,
                this.name,
                this.mobile,
                this.office_phone,
                this.residence_phone,
                this.honour,
                this.designation,
                this.email,
                this.website,
                this.Latitude,
                this.Longitude
        });
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Faculty createFromParcel(Parcel in) {
            return new Faculty(in);
        }

        public Faculty[] newArray(int size) {
            return new Faculty[size];
        }
    };
}


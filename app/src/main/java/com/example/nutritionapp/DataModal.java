package com.example.nutritionapp;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class DataModal implements Parcelable {

    private String email;
    private String name;
    private Uri ImageUrl;

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public static Creator<DataModal> getCREATOR() {
        return CREATOR;
    }

    private String name2;

    protected DataModal(Parcel in) {
        email = in.readString();
        name = in.readString();
        ImageUrl = in.readParcelable(Uri.class.getClassLoader());
        name2=in.readString();
    }

    public static final Creator<DataModal> CREATOR = new Creator<DataModal>() {
        @Override
        public DataModal createFromParcel(Parcel in) {
            return new DataModal(in);
        }

        @Override
        public DataModal[] newArray(int size) {
            return new DataModal[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(Uri imageUrl) {
        ImageUrl = imageUrl;
    }



    public DataModal() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(email);
        parcel.writeString(name);
        parcel.writeParcelable(ImageUrl, i);
        parcel.writeString(name2);
    }
}

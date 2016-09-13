package com.nkr.testapp101.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by neel on 07/09/2016.
 */
public class User implements Parcelable {

  private   String fName;
   private String lName;
    private String email;
  private  String password;

  private   String phone;
   private String userType;


    public User(String fName, String lName, String email, String password, String phone, String userType) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userType = userType;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public User() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(fName);
        parcel.writeString(lName);
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeString(phone);
        parcel.writeString(userType);

    }

    private User(Parcel in){
        fName=in.readString();
        lName=in.readString();
        email=in.readString();
        password=in.readString();
        phone=in.readString();
        userType=in.readString();

    }

    public static final Creator<User>CREATOR=new Creator<User>() {
        @Override
        public User createFromParcel(Parcel parcel) {
            return new User(parcel);
        }

        @Override
        public User[] newArray(int i) {
            return new User[i];
        }
    };
}

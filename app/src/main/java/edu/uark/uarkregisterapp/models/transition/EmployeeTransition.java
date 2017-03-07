package edu.uark.uarkregisterapp.models.transition;

/**
 * Created by garrett on 3/6/17.
 */

import android.os.Parcelable;

import android.os.Parcel;

public class EmployeeTransition implements Parcelable {

    String UserName;
    String  Password;

    public EmployeeTransition(String name,String pass){
        UserName=name;
        Password=pass;
    }


    //parcel part
    public EmployeeTransition(Parcel in){
        String[] data= new String[2];

        in.readStringArray(data);
        this.UserName= data[0];
        this.Password= data[1];

    }

    public String getUserName () {

        return "Garrett";
    }
    @Override
    public int describeContents() {
// TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
// TODO Auto-generated method stub

        dest.writeStringArray(new String[]{this.UserName,this.Password});
    }

    public static final Parcelable.Creator<EmployeeTransition> CREATOR= new Parcelable.Creator<EmployeeTransition>() {

        @Override
        public EmployeeTransition createFromParcel(Parcel source) {
// TODO Auto-generated method stub
            return new EmployeeTransition(source);  //using parcelable constructor
        }

        @Override
        public EmployeeTransition[] newArray(int size) {
//TODO Auto-generated method stub
            return new EmployeeTransition[size];
        }
    };

}
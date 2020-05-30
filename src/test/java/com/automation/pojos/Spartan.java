package com.automation.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Spartan {

    private int _id;
    private String _name;
    private String _gender;
    @SerializedName("phone")
    private long _phoneNumber;

    public Spartan(String name,String gender,long phoneNumber){
        this._name = name;
        this._gender=gender;
        set_phoneNumber(phoneNumber);
    }
    public Spartan(int id ,String name,String gender,long phoneNumber){
        this._id = id;
        this._name = name;
        this._gender=gender;
        set_phoneNumber(phoneNumber);
    }

    public void set_phoneNumber(long _phoneNumber) {


        if(String.valueOf(_phoneNumber).length()<10){
            throw new RuntimeException("Phone number is too short!");
        }
            this._phoneNumber = _phoneNumber;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_gender(String _gender) {
        this._gender = _gender;
    }


    public long get_phoneNumber() {
        return _phoneNumber;
    }

    public String get_name() {
        return _name;
    }

    public int get_id() {
        return _id;
    }

    public String get_gender() {
        return _gender;
    }

    public Spartan(){

    }

    @Override
    public String toString() {
        return "Spartan{" +
                "_id=" + _id +
                ", _name='" + _name + '\'' +
                ", _gender='" + _gender + '\'' +
                ", _phoneNumber=" + _phoneNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Spartan)) return false;
        Spartan spartan = (Spartan) o;
        return get_id() == spartan.get_id();
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id,_name,_gender,_phoneNumber);
    }
}

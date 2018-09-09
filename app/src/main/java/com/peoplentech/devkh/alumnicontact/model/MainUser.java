package com.peoplentech.devkh.alumnicontact.model;

/**
 * Created by User on 5/5/2018.
 */

public class MainUser {
    private int userId;
    private String Name;
    private String Gender;
    private String Blood;
    private String Address;
    private String Phone;
    private String Email;
    private String Dept;
    private String Batch;
    private String Job;

    public MainUser (int id, String name, String gender, String blood, String address, String phone,String email, String dept, String batch, String job) {
        this.userId = id;
        this.Name = name;
        this.Gender = gender;
        this.Blood = blood;
        this.Address = address;
        this.Phone = phone;
        this.Email = email;
        this.Dept = dept;
        this.Batch = batch;
        this.Job = job;

    }

    public int getuserId() {
        return userId;
    }

    public void setuserId(int id) {
        this.userId = id;
    }




    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }




    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        this.Gender = gender;
    }





    public String getBlood() {
        return Blood;
    }

    public void setBlood(String blood) {
        this.Blood = blood;
    }





    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }





    public String getPhone() { return Phone;}

    public void setPhone(String phone) { this.Phone = phone; }





    public String getEmail() { return Email;}

    public void setEmail(String email) { this.Email = email; }





    public String getDept() { return Dept;}

    public void setDept(String dept) { this.Dept = dept; }





    public String getBatch() { return Batch;}

    public void setBatch(String batch) { this.Batch = batch; }





    public String getJob() { return Job;}

    public void setJob(String job) { this.Job = job; }
}


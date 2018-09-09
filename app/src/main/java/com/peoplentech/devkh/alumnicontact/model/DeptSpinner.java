package com.peoplentech.devkh.alumnicontact.model;

/**
 * Created by User on 5/6/2018.
 */

public class DeptSpinner {
    private int userId;
    private String Name;

    public DeptSpinner(int id, String name) {
        this.userId = id;
        this.Name = name;
    }

    public int getSpinnerId() {
        return userId;
    }

    public void setSpinnerId(int id) {
        this.userId = id;
    }




    public String getSpinnerName() {
        return Name;
    }

    public void setSpinnerName(String name) {
        this.Name = name;
    }
}

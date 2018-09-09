package com.peoplentech.devkh.alumnicontact.model;

/**
 * Created by User on 5/10/2018.
 */

public class BatchNo {

    private int batchId;
    private String BatchNumber;
    public BatchNo (int id, String batchnumber) {
        this.batchId = id;
        this.BatchNumber = batchnumber;


    }

    public int getbatchId() {
        return batchId;
    }

    public void setbatchId(int id) {
        this.batchId = id;
    }




    public String getBatchNumber() {
        return BatchNumber;
    }

    public void setBatchNumber(String batchnumber) {
        this.BatchNumber = batchnumber;
    }

    }


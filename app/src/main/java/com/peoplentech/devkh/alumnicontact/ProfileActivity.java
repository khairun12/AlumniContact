package com.peoplentech.devkh.alumnicontact;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.peoplentech.devkh.alumnicontact.SQLiteHelper.Table_Column_1_Name;
import static com.peoplentech.devkh.alumnicontact.SQLiteHelper.Table_Column_2_Blood;
import static com.peoplentech.devkh.alumnicontact.SQLiteHelper.Table_Column_3_Address;
import static com.peoplentech.devkh.alumnicontact.SQLiteHelper.Table_Column_4_Phone;
import static com.peoplentech.devkh.alumnicontact.SQLiteHelper.Table_Column_5_Email;
import static com.peoplentech.devkh.alumnicontact.SQLiteHelper.Table_Column_6_Dept;
import static com.peoplentech.devkh.alumnicontact.SQLiteHelper.Table_Column_7_Batch;
import static com.peoplentech.devkh.alumnicontact.SQLiteHelper.Table_Column_8_Job;
import static com.peoplentech.devkh.alumnicontact.SQLiteHelper.Table_Column_9_Gender;
import static com.peoplentech.devkh.alumnicontact.SQLiteHelper.Table_Column_ID;

public class ProfileActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    String WEB_URL;

    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        linearLayout = (LinearLayout) findViewById(R.id.department);

        //load data for SQLite
        WEB_URL = "http://10.16.20.41/alumni/blood/allDonor.php?id=";
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            SQLiteDataBaseBuild();

            SQLiteTableBuild();

            DeletePreviousData();

            loadAllData(0);
        }

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });
        linearLayout = (LinearLayout) findViewById(R.id.blood);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent blood = new Intent(ProfileActivity.this,BloodActivity.class);
                startActivity(blood);
            }
        });

        linearLayout = (LinearLayout) findViewById(R.id.batch_no);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent batch = new Intent(ProfileActivity.this,BatchActivity.class);
                startActivity(batch);
            }
        });

    }


    private void loadAllData(int id) {


        @SuppressLint("StaticFieldLeak") AsyncTask<Integer, Void, Void> asyncTask = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... designationIds) {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(WEB_URL + designationIds[0])
                        .build();
                try {
                    Response response = client.newCall(request).execute();

                    JSONArray array = new JSONArray(response.body().string());

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);

                        String id = object.getString("id");
                        String name = object.getString("name");
                        String gender = object.getString("gender");
                        String address = object.getString("address");
                        String blood = object.getString("blood");
                        String phone = object.getString("phone");
                        String email = object.getString("email");
                        String dept = object.getString("dept");
                        String batch = object.getString("batch");
                        String job = object.getString("job");
                        String SQLiteDataBaseQueryHolder = "INSERT INTO "+SQLiteHelper.TABLE_NAME+" (id, name, blood, address, phone, email, dept, batch, job, gender) VALUES('"+id+"', '"+name+"', '"+blood+"', '"+address+"', '"+phone+"', '"+email+"', '"+dept+"', '"+batch+"', '"+job+"', '"+gender+"');";

                        sqLiteDatabase.execSQL(SQLiteDataBaseQueryHolder);
                    }



                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

            }
        };

        asyncTask.execute(id);
    }

    public void SQLiteDataBaseBuild(){

        sqLiteDatabase = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

    public void SQLiteTableBuild(){

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+SQLiteHelper.TABLE_NAME+"("+Table_Column_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+Table_Column_1_Name+" VARCHAR, "+Table_Column_2_Blood+" VARCHAR, "+Table_Column_3_Address+" VARCHAR, "+Table_Column_4_Phone+" VARCHAR, "+Table_Column_5_Email+" VARCHAR, "+Table_Column_6_Dept+" VARCHAR, "+Table_Column_7_Batch+" VARCHAR,"+Table_Column_8_Job+" VARCHAR,"+Table_Column_9_Gender+" VARCHAR)");

    }

    public void DeletePreviousData(){

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            sqLiteDatabase.execSQL("DELETE FROM "+SQLiteHelper.TABLE_NAME+"");
        }


    }


}

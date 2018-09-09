package com.peoplentech.devkh.alumnicontact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.peoplentech.devkh.alumnicontact.model.DeptSpinner;

public class MainActivity extends AppCompatActivity {

    LinearLayout cseLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cseLayout = (LinearLayout) findViewById(R.id.cse);
        cseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cse = new Intent(MainActivity.this, DeptActivity.class);
                startActivity(cse);
            }
        });
    }
}

package com.peoplentech.devkh.alumnicontact;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.peoplentech.devkh.alumnicontact.adapter.BatchAdapter;
import com.peoplentech.devkh.alumnicontact.model.BatchNo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BatchActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private List<BatchNo> batch;
    private RecyclerView recyclerView;
    private LinearLayoutManager gridLayout;
    private BatchAdapter adapter;
    protected View mView;
    LinearLayout BatchLayout;
    SwipeRefreshLayout refreshbatch;
    String WEB_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch);

        BatchLayout = (LinearLayout) findViewById(R.id.batchLayout);
        refreshbatch = (SwipeRefreshLayout) findViewById(R.id.batchRefresh);
        refreshbatch.setOnRefreshListener(this);
        //initCustomSpinner();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewBatch);
        batch = new ArrayList<>();
        gridLayout = new LinearLayoutManager(BatchActivity.this);
        recyclerView.setLayoutManager(gridLayout);

        adapter = new BatchAdapter(BatchActivity.this, batch);
        recyclerView.setAdapter(adapter);

        if (adapter.getItemCount() > 0) {

            batch.clear();
            adapter.notifyItemRangeRemoved(0,adapter.getItemCount()-1);
        }

        WEB_URL = "http://10.16.20.41/alumni/batch/batch.php?id=";
        loadAllData(0);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (gridLayout.findLastCompletelyVisibleItemPosition() == batch.size() - 1) {
                    loadAllData(batch.get(batch.size() - 1).getbatchId());
                }

            }
        });

        EditText searchBatch = (EditText) findViewById(R.id.batch_search);
        searchBatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BatchActivity.this,SearchBatchActivity.class);
                startActivity(intent);
            }
        });

    }

    //Recyclerview method
    private void loadAllData(int id) {
        if (recyclerView.getAdapter() == null) {
            recyclerView.setAdapter(adapter);
        }

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

                        BatchNo batch_number = new BatchNo(object.getInt("id"), object.getString("batch_number"));

                        BatchActivity.this.batch.add(batch_number);
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

                refreshbatch.setRefreshing(false);
                adapter.notifyDataSetChanged();
            }
        };

        asyncTask.execute(id);
    }
    @Override
    public void onRefresh() {
        ConnectivityManager conntivityManager = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(conntivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState()== NetworkInfo.State.CONNECTED ||
                conntivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState()== NetworkInfo.State.CONNECTED){

            //clear data
            batch.clear();
            recyclerView.setAdapter(null);
            //adapter.notifyItemRangeRemoved(0,adapter.getItemCount()-1);
            //load
            loadAllData(0);
        } else {

            refreshbatch.setRefreshing(false);
            Snackbar snackbar = Snackbar.make(BatchLayout, "No Internet Connection", Snackbar.LENGTH_LONG);
            snackbar.show();
        }

    }
}

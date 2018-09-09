package com.peoplentech.devkh.alumnicontact;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.peoplentech.devkh.alumnicontact.adapter.BloodAdapter;
import com.peoplentech.devkh.alumnicontact.model.DeptSpinner;
import com.peoplentech.devkh.alumnicontact.model.MainUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShowBatchActivity extends AppCompatActivity {

    String URL = "http://10.16.20.41/alumni/batch/getByBatch.php?id=";
    String batch_name;
    //int batchName;
    LinearLayout DepartmentLayout;
    //SwipeRefreshLayout depRefreshdoctor;
    RecyclerView depRecyclerView;
    private LinearLayoutManager depGridLayout;
    private List<MainUser> name;
    private BloodAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_batch);

        //added
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            batch_name = (String) bundle.get("batch_number");
            //batchName = Integer.parseInt(batch_name);
            //URL = "http://10.16.20.41/alumni/batch/getByBatch.php?id=" + batch_name;
        }

            depRecyclerView = (RecyclerView) findViewById(R.id.recyclerviewShowBatch);
            name = new ArrayList<>();
            depGridLayout = new LinearLayoutManager(ShowBatchActivity.this);
            depRecyclerView.setLayoutManager(depGridLayout);

            adapter = new BloodAdapter(ShowBatchActivity.this, name);
            depRecyclerView.setAdapter(adapter);

            if (adapter.getItemCount() > 0) {

                name.clear();
                adapter.notifyItemRangeRemoved(0,adapter.getItemCount()-1);
            }


            loadAllData(0, batch_name);

            depRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                    if (depGridLayout.findLastCompletelyVisibleItemPosition() == name.size() - 1) {
                        loadAllData(name.get(name.size() - 1).getuserId(), batch_name);

                    }

                }
            });

    }

    //Recyclerview method
    private void loadAllData(int id, final String batch) {
        if (depRecyclerView.getAdapter() == null) {
            depRecyclerView.setAdapter(adapter);
        }

        @SuppressLint("StaticFieldLeak") AsyncTask<Integer, Void, Void> asyncTask = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... desIDs) {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(URL + desIDs[0] + "&batch=" + batch)
                        .build();
                try {
                    Response response = client.newCall(request).execute();

                    JSONArray array = new JSONArray(response.body().string());

                    for (int i = 0; i < array.length(); i++) {

                    JSONObject object = array.getJSONObject(i);
                    //for (int i = 0; i < object.length(); i++) {

                        MainUser doc = new MainUser(object.getInt("id"), object.getString("name"),
                                object.getString("gender"), object.getString("blood"), object.getString("address"),
                                object.getString("phone") ,object.getString("email"),
                                object.getString("dept"),object.getString("batch"),object.getString("job"));
                        ShowBatchActivity.this.name.add(doc);
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
                // depRefreshdoctor.setRefreshing(false);
                adapter.notifyDataSetChanged();
            }
        };

        asyncTask.execute(id);
    }
}

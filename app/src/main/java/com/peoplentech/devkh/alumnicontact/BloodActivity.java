package com.peoplentech.devkh.alumnicontact;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.support.design.widget.Snackbar;

import com.peoplentech.devkh.alumnicontact.adapter.BloodAdapter;
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

public class BloodActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    LinearLayout DoctorAppointLayout;
    SwipeRefreshLayout refreshdoctor;
    RecyclerView recyclerView;
    private LinearLayoutManager gridLayout;
    private List<MainUser> name;
    private BloodAdapter adapter;
    String WEB_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood);

        DoctorAppointLayout = (LinearLayout) findViewById(R.id.bloodLayout);
        refreshdoctor = (SwipeRefreshLayout) findViewById(R.id.doctorRefresh);
        refreshdoctor.setOnRefreshListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        initCustomSpinner();
    }

    private void initCustomSpinner() {

        Spinner spinnerCustom= (Spinner) findViewById(R.id.doctorSpinner);
        // Spinner Drop down elements
        ArrayList<String> areas = new ArrayList<String>();
        areas.add("Select Blood Group");
        areas.add("A+");
        areas.add("A-");
        areas.add("B+");
        areas.add("B-");
        areas.add("AB+");
        areas.add("AB-");
        areas.add("O+");
        areas.add("O-");

        CustomSpinnerAdapter customSpinnerAdapter=new CustomSpinnerAdapter(this, areas);
        spinnerCustom.setAdapter(customSpinnerAdapter);
        spinnerCustom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
                name = new ArrayList<>();
                gridLayout = new LinearLayoutManager(BloodActivity.this);
                recyclerView.setLayoutManager(gridLayout);

                adapter = new BloodAdapter(BloodActivity.this, name);
                recyclerView.setAdapter(adapter);

                if (adapter.getItemCount() > 0) {

                    name.clear();
                    adapter.notifyItemRangeRemoved(0,adapter.getItemCount()-1);
                }

                String item = parent.getItemAtPosition(position).toString();
                if (position == 0) {

                    WEB_URL = "http://10.16.20.41/alumni/blood/allDonor.php?id=";
                    loadAllData(0);

                    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                            if (gridLayout.findLastCompletelyVisibleItemPosition() == name.size() - 1) {
                                loadAllData(name.get(name.size() - 1).getuserId());
                            }

                        }
                    });
                } else if (position > 0) {

                    //Toast.makeText(parent.getContext(), "Selected " + item, Toast.LENGTH_SHORT).show();
                    switch (item) {
                        case "A+":

                            WEB_URL = "http://10.16.20.41/alumni/blood/A_positive.php?id=";

                            getDataFromDB(0);

                            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                @Override
                                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                                    if (gridLayout.findLastCompletelyVisibleItemPosition() == name.size() - 1) {
                                        getDataFromDB(name.get(name.size() - 1).getuserId());
                                    }
                                }
                            });

                            break;
                        case "A-":
                            //Toast.makeText(parent.getContext(), "World", Toast.LENGTH_SHORT).show();
                            WEB_URL = "http://10.16.20.41/alumni/blood/A_negative.php?id=";

                            getDataFromDB(0);

                            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                @Override
                                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                                    if (gridLayout.findLastCompletelyVisibleItemPosition() == name.size() - 1) {
                                        getDataFromDB(name.get(name.size() - 1).getuserId());
                                    }
                                }
                            });
                            break;
                        case "B+":
                            WEB_URL = "http://10.16.20.41/alumni/blood/B_positive.php?id=";

                            getDataFromDB(0);

                            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                @Override
                                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                                    if (gridLayout.findLastCompletelyVisibleItemPosition() == name.size() - 1) {
                                        getDataFromDB(name.get(name.size() - 1).getuserId());
                                    }
                                }
                            });
                            break;
                        case "B-":
                            WEB_URL = "http://10.16.20.41/alumni/blood/B_negative.php?id=";

                            getDataFromDB(0);

                            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                @Override
                                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                                    if (gridLayout.findLastCompletelyVisibleItemPosition() == name.size() - 1) {
                                        getDataFromDB(name.get(name.size() - 1).getuserId());
                                    }
                                }
                            });
                            break;
                        case "AB+":
                            WEB_URL = "http://10.16.20.41/alumni/blood/AB_positive.php?id=";

                            getDataFromDB(0);

                            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                @Override
                                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                                    if (gridLayout.findLastCompletelyVisibleItemPosition() == name.size() - 1) {
                                        getDataFromDB(name.get(name.size() - 1).getuserId());
                                    }
                                }
                            });
                            break;
                        case "AB-":
                            WEB_URL = "http://10.16.20.41/alumni/blood/AB_negative.php?id=";

                            getDataFromDB(0);

                            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                @Override
                                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                                    if (gridLayout.findLastCompletelyVisibleItemPosition() == name.size() - 1) {
                                        getDataFromDB(name.get(name.size() - 1).getuserId());
                                    }
                                }
                            });
                            break;
                        case "O+":
                            WEB_URL = "http://10.16.20.41/alumni/blood/O_positive.php?id=";

                            getDataFromDB(0);

                            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                @Override
                                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                                    if (gridLayout.findLastCompletelyVisibleItemPosition() == name.size() - 1) {
                                        getDataFromDB(name.get(name.size() - 1).getuserId());
                                    }
                                }
                            });
                            break;
                        case "O-":
                            WEB_URL = "http://10.16.20.41/alumni/blood/O_negative.php?id=";

                            getDataFromDB(0);

                            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                @Override
                                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                                    if (gridLayout.findLastCompletelyVisibleItemPosition() == name.size() - 1) {
                                        getDataFromDB(name.get(name.size() - 1).getuserId());
                                    }
                                }
                            });
                            break;
                    }

                }
                int size = name.size();
                if (size > 0){
                    for (int i = 0; i < size; i++){
                        name.remove(0);
                    }
                    adapter.notifyItemRemoved(position);
                    adapter.notifyItemRangeChanged(position, name.size());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public class CustomSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

        private Context activity;
        private ArrayList<String> asr;

        public CustomSpinnerAdapter(Context context,ArrayList<String> asr) {
            this.asr=asr;
            activity = context;
        }



        public int getCount()
        {
            return asr.size();
        }

        public Object getItem(int i)
        {
            return asr.get(i);
        }

        public long getItemId(int i)
        {
            return (long)i;
        }



        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView txt = new TextView(BloodActivity.this);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(18);
            txt.setGravity(Gravity.CENTER_VERTICAL);
            txt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            txt.setText(asr.get(position));
            if (position > 0) {
                txt.setTextColor(Color.parseColor("#000000"));
            } else {
                txt.setTextColor(Color.parseColor("#808080"));
            }
            return txt;
        }

        public View getView(int i, View view, ViewGroup viewgroup) {
            TextView txt = new TextView(BloodActivity.this);
            txt.setGravity(Gravity.CENTER);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(22);
            //txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_drop, 0);
            txt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_person, 0, R.drawable.ic_dropdown, 0);
            txt.setText(asr.get(i));
            txt.setTextColor(Color.parseColor("#ffffff"));
            return txt;
        }

    }

    //Recyclerview method
    private void getDataFromDB(int id) {
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

                        MainUser doc = new MainUser(object.getInt("id"), object.getString("name"),
                                object.getString("gender"), object.getString("blood"), object.getString("address"),
                                object.getString("phone") ,object.getString("email"),
                                object.getString("dept"),object.getString("batch"),object.getString("job"));

                        BloodActivity.this.name.add(doc);
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
                refreshdoctor.setRefreshing(false);
                adapter.notifyDataSetChanged();
            }
        };

        asyncTask.execute(id);
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

                        MainUser doc = new MainUser(object.getInt("id"), object.getString("name"),
                                object.getString("gender"), object.getString("blood"), object.getString("address"),
                                object.getString("phone") ,object.getString("email"),
                                object.getString("dept"),object.getString("batch"),object.getString("job"));
                        BloodActivity.this.name.add(doc);
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
                refreshdoctor.setRefreshing(false);
                adapter.notifyDataSetChanged();
            }
        };

        asyncTask.execute(id);
    }

    @Override
    public void onRefresh() {
        ConnectivityManager conntivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(conntivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState()== NetworkInfo.State.CONNECTED ||
                conntivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState()== NetworkInfo.State.CONNECTED){

            //clear data
            name.clear();
            recyclerView.setAdapter(null);
            //adapter.notifyItemRangeRemoved(0,adapter.getItemCount()-1);
            //load
            loadAllData(0);
        } else {

            refreshdoctor.setRefreshing(false);
            Snackbar snackbar = Snackbar.make(DoctorAppointLayout, "No internet Connection", Snackbar.LENGTH_LONG);
            snackbar.show();
        }

    }

}

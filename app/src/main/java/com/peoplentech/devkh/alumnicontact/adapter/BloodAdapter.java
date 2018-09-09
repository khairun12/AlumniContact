package com.peoplentech.devkh.alumnicontact.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peoplentech.devkh.alumnicontact.BloodActivity;
import com.peoplentech.devkh.alumnicontact.R;
import com.peoplentech.devkh.alumnicontact.model.MainUser;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by User on 5/5/2018.
 */

public class BloodAdapter extends RecyclerView.Adapter<BloodAdapter.ViewHolder> {

    private Context context;
    private List<MainUser> user;

    public BloodAdapter(Context context, List<MainUser> user) {
        this.context = context;
        this.user = user;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_main,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.user_name.setText(user.get(position).getName());
        holder.user_gender.setText(user.get(position).getGender());
        holder.user_blood.setText(user.get(position).getBlood());
        holder.user_address.setText(user.get(position).getAddress());
        holder.user_phone.setText(user.get(position).getPhone());
        holder.user_email.setText(user.get(position).getEmail());
        holder.user_dept.setText(user.get(position).getDept());
        holder.user_batch.setText(user.get(position).getBatch());
        holder.user_job.setText(user.get(position).getJob());
    }



    @Override
    public int getItemCount() {
        return user.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView user_name;
        public TextView user_gender;
        public TextView user_blood;
        public TextView user_address;
        public TextView user_phone;
        public TextView user_email;
        public TextView user_dept;
        public TextView user_batch;
        public TextView user_job;

        public TextView user_callHere;
        public TextView user_emailHere;


        public ViewHolder(View itemView) {
            super(itemView);
            user_name = (TextView) itemView.findViewById(R.id.name);
            user_gender = (TextView) itemView.findViewById(R.id.gender);
            user_blood = (TextView) itemView.findViewById(R.id.blood);
           user_address = (TextView) itemView.findViewById(R.id.address);
            user_phone = (TextView) itemView.findViewById(R.id.phone);
            user_email = (TextView) itemView.findViewById(R.id.email);
            user_dept = (TextView) itemView.findViewById(R.id.dept);
            user_batch= (TextView) itemView.findViewById(R.id.batch);
            user_job = (TextView) itemView.findViewById(R.id.job);




            user_callHere = (TextView) itemView.findViewById(R.id.callhere);
            user_emailHere = (TextView) itemView.findViewById(R.id.emailhere);

            user_emailHere.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String mail = user_email.getText().toString();
                    Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
                    mailIntent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    if (!TextUtils.isEmpty(mail)){
                        mailIntent.setData(Uri.parse("mailto:" + mail));
                        context.getApplicationContext().startActivity(mailIntent);
                    }
                }
            });
            user_callHere.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String phone = user_phone.getText().toString();
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    if (!TextUtils.isEmpty(phone)) {
                        callIntent.setData(Uri.parse("tel:" + phone));
                        context.getApplicationContext().startActivity(callIntent);
                    }
                }
            });

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            showPopupMenu(v,position);

        }
    }

    private void showPopupMenu(View view, int position) {

        /*Intent intent = new Intent(context.getApplicationContext(), ShowDoctorActivity.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("doc_name", doctor.get(position).getName());
        intent.putExtra("doc_area", doctor.get(position).getArea());
        intent.putExtra("doc_email", doctor.get(position).getEmail());
        intent.putExtra("doc_phone", doctor.get(position).getChamberNumber());
        intent.putExtra("doc_deg", doctor.get(position).getEduDegree());
        intent.putExtra("cham_name", doctor.get(position).getChamberName());
        intent.putExtra("cham_address", doctor.get(position).getChamberAddress());
        intent.putExtra("edu_inst", doctor.get(position).getEduInstitute());
        intent.putExtra("job_inst", doctor.get(position).getJobInstitute());
        intent.putExtra("job_des", doctor.get(position).getJobDegree());
        context.getApplicationContext().startActivity(intent);*/
    }

}

package com.peoplentech.devkh.alumnicontact.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peoplentech.devkh.alumnicontact.MainActivity;
import com.peoplentech.devkh.alumnicontact.R;
import com.peoplentech.devkh.alumnicontact.ShowBatchActivity;
import com.peoplentech.devkh.alumnicontact.model.BatchNo;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by User on 5/10/2018.
 */

public class BatchAdapter extends RecyclerView.Adapter<BatchAdapter.ViewHolder> {

    private Context context;
    private List<BatchNo> batch;

    public BatchAdapter(Context context, List<BatchNo> batch) {
        this.context = context;
        this.batch = batch;
    }

    @Override
    public BatchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_batch,parent,false);

        return new BatchAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final BatchAdapter.ViewHolder holder, int position) {

        holder.batch_name.setText(batch.get(position).getBatchNumber());

    }



    @Override
    public int getItemCount() {
        return batch.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView batch_name;
        public CardView batchCardView;



        public ViewHolder(View itemView) {
            super(itemView);
            batch_name = (TextView) itemView.findViewById(R.id.batchnoMain);
            batchCardView = (CardView) itemView.findViewById(R.id.cardviewBatch);
            batchCardView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            showPopupMenu(v,position);

        }
    }

    private void showPopupMenu(View view, int position) {

        Intent newbatch = new Intent(context.getApplicationContext(), ShowBatchActivity.class);
        newbatch.addFlags(FLAG_ACTIVITY_NEW_TASK);
        newbatch.putExtra("batch_number", batch.get(position).getBatchNumber());
        context.getApplicationContext().startActivity(newbatch);
    }

}
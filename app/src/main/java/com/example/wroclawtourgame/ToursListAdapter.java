package com.example.wroclawtourgame;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wroclawtourgame.model.Tour;

import java.util.List;

public class ToursListAdapter extends RecyclerView.Adapter<ToursListAdapter.ToursViewHolder> {

    private List<Tour> mTours;
    private LayoutInflater mInflater;
    private Context mContext;

    public ToursListAdapter(List<Tour> tours, Context context) {
        mTours = tours;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ToursViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.tour_row, parent, false);
        return new ToursViewHolder(v, mTours);
    }

    @Override
    public void onBindViewHolder(ToursListAdapter.ToursViewHolder holder, int position) {
        holder.tvTourName.setText(mTours.get(position).getName());
        holder.tvTourDescription.setText(mTours.get(position).getDescription());
        holder.tvTourDuration.setText(mTours.get(position).getDuration());
        if (mTours.get(position).isFinished()) {
            holder.ivIsFinished.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mTours.size();
    }

    class ToursViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTourName, tvTourDescription, tvTourDuration;
        ImageView ivIsFinished;
        private List<Tour> mTours;

        public ToursViewHolder(View itemView, List<Tour> tours) {
            super(itemView);

            mTours = tours;

            tvTourName = itemView.findViewById(R.id.tourNameTextView);
            tvTourDescription = itemView.findViewById(R.id.tourDescriptionTextView);
            tvTourDuration = itemView.findViewById(R.id.tourDurationTextView);
            ivIsFinished = itemView.findViewById(R.id.isVisitedImageView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Tour currTour = mTours.get(getAdapterPosition());

            if (!currTour.hasPoints()) {
                Toast.makeText(view.getContext(), (R.string.no_points_in_this_tour), Toast.LENGTH_LONG).show();
            } else if (currTour.isFinished()) {
                Toast.makeText(view.getContext(), (R.string.all_points_visited), Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(view.getContext(), PointActivity.class);
                intent.putExtra("tourObject", currTour);
                view.getContext().startActivity(intent);
            }
        }
    }

}

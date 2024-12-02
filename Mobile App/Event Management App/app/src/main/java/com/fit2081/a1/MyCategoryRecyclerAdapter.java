package com.fit2081.a1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyCategoryRecyclerAdapter extends RecyclerView.Adapter<MyCategoryRecyclerAdapter.CatCustomViewHolder>{

    ArrayList<EventCategory> data = new ArrayList<EventCategory>();

    public MyCategoryRecyclerAdapter() {
    }


    @NonNull
    @Override
    public CatCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_card_layout,parent,false);
        CatCustomViewHolder viewHolder = new CatCustomViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CatCustomViewHolder holder, int position) {
        holder.tvCatId.setText(String.valueOf(data.get(position).getCatId()));
        holder.tvCatName.setText(String.valueOf(data.get(position).getCatName()));
        holder.tvEventCount.setText(String.valueOf(data.get(position).getEventCount()));
        if (data.get(position).isActive()){
            holder.tvIsActive.setText("Yes");
        }
        else {
            holder.tvIsActive.setText("No");
        }

        holder.cardView.setOnClickListener(v -> {
            String selectedEventLocation = data.get(position).getEventLocation();

            Context context = holder.cardView.getContext();
            Intent intent = new Intent(context,GoogleMapActivity.class);
            intent.putExtra("Location",selectedEventLocation);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        if (this.data != null) { // if data is not null
            return this.data.size(); // then return the size of ArrayList
        }

        // else return zero if data is null
        return 0;
    }

    public void setData(ArrayList<EventCategory> data) {
        this.data = data;
    }


    public class CatCustomViewHolder extends RecyclerView.ViewHolder {

        public TextView tvCatId;
        public TextView tvCatName;
        public TextView tvEventCount;
        public TextView tvIsActive;
        public View cardView;

        public CatCustomViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView;
            tvCatId = itemView.findViewById(R.id.tv_cat_id);
            tvCatName = itemView.findViewById(R.id.tv_cat_name);
            tvEventCount = itemView.findViewById(R.id.tv_count);
            tvIsActive = itemView.findViewById(R.id.tv_cat_active);
        }
    }
}

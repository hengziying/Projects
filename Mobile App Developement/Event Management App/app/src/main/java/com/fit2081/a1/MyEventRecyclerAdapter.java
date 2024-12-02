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

public class MyEventRecyclerAdapter extends RecyclerView.Adapter<MyEventRecyclerAdapter.EventCustomViewHolder> {

    ArrayList<Event> data = new ArrayList<Event>();


    public void setData(ArrayList<Event> data) {
        this.data = data;
    }

    public MyEventRecyclerAdapter() {
    }

    @NonNull
    @Override
    public EventCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_card_layout,parent,false);
        EventCustomViewHolder viewHolder = new EventCustomViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventCustomViewHolder holder, int position) {
        String event_id = "Id  "+ String.valueOf(data.get(position).getEventId());
        String event_name ="Name  "+ String.valueOf(data.get(position).getEventName());
        String cat_id ="Category Id  "+ String.valueOf(data.get(position).getCatId());
        String tickets ="Tickets  "+ String.valueOf(data.get(position).getTicketsAvail());
        holder.tvEventId.setText(event_id);
        holder.tvEventName.setText(event_name);
        holder.tvCatId.setText(cat_id);
        holder.tvTickets.setText(tickets);
        if (data.get(position).isActive()){
            holder.tvIsActive.setText("Active");
        }
        else {
            holder.tvIsActive.setText("Inactive");
        }

        holder.cardView.setOnClickListener(v -> {
            String selectedEventName = data.get(position).getEventName();

            Context context = holder.cardView.getContext();
            Intent intent = new Intent(context,EventGoogleResult.class);
            intent.putExtra("eventName",selectedEventName);
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

    public class EventCustomViewHolder extends RecyclerView.ViewHolder {

        public TextView tvEventId;
        public TextView tvEventName;
        public TextView tvCatId;
        public TextView tvTickets;
        public TextView tvIsActive;
        public View cardView;

        public EventCustomViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView;
            tvEventId = itemView.findViewById(R.id.tv_event_id);
            tvEventName = itemView.findViewById(R.id.tv_event_name);
            tvTickets = itemView.findViewById(R.id.tv_tickets);
            tvCatId = itemView.findViewById(R.id.tv_event_cat_id);
            tvIsActive = itemView.findViewById(R.id.tv_event_active);
        }
    }
}

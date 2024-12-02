package com.fit2081.a1;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Event {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private  int id;

    @ColumnInfo(name = "columnEventId")
    private String eventId;

    @ColumnInfo(name = "columnEventCatId")
    private String catId;

    @ColumnInfo(name = "columnEventName")
    private String eventName;

    @ColumnInfo(name = "columnTickets")
    private int ticketsAvail;

    @ColumnInfo(name = "columnIsActive")
    private boolean isActive;

    public Event(String eventId, String eventName, String catId, int ticketsAvail, boolean isActive) {
        this.eventId = eventId;
        this.catId = catId;
        this.eventName = eventName;
        this.ticketsAvail = ticketsAvail;
        this.isActive = isActive;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getTicketsAvail() {
        return ticketsAvail;
    }

    public void setTicketsAvail(int ticketsAvail) {
        this.ticketsAvail = ticketsAvail;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

package com.fit2081.a1;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class EventCategory {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private  int id;

    @ColumnInfo(name = "columnCatName")
    private String catName;

    @ColumnInfo(name = "columnCatId")
    private String catId;
    @ColumnInfo(name = "columnEventCount")
    private int eventCount;

    @ColumnInfo(name = "columnOriEventCount")
    private int ori_event_count;

    @ColumnInfo(name = "columnIsActive")
    private boolean isActive;

    @ColumnInfo(name = "columnEventLocation")
    private String eventLocation;

    public EventCategory() {
        // Default constructor required by Room
    }

    public EventCategory(String catName, String catId, int eventCount, boolean isActive,String location) {
        this.catName = catName;
        this.catId = catId;
        this.eventCount = eventCount;
        this.isActive = isActive;
        this.ori_event_count = eventCount;
        this.eventLocation = location;
    }

    public int getOri_event_count() {
        return ori_event_count;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public int getEventCount() {
        return eventCount;
    }

    public void setEventCount(int eventCount) {
        this.eventCount = eventCount;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setOri_event_count(int ori_event_count) {
        this.ori_event_count = ori_event_count;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventCatLocation) {
        this.eventLocation = eventCatLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

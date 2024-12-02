package com.fit2081.a1.provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.fit2081.a1.Event;

import java.util.List;

@Dao
public interface EventDAO {

    @Query("select * from event")
    LiveData<List<Event>> getAllItems();

    @Insert
    void addItem(Event item);

    @Query("DELETE FROM event")
    void deleteAllEvent();

    @Query("DELETE FROM event WHERE id = (SELECT MAX(id) FROM event)")
    void deleteLastSavedEvent();

    @Query("SELECT columnEventCatId FROM event WHERE id = (SELECT MAX(id) FROM event)")
    String getLastSavedEventCatId();

    @Query("SELECT columnEventId FROM event WHERE id = (SELECT MAX(id) FROM event)")
    String getLastSavedEventId();
}

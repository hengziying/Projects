package com.fit2081.a1.provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.fit2081.a1.EventCategory;

import java.util.List;

@Dao
public interface EventCategoryDAO {
    @Query("select * from eventcategory")
    LiveData<List<EventCategory>> getAllItems();

    @Insert
    void addItem(EventCategory item);

    @Query("DELETE FROM eventcategory")
    void deleteAllEventCat();

    @Query("SELECT * FROM eventcategory WHERE columnCatId = :catId LIMIT 1")
    EventCategory getCategoryById(String catId);

    @Query("UPDATE eventcategory SET columnEventCount = columnEventCount + 1 WHERE columnCatId = :catId")
    void incrementEventCount(String catId);

    @Query("UPDATE eventcategory SET columnEventCount = columnEventCount - 1 WHERE columnCatId = :catId")
    void decrementEventCount(String catId);

    @Query("UPDATE eventcategory SET columnEventCount = columnOriEventCount")
    void resetEventCount();

}

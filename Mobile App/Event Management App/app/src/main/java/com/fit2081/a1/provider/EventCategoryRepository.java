package com.fit2081.a1.provider;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.fit2081.a1.EventCategory;

import java.util.List;

public class EventCategoryRepository {
    // private class variable to hold reference to DAO
    private EventCategoryDAO eventCategoryDAO;
    // private class variable to temporary hold all the items retrieved and pass outside of this class
    private LiveData<List<EventCategory>> allEvenCatLiveData;

    // constructor to initialise the repository class
    EventCategoryRepository(Application application) {
        // get reference/instance of the database
        EMADatabase db = EMADatabase.getDatabase(application);

        // get reference to DAO, to perform CRUD operations
        eventCategoryDAO = db.eventCategoryDAO();

        // once the class is initialised get all the items in the form of LiveData
        allEvenCatLiveData = eventCategoryDAO.getAllItems();
    }

    /**
     * Repository method to get all cards
     * @return LiveData of type List<Item>
     */
    LiveData<List<EventCategory>> getAllEventCat() {
        return allEvenCatLiveData;
    }

    /**
     * Repository method to insert one single item
     * @param eventCategory object containing details of new Item to be inserted
     */
    void insert(EventCategory eventCategory) {
        EMADatabase.databaseWriteExecutor.execute(() -> eventCategoryDAO.addItem(eventCategory));
    }

    /**
     * Repository method to delete all records
     */
    void deleteAll() {
        EMADatabase.databaseWriteExecutor.execute(() -> eventCategoryDAO.deleteAllEventCat());
    }

    EventCategory getCategoryById(String catId){
        try {
            return EMADatabase.databaseWriteExecutor.submit(() -> eventCategoryDAO.getCategoryById(catId)).get();
        } catch (Exception e) {
            return null;
        }
    }

    void incrementEventCount(String catId){
        EMADatabase.databaseWriteExecutor.execute(() -> eventCategoryDAO.incrementEventCount(catId));
    }

    void decrementEventCount(String catId){
        EMADatabase.databaseWriteExecutor.execute(() -> eventCategoryDAO.decrementEventCount(catId));
    }

    void resetEventCount(){
        EMADatabase.databaseWriteExecutor.execute(() -> eventCategoryDAO.resetEventCount());
    }
}

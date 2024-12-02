package com.fit2081.a1.provider;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.fit2081.a1.Event;

import java.util.List;

public class EventRepository {
    // private class variable to hold reference to DAO
    private EventDAO eventDAO;
    // private class variable to temporary hold all the items retrieved and pass outside of this class
    private LiveData<List<Event>> allEventLiveData;

    // constructor to initialise the repository class
    EventRepository(Application application) {
        // get reference/instance of the database
        EMADatabase db = EMADatabase.getDatabase(application);

        // get reference to DAO, to perform CRUD operations
        eventDAO = db.eventDAO();

        // once the class is initialised get all the items in the form of LiveData
        allEventLiveData = eventDAO.getAllItems();
    }

    /**
     * Repository method to get all cards
     * @return LiveData of type List<Item>
     */
    LiveData<List<Event>> getAllEvent() {
        return allEventLiveData;
    }

    /**
     * Repository method to insert one single item
     * @param event object containing details of new Item to be inserted
     */
    void insert(Event event) {
        EMADatabase.databaseWriteExecutor.execute(() -> eventDAO.addItem(event));
    }

    /**
     * Repository method to delete all records
     */
    void deleteAll() {
        EMADatabase.databaseWriteExecutor.execute(() -> eventDAO.deleteAllEvent());
    }

    void deleteLastSavedEvent(){
        EMADatabase.databaseWriteExecutor.execute(() -> eventDAO.deleteLastSavedEvent());
    }

    String getLastSavedEventCatId(){
        try {
            return EMADatabase.databaseWriteExecutor.submit(() -> eventDAO.getLastSavedEventCatId()).get();
        } catch (Exception e) {
            return null;
        }
    }

    String getLastSavedEventId(){
        try {
            return EMADatabase.databaseWriteExecutor.submit(() -> eventDAO.getLastSavedEventId()).get();
        } catch (Exception e) {
            return null;
        }
    }
}

package com.fit2081.a1.provider;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.fit2081.a1.EventCategory;

import java.util.List;

public class EventCategoryViewModel extends AndroidViewModel {
    // reference to CardRepository
    private EventCategoryRepository repository;
    // private class variable to temporary hold all the items retrieved and pass outside of this class
    private LiveData<List<EventCategory>> allEventCatLiveData;

    public EventCategoryViewModel(@NonNull Application application) {
        super(application);

        // get reference to the repository class
        repository = new EventCategoryRepository(application);

        // get all items by calling method defined in repository class
        allEventCatLiveData = repository.getAllEventCat();
    }

    /**
     * ViewModel method to get all cards
     * @return LiveData of type List<Item>
     */
    public LiveData<List<EventCategory>> getAllEventCat() {
        return allEventCatLiveData;
    }

    /**
     * ViewModel method to insert one single item,
     * usually calling insert method defined in repository class
     * @param eventCategory object containing details of new Item to be inserted
     */
    public void insert(EventCategory eventCategory) {
        repository.insert(eventCategory);
    }

    /**
     * ViewModel method to delete all records
     */
    public void deleteAll() {
        repository.deleteAll();
    }

    public EventCategory getCategoryById(String catId){
        return repository.getCategoryById(catId);
    }

    public void incrementEventCount (String catId){
        repository.incrementEventCount(catId);
    }

    public void decrementEventCount (String catId){
        repository.decrementEventCount(catId);
    }

    public void resetEventCount(){
        repository.resetEventCount();
    }
}

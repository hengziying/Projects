package com.fit2081.a1.provider;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.fit2081.a1.Event;

import java.util.List;

public class EventViewModel extends AndroidViewModel {

    // reference to CardRepository
    private EventRepository repository;
    // private class variable to temporary hold all the items retrieved and pass outside of this class
    private LiveData<List<Event>> allEventLiveData;

    public EventViewModel(@NonNull Application application) {
        super(application);

        // get reference to the repository class
        repository = new EventRepository(application);

        // get all items by calling method defined in repository class
        allEventLiveData = repository.getAllEvent();
    }

    /**
     * ViewModel method to get all cards
     * @return LiveData of type List<Item>
     */
    public LiveData<List<Event>> getAllEvent() {
        return allEventLiveData;
    }

    /**
     * ViewModel method to insert one single item,
     * usually calling insert method defined in repository class
     * @param event object containing details of new Item to be inserted
     */
    public void insert(Event event) {
        repository.insert(event);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void deleteLastSavedEvent(){
        repository.deleteLastSavedEvent();
    }

    public String getLastSavedEventCatId(){
        return repository.getLastSavedEventCatId();
    }

    public String getLastSavedEventId(){
        return repository.getLastSavedEventCatId();
    }
}

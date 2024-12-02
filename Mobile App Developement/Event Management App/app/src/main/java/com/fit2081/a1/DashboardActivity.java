package com.fit2081.a1;

import static com.fit2081.a1.Utils.generateRandomDigits;
import static com.fit2081.a1.Utils.generateRandomString;
import static com.fit2081.a1.Utils.isStringAlphanumeric;
import static com.fit2081.a1.Utils.isStringAlphabetic;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.GestureDetectorCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import com.fit2081.a1.provider.EventCategoryViewModel;
import com.fit2081.a1.provider.EventViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class DashboardActivity extends AppCompatActivity {

    private DrawerLayout drawerlayout;
    private NavigationView navigationView;
    TextView tvCatId;
    TextView tvName;
    TextView tvTicket;
    Switch switchIsActive;
    TextView tvEventID;

    TextView tvTouch;
    FloatingActionButton fab;

    View touchpad;

    // add as class variable of MainActivity
    private EventCategoryViewModel eventCategoryViewModel;
    private EventViewModel eventViewModel;

    // help detect basic gestures like scroll, single tap, double tap, etc
    private GestureDetectorCompat mDetector;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        Toolbar toolbar=(Toolbar)findViewById(R.id.appBarHeader);
        drawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerlayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerlayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new MyNavigationListener());


        //set up floating button
        tvCatId = findViewById(R.id.editTextCatID1);
        tvName = findViewById(R.id.editTextEventName1);
        tvTicket = findViewById(R.id.editTextTicketsAvail1);
        switchIsActive = findViewById(R.id.switchEventActive1);
        tvEventID = findViewById(R.id.editTextEventID);
        tvTouch = findViewById(R.id.tvTouch);


        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean saved = addEvent(view);
                if (saved){
                    Snackbar.make(view, "Event saved", Snackbar.LENGTH_LONG)
                            .setAction("UNDO", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    undoSavedEvent();
                                }
                            }).show();
                }
            }
        });

        eventCategoryViewModel = new ViewModelProvider(this).get(EventCategoryViewModel.class);
        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);

        touchpad = findViewById(R.id.touchpad);
        CustomGestureDetector customGestureDetector = new CustomGestureDetector();
        mDetector = new GestureDetectorCompat(this, customGestureDetector);
        touchpad.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDetector.onTouchEvent(event);
                return true;
            }
        });

        mDetector.setOnDoubleTapListener(customGestureDetector);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    /**
     * A convenience class to extend when you only want to listen for a subset of all the gestures.
     */
    class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDoubleTap(@NonNull MotionEvent e) {
            tvTouch.setText("onDoubleTap");
            addEvent(fab);
            return super.onDoubleTap(e);
        }


        @Override
        public void onLongPress(@NonNull MotionEvent e) {
            tvTouch.setText("onLongPress");
            clearEventForm();
            super.onLongPress(e);
        }

    }


    public boolean addEvent(View view) {
        String catIdString = tvCatId.getText().toString();
        String nameString = tvName.getText().toString();
        String ticketString = tvTicket.getText().toString();
        boolean isActiveBool = switchIsActive.isChecked();
        int ticketInt = 0;


        if (!ticketString.isEmpty()){
            ticketInt = Integer.parseInt(ticketString);
        }

        //check whether the name or cat id is empty
        if (nameString.isEmpty() || catIdString.isEmpty()){
            Toast.makeText(getApplicationContext(),"Event name or category ID cannot be empty.",Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!isStringAlphanumeric(nameString) && !isStringAlphabetic(nameString)){
            Toast.makeText(getApplicationContext(),"Invalid event name",Toast.LENGTH_SHORT).show();
            return false;
        }
        //tickets cannot be negative
        if (ticketInt<0){
            Toast.makeText(getApplicationContext(),"Invalid 'Tickets available",Toast.LENGTH_SHORT).show();
            return false;
        }

        //if the cat id does not exist
        if (!catIdValid(catIdString)){
            Toast.makeText(getApplicationContext(),"Category does not exist.",Toast.LENGTH_SHORT).show();
            return false;
        }

        String event_id = generateEventId();
        addEventToDB(event_id,catIdString,nameString,ticketInt,isActiveBool);

        tvEventID.setText(event_id);
        incrementEventCount(catIdString);
        Toast.makeText(getApplicationContext(),"Event Saved Successfully: "+ event_id +" to " + catIdString,Toast.LENGTH_SHORT).show();
        updateCatList();


        Snackbar.make(view, "Event saved", Snackbar.LENGTH_LONG)
                    .setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            undoSavedEvent();
                        }
                    }).show();

        return true;

    }

    public void addEventToDB(String eventId, String catId,String name, int ticket, boolean active) {

        Event event = new Event(eventId,name,catId,ticket,active);

        // insert new record to database
        eventViewModel.insert(event);

    }


    public void undoSavedEvent(){

        String eventId = eventViewModel.getLastSavedEventId();
        String catId  = eventViewModel.getLastSavedEventCatId();
        decrementEventCount(catId);
        eventViewModel.deleteLastSavedEvent();
        Toast.makeText(getApplicationContext(),"Event ID: "+eventId+" undo successful.",Toast.LENGTH_SHORT).show();

    }

    static String generateEventId(){
        String eventCatId = "E"+generateRandomString(2)+"-"+generateRandomDigits(5);
        return eventCatId;

    }


    public void dltCatList(){
        eventCategoryViewModel.deleteAll();
        Toast.makeText(getApplicationContext(),"All categories deleted.",Toast.LENGTH_SHORT).show();
    }

    public void dltEventList(){
        eventViewModel.deleteAll();
        resetEventCount();
        Toast.makeText(getApplicationContext(),"All events deleted.",Toast.LENGTH_SHORT).show();
    }

    public void updateCatList(){
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_list_category,new FragmentListCategory()).commit();

    }


    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.options_menu,menu);

        return true;
    }


    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (item.getItemId() == R.id.option_clear_event_form) {
            clearEventForm();
        }
        else if (item.getItemId() == R.id.option_dlt_cat){
            dltCatList();
        }
        else if (item.getItemId() == R.id.option_dlt_event){
            dltEventList();
        }
        return true;
    }

    class MyNavigationListener implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // get the id of the selected item
            int id = item.getItemId();
            if (id == R.id.nav_view_cat) {
                Intent intent = new Intent(getApplicationContext(), ListCategoryActivity.class);
                startActivity(intent);
            } else if (id == R.id.nav_add_cat) {
                Intent intent = new Intent(getApplicationContext(), NewEventCatActivity.class);
                startActivity(intent);
            }else if (id == R.id.nav_view_events) {
                Intent intent = new Intent(getApplicationContext(), ListEventActivity.class);
                startActivity(intent);
            }else if (id == R.id.nav_logout) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
            // close the drawer
            drawerlayout.closeDrawers();
            // tell the OS
            return true;
        }
    }

    public void clearEventForm(){
        tvEventID.setText("");
        tvName.setText("");
        tvCatId.setText("");
        tvTicket.setText("");
        switchIsActive.setChecked(false);
    }

    public void resetEventCount(){
        eventCategoryViewModel.resetEventCount();

    }

    public void incrementEventCount(String catId){
        eventCategoryViewModel.incrementEventCount(catId);
    }

    public void decrementEventCount(String catId){
        eventCategoryViewModel.decrementEventCount(catId);
    }

    public boolean catIdValid(String catId){
        EventCategory eventCat = eventCategoryViewModel.getCategoryById(catId);
        return eventCat != null;
    }


}
package com.fit2081.a1;

import static com.fit2081.a1.Utils.generateRandomDigits;
import static com.fit2081.a1.Utils.generateRandomString;
import static com.fit2081.a1.Utils.isInteger;
import static com.fit2081.a1.Utils.countDelimiter;
import static com.fit2081.a1.Utils.isStringAlphanumeric;
import static com.fit2081.a1.Utils.isStringAlphabetic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.fit2081.a1.provider.EventCategoryViewModel;
import com.google.gson.Gson;


public class NewEventCatActivity extends AppCompatActivity {
    TextView tvName;
    TextView tvEventCount;
    Switch switchIsActive;

    TextView tvEventCatID;

    TextView tvEventCatLocation;

    private EventCategoryViewModel eventCategoryViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event_cat);

        tvName = findViewById(R.id.editTextEventcatName);
        tvEventCount = findViewById(R.id.editTextEventcatCount);
        switchIsActive = findViewById(R.id.switchEventCatActive);
        tvEventCatID = findViewById(R.id.editTextEventCatID);
        tvEventCatLocation = findViewById(R.id.editTextLocation);

        ActivityCompat.requestPermissions(this, new String[]{
                android.Manifest.permission.SEND_SMS,
                android.Manifest.permission.RECEIVE_SMS,
                android.Manifest.permission.READ_SMS
        }, 0);

        MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
        registerReceiver(myBroadcastReceiver, new IntentFilter(SMSReceiver.SMS_FILTER), RECEIVER_EXPORTED);



        Toolbar toolbar=(Toolbar)findViewById(R.id.appBarHeader);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Enable the Up button

        // Set navigation icon click listener
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Finish the activity and return to the previous one
            }
        });

        // initialise ViewModel
        eventCategoryViewModel = new ViewModelProvider(this).get(EventCategoryViewModel.class);


    }


    public void onSaveCatButtonClick(View view) {

        String nameString = tvName.getText().toString();
        String countString = tvEventCount.getText().toString();
        boolean isActiveBool = switchIsActive.isChecked();
        String locationString = tvEventCatLocation.getText().toString();
        int countInt = 0;
        if (!countString.isEmpty()){
            countInt = Integer.parseInt(countString);
        }


        if (nameString.isEmpty() || (!isStringAlphanumeric(nameString) && !isStringAlphabetic(nameString))) {
            Toast.makeText(getApplicationContext(), "Invalid Category Name", Toast.LENGTH_SHORT).show();
            return;
        }


        if (countInt < 0){
            Toast.makeText(getApplicationContext(), "Invalid Event Count", Toast.LENGTH_SHORT).show();
            return;
        }


        String catId = generateEvenCatId();
        addCatToDB(catId,nameString,countInt,isActiveBool,locationString);


        tvEventCatID.setText(catId);

        Toast.makeText(this, "Category saved successfully: " + catId, Toast.LENGTH_SHORT).show();
        finish();
    }


    public void addCatToDB(String eventCatID, String name, int eventCount, boolean active, String location) {

        EventCategory eventCat= new EventCategory(name,eventCatID,eventCount,active, location);

        // insert new record to database
        eventCategoryViewModel.insert(eventCat);

    }

    private String generateEvenCatId() {
        return "C" + generateRandomString(2) + "-" + generateRandomDigits(4);

    }

    class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra(SMSReceiver.SMS_MSG_KEY);
            String[] messages = msg.split(":");
            int delimiterCount = countDelimiter(messages[1], ';');
            String[] msgParts = messages[1].split(";");
            if (!messages[0].equals("category")) {
                Toast.makeText(context, "Unknown or invalid command", Toast.LENGTH_SHORT).show();
                clearText();
                return;
            }

            if (delimiterCount == 2) {
                String name = msgParts[0].trim();
                String eventCount = (msgParts.length > 1) ? msgParts[1].trim() : ""; // Check if EventCount exists, otherwise set to empty string
                String isActive = (msgParts.length > 2) ? msgParts[2].trim() : "FALSE";   // Check if IsActive exists, otherwise set to empty string

                if (!eventCount.isEmpty()) {
                    if (!isInteger(eventCount)) {
                        Toast.makeText(context, "Unknown or invalid command", Toast.LENGTH_SHORT).show();
                        clearText();
                        return;
                    }
                }

                if (!isActive.isEmpty()) {
                    if (!isActive.equals("TRUE") && !isActive.equals("FALSE")) {
                        Toast.makeText(context, "Unknown or invalid command", Toast.LENGTH_SHORT).show();
                        clearText();
                        return;
                    }

                }
                tvName.setText(name);
                tvEventCount.setText(eventCount);
                switchIsActive.setChecked(Boolean.parseBoolean(isActive.toLowerCase()));
            } else {
                Toast.makeText(context, "Unknown or invalid command", Toast.LENGTH_SHORT).show();
                clearText();
            }


        }
    }

    private void clearText(){
        tvEventCatID.setText("");
        tvName.setText("");
        tvEventCount.setText("");
        switchIsActive.setChecked(false);
    }


}





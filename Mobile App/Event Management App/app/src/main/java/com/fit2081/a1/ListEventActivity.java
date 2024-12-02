package com.fit2081.a1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

public class ListEventActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_event);

        Toolbar toolbar=(Toolbar)findViewById(R.id.appBarHeader);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction().replace(R.id.frag_list_event,new FragmentListEvent()).commit();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Enable the Up button

        // Set navigation icon click listener
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Finish the activity and return to the previous one
            }
        });
    }


}
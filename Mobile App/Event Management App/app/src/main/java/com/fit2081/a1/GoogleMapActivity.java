package com.fit2081.a1;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.fit2081.a1.databinding.ActivityGoogleMapBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GoogleMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityGoogleMapBinding binding;
    private String countryToFocus;

    SupportMapFragment mapFragment;

    private Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGoogleMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        countryToFocus = getIntent().getExtras().getString("Location", "Malaysia");
        if (countryToFocus.isEmpty()){
            countryToFocus = "Malaysia";
        }

        geocoder = new Geocoder(this, Locale.getDefault());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        findCountryMoveCamera();

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {
                //save current location
                String msg;
                String selectedCountry = "";


                List<Address> addresses = new ArrayList<>();
                try {
                    //The results of getFromLocation are a best guess and are not guaranteed to be meaningful or correct.
                    // It may be useful to call this method from a thread separate from your primary UI thread.
                    addresses = geocoder.getFromLocation(point.latitude, point.longitude, 1); //last param means only return the first address object
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                if (addresses.size() == 0) {
                    msg = "No Country at this location!! Sorry";
                }
                else {
                    android.location.Address address = addresses.get(0);
                    selectedCountry = address.getCountryName();
                    msg="The selected country is "+ selectedCountry;
                }
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findCountryMoveCamera() {


        // getFromLocationName method works for API 33 and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            /**
             * countryToFocus: String value, any string we want to search
             * maxResults: how many results to return if search was successful
             * successCallback method: if results are found, this method will be executed
             *                          runs in a background thread
             */

            geocoder.getFromLocationName(countryToFocus, 1, addresses -> {
                Log.d(TAG, "ADDRESSES " + addresses + addresses.isEmpty());
                // if there are results, this condition would return true
                if (!addresses.isEmpty()) {
                    // run on UI thread as the user interface will update once set map location
                    runOnUiThread(() -> {
                        // define new LatLng variable using the first address from list of addresses
                        LatLng newAddressLocation = new LatLng(
                                addresses.get(0).getLatitude(),
                                addresses.get(0).getLongitude()
                        );

                        // repositions the camera according to newAddressLocation
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(newAddressLocation));

                        // just for reference add a new Marker
                        mMap.addMarker(
                                new MarkerOptions()
                                        .position(newAddressLocation)
                                        .title(countryToFocus)
                        );

                        // set zoom level to 8.5f or any number between range of 2.0 to 21.0
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
                    });
                }else{
                    countryToFocus = "Malaysia";
                    findCountryMoveCamera();
                    runOnUiThread(() -> Toast.makeText(this, "Category address not found. Default to Malaysia.", Toast.LENGTH_SHORT).show());
                }

            });
        }
    }


}
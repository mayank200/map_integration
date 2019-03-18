package com.example.sharmas.map_integration;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

@SuppressWarnings("ALL")
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,LocationListener {

    private GoogleMap mMap;
    MenuItem i1,i2,i3,i4,i5;
    EditText txt1,txt2;
    ArrayList markerPoints= new ArrayList();
    protected LocationManager locationManager;
    @SuppressWarnings("deprecation")
    protected LocationListener locationListener;
    double longitude,latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
         txt1=findViewById(R.id.tx1);
         txt2=findViewById(R.id.txt2);
         i1=findViewById(R.id.s);
        i2=findViewById(R.id.r);
        i3=findViewById(R.id.n);
        i4=findViewById(R.id.d);
        i5=findViewById(R.id.a);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        }
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu, menu);

        return true;

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

        mMap = googleMap;
      /*  LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {



            }
    */
    }


            @Override
            public void onLocationChanged(Location location) {
        mMap.clear();
                LatLng now = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(now).title("Marker is on you"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(now));
                mMap.stopAnimation();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }

            public void show1(View view) {

                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

            }

    public void show2(View view) {
 float v1=Float.parseFloat(txt1.getText().toString());
float v2=Float.parseFloat(txt2.getText().toString());

        if(v1< -85.05112878 || v1> 85.05112878)
        {
            if (v2<-180 || v2>180)
            {
                Toast.makeText(getApplicationContext(),"Values typed exceeds range",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Latitude typed exceeds range",Toast.LENGTH_LONG).show();
            }
        }
        else if(v2 < -180 || v2 > 180) {
            if (v1< -85.05112878 || v1> 85.05112878) {
                Toast.makeText(this, "Values typed exceeds range", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Longitude typed exceeds range", Toast.LENGTH_LONG).show();
            }
        }
        else {
            mMap.clear();
            LatLng neo = new LatLng(v1, v2);

            mMap.addMarker(new MarkerOptions().position(neo).title("Marker is here"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(neo));
        }
        }
    public boolean onOptionsItemSelected(MenuItem item) {

//respond to menu item selection
        switch (item.getItemId()) {

            case R.id.s:

                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.silver));

                return true;

            case R.id.n:

                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.night));

                return true;

            case R.id.d:

                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.dark));

                return true;

            case R.id.a:

                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.aubergine));

                return true;
            default:

                return super.onOptionsItemSelected(item);

        }

    }


}

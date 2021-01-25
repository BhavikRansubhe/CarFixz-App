package com.br14x.carfixz;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationListener locationListener;
    private LocationManager locationManager;
    private LatLng latLng;
    private final long MIN_TIME = 1000;  //1 SECOND

    private final long MIN_DIST = 2;     //2 meters

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PackageManager.PERMISSION_GRANTED);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PackageManager.PERMISSION_GRANTED);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng a1grage = new LatLng(18.53, 73.87);
        mMap.addMarker(new MarkerOptions().position(a1grage).title("A1 Garage"));


        LatLng omgarage = new LatLng(18.526, 73.844);
        mMap.addMarker(new MarkerOptions().position(omgarage).title("OM Service Centre"));

        LatLng saigarage = new LatLng(18.489, 73.865);
        mMap.addMarker(new MarkerOptions().position(saigarage).title("Sai Service Centre"));


        locationListener = new LocationListener() {

            @Override

            public void onLocationChanged(Location location) {

                double latitude;

                double longitude;

                try {

                    latitude = location.getLatitude();

                    longitude = location.getLongitude();

                    latLng = new LatLng(latitude,longitude);

                    mMap.addMarker(new MarkerOptions().position(latLng).title("My Current Location"));

                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

                    mMap.setMyLocationEnabled(true);

                } catch (SecurityException e) {

                    e.printStackTrace();

                }

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

        };



        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);





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
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DIST, locationListener);
    }

}

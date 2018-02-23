package com.example.max.busapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.FirebaseDatabase;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnCameraMoveListener {

    private GoogleMap mMap;
    private GroundOverlay car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        FirebaseDatabase database = FirebaseHelper.getInstance().db;
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
        // Add a marker in Sydney and move the camera
        LatLng baltimore = new LatLng(39.281516, -76.597448);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(baltimore,17));
        BitmapDescriptor image = BitmapDescriptorFactory.fromResource(R.mipmap.redcartopviewhi);
        car  = mMap.addGroundOverlay(new GroundOverlayOptions().image(image).position(baltimore,50f, 30f));
        mMap.setOnCameraMoveListener(this);
    }

    @Override
    public void onCameraMove()
    {
        if(car != null && car.isVisible() && mMap != null)
        {
            car.setPosition(mMap.getCameraPosition().target);
        }
    }

    /*Called when Go Button clicked*/
    public void startRoute(View view)
    {
        if(!FirebaseHelper.latLongList.isEmpty())
        {
            LatLong loc1 = FirebaseHelper.latLongList.get(0);
            mMap.moveCamera(CameraUpdateFactory.scrollBy(400,0));
        }
    }

}

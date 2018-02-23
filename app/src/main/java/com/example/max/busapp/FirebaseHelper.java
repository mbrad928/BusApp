package com.example.max.busapp;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by mbrad on 2/22/2018.
 */

public class FirebaseHelper {
    private static FirebaseHelper instance = null;
    public FirebaseDatabase db;

    public static ArrayList<LatLong> latLongList = new ArrayList<>();

    protected FirebaseHelper() {
        db = FirebaseDatabase.getInstance();
    }

    public static FirebaseHelper getInstance() {
        if (instance == null) {
            instance = new FirebaseHelper();
            getData();

        }
        return instance;
    }

    public static void getData() {
        String returnS = "";

        DatabaseReference myRef = getInstance().db.getReference("Locations");


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        LatLong loc = new LatLong(snapshot.child("0").getValue(String.class), snapshot.child("1").getValue(String.class));
                        latLongList.add(loc);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }
}



package com.example.max.busapp;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by mbrad on 2/22/2018.
 */

public class FirebaseHelper {
    private static FirebaseHelper instance = null;
    public FirebaseDatabase db;
    protected FirebaseHelper()
    {
        db = FirebaseDatabase.getInstance();
    }

    public static FirebaseHelper getInstance(){
        if(instance == null)
        {
            instance = new FirebaseHelper();
        }
        return instance;
    }

    public static String getStringReference(String reference)
    {
        String returnS = "";
        if(reference != null && !reference.isEmpty()) {
            DatabaseReference myRef = getInstance().db.getReference(reference);


            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    String value = dataSnapshot.getValue(String.class);
                    returnS = value;
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                }
            });
        }
    }
}

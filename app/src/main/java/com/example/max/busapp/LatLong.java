package com.example.max.busapp;

/**
 * Created by mbrad on 2/22/2018.
 */

public class LatLong {
    private String latValue;
    private String longValue;

    public LatLong(String latValue, String longValue)
    {
        this.latValue = latValue;
        this.longValue = longValue;
    }

    public String getLatValue()
    {
        return this.latValue;
    }

    public String getLongValue()
    {
        return this.longValue;
    }
}

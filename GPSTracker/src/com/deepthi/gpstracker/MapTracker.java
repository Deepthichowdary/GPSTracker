package com.deepthi.gpstracker;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MapTracker extends Activity {
	
	LocationListener mlocListener;
	
	
	//variables for coordinates
	double latitude;
	double longitude;
	
	//This holds list view data
	ArrayList<String> data = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	ListView lv;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lv = (ListView) findViewById(R.id.listView);
		
		
		LocationManager mlocManager =  (LocationManager)getSystemService(Context.LOCATION_SERVICE); 


		boolean check_if_GPS_is_enabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		
		Location l = null;
		if(check_if_GPS_is_enabled)
			l= mlocManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		
		
		if(l!=null)
		{
			longitude = l.getLongitude();
			latitude = l.getLatitude();
		}
		
		//This is object for location changed listener
		//mlocListener = new MyLocationListener(); 
		
		//currently set to 0 distance and 0 milli seconds interval, change these values to play around
		mlocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 5000,2, mlocListener); 

		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, data);

        //sets adapter to listview
		lv.setAdapter(adapter);


	}

	

}

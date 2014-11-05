package com.deepthi.gpstracker;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
public class MainActivity extends Activity {
	LocationListener mlocListener;
	
	
	//variables for coordinates
	double latitude;
	double longitude;
	
	ArrayList<String> data = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	ListView lv;
	LocationManager mlocManager;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView) findViewById(R.id.listView);
		
		
		mlocManager =  (LocationManager)getSystemService(Context.LOCATION_SERVICE); 


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
		mlocListener = new MyLocationListener(); 
		
		//currently set to 0 distance and 0 milli seconds interval, change these values to play around
		 

		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, data);

        //sets adapter to listview
		lv.setAdapter(adapter);
		

	}

	
	public void startMethod(View v) {
//		if(mlocManager==null)
//			mlocManager =  (LocationManager)getSystemService(Context.LOCATION_SERVICE);
//		
		if(mlocListener==null)
			mlocListener = new MyLocationListener();
		mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,10000,0, mlocListener);
//		Intent gpsIntent = new Intent(MainActivity.this,
//				MapTracker.class);
//		startActivity(gpsIntent);
		//MyLocationListener my = new MyLocationListener();
	}
	
	public void stopMethod(View v) {
		//TODO: not sure what to implement for this button :)
		MyLocationListener my = new MyLocationListener();
		my.deactivate();
		//removeUpdates(my);
		
	}
	
	public class MyLocationListener implements LocationListener 

	{ 

		
		
		//This is most imp method in this app!
		@Override 
		public void onLocationChanged(Location loc) 
		{ 

			String listText = "Latitude: " + loc.getLatitude() + " Longitude:" + loc.getLongitude(); 
			data.add(listText);
			adapter.notifyDataSetChanged();

		} 
	
        public void deactivate() {
            // Remove location updates from Location Manager
        	
        	mlocManager.removeUpdates(mlocListener);
        	mlocListener = null;
        	
        }




		//Explore below methods to know more about customizing app. for ex, onProviderDisabled will be triggered when GPS turns off
		// you can add any alerts to let users know about this change... and so on...
		@Override 
		public void onProviderDisabled(String provider) 

		{ 
			//TODO: Add code, if necessary, to override this method
		}
		
		@Override 
		public void onProviderEnabled(String provider) 

		{ 
			//TODO: Add code, if necessary, to override this method
		} 

		@Override 
		public void onStatusChanged(String provider, int status, Bundle extras) 

		{ 
			//TODO: Add code, if necessary, to override this method
		} 

	}

}
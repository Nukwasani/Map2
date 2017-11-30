package com.example.thandiwe.map2;

import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, LocationListener {


    TextView tv1, tv2;

    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    Location mLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildGoogleApiClient();




    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

       /* if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

         // getting your current location
        if(mLocation != null)
        {
            tv1 = (TextView)findViewById(R.id.textView);
            tv1.setText(String.valueOf(mLocation.getLatitude()));

            tv2 = (TextView)findViewById(R.id.textView2);
            tv2.setText(String.valueOf(mLocation.getLongitude()));
        }*/

        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000);



        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
      LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest,this);



    }

    @Override
    public void onConnectionSuspended(int i) {


    }

    public void buildGoogleApiClient()
    {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                         .addConnectionCallbacks(this)
                         .addOnConnectionFailedListener(this)
                         .addApi(LocationServices.API).build();
    }

        @Override
        protected void onStart() {


            super.onStart();
            mGoogleApiClient.connect();
}

        @Override
        protected void onStop() {


            super.onStop();
            mGoogleApiClient.disconnect();
        }

    @Override
    public void onLocationChanged(Location location) {

     mLocation = location;
        if(mLocation != null)
        {
            tv1 = (TextView)findViewById(R.id.textView);
            tv1.setText(String.valueOf(mLocation.getLatitude()));

            tv2 = (TextView)findViewById(R.id.textView2);
            tv2.setText(String.valueOf(mLocation.getLongitude()));
        }

    }
}

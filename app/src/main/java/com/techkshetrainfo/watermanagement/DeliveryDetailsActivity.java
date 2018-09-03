package com.techkshetrainfo.watermanagement;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class DeliveryDetailsActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    MapView mMapView;
    private GoogleMap googleMap;
    private GoogleApiClient mGoogleApiClient;
    Location mLastLocation;

    double lat;
    double lng;
    private ProgressBar progress;
    private static final int MY_REQUEST_CODE = 103;

    EditText et_address, et_quantity,et_area;
    CheckBox cb_mon, cb_tue, cb_wed, cb_thur, cb_fri, cb_sat, cb_sun, cb_all;
    Button save_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_details);
        Toolbar toolbar=findViewById(R.id.deltool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Delivery details");

        et_area=findViewById(R.id.et_area);
       // et_address = (EditText) findViewById(R.id.et_address);
        progress = (ProgressBar) findViewById(R.id.progress);
        et_quantity = (EditText) findViewById(R.id.et_quantity);

        cb_mon = (CheckBox) findViewById(R.id.cb_mon);
        cb_tue = (CheckBox) findViewById(R.id.cb_tue);
        cb_wed = (CheckBox) findViewById(R.id.cb_wed);
        cb_thur = (CheckBox) findViewById(R.id.cb_thur);

        cb_fri = (CheckBox) findViewById(R.id.cb_fri);
        cb_sat = (CheckBox) findViewById(R.id.cb_sat);
        cb_sun = (CheckBox) findViewById(R.id.cb_sun);
        cb_all = (CheckBox) findViewById(R.id.cb_all);

        cb_mon.setTag("0");
        cb_tue.setTag("0");
        cb_wed.setTag("0");
        cb_thur.setTag("0");

        cb_fri.setTag("0");
        cb_sat.setTag("0");
        cb_sun.setTag("0");
        cb_all.setChecked(false);

        mMapView = (MapView) findViewById(R.id.mapView_f);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        save_btn = (Button) findViewById(R.id.save_btn);

        if ((int) Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(
                            new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.ACCESS_FINE_LOCATION},
                            101);
                }
                //return;

            } else {
                load_map();

            }
        } else {
            load_map();

        }

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (CommomFunctions.isnetworkavailable(DeliveryDetailsActivity.this)) {

                    validate_complaint();


                } else {

                    CommomFunctions.show_dialog(DeliveryDetailsActivity.this, getString(R.string.internet_error_title), getString(R.string.internet_error));
                }


            }

        });

    }

    private void load_map() {

        // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(this.getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                buildGoogleApiClient();
                mGoogleApiClient.connect();

                // For showing a move to my location button
                /*if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }*/
                //googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map

            }
        });

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMapView != null) {

            mMapView.onDestroy();
        }

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    @SuppressWarnings({"MissingPermission"})
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 101: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    load_map();


                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    Toast.makeText(this, "Current Location not available Please Turn On Location Service ", Toast.LENGTH_SHORT).show();
                    //CommonFunctions.showAlertDialog(Maps_activity.this,"Current Location","Current Location not available Please Turn On Location Service ");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }
            case MY_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    clickpic();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                // return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    private void validate_complaint() {

        if (et_address.getText().length() == 0) {
            et_address.setError(getString(R.string.empty_location));
        }  else if (et_quantity.getText().length() == 0) {
            et_quantity.setError(getString(R.string.empty_field));
        } else if (cb_all.isChecked() || cb_mon.isChecked() || cb_tue.isChecked() || cb_wed.isChecked() || cb_thur.isChecked() || cb_fri.isChecked() || cb_sat.isChecked() || cb_sun.isChecked()) {
            cb_all.setError(getString(R.string.empty_field));
        } else {
            if (CommonFunctions.isnetworkavailable(DeliveryDetailsActivity.this)) {
                progress.setVisibility(View.VISIBLE);
                register_user();
            } else {
                Toast.makeText(this, R.string.internet_error, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void register_user() {
        Toast.makeText(DeliveryDetailsActivity.this, "User Registered Successfully!", Toast.LENGTH_LONG).show();
    }

    public void onCheckboxClicked(View view) {

        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.cb_all:
                if (checked) {
//                    Boolean checkBoxState = simpleCheckBox.isChecked();
                    cb_mon.setChecked(true);
                    cb_tue.setChecked(true);
                    cb_wed.setChecked(true);
                    cb_thur.setChecked(true);
                    cb_fri.setChecked(true);
                    cb_sat.setChecked(true);
                    cb_sun.setChecked(true);
                    cb_mon.setTag("1");
                    cb_tue.setTag("1");
                    cb_wed.setTag("1");
                    cb_thur.setTag("1");
                    cb_fri.setTag("1");
                    cb_sat.setTag("1");
                    cb_sun.setTag("1");


                } else {
                    cb_mon.setChecked(false);
                    cb_tue.setChecked(false);
                    cb_wed.setChecked(false);
                    cb_thur.setChecked(false);
                    cb_fri.setChecked(false);
                    cb_sat.setChecked(false);
                    cb_sun.setChecked(false);
                    cb_mon.setTag("0");
                    cb_tue.setTag("0");
                    cb_wed.setTag("0");
                    cb_thur.setTag("0");
                    cb_fri.setTag("0");
                    cb_sat.setTag("0");
                    cb_sun.setTag("0");


                }
                break;
            case R.id.cb_mon:
                if (checked) {

                    cb_mon.setTag("1");

                } else {

                    cb_mon.setTag("0");
                }
                break;
            case R.id.cb_tue:
                if (checked) {

                    cb_tue.setTag("1");

                } else {

                    cb_tue.setTag("0");
                }
                break;
            case R.id.cb_wed:
                if (checked) {

                    cb_wed.setTag("1");

                } else {

                    cb_wed.setTag("0");
                }
                break;
            case R.id.cb_thur:
                if (checked) {
//                    checkedThur = "1";
                    //cb_thur.setChecked(true);
                    cb_thur.setTag("1");


                } else {

                    cb_thur.setTag("0");
                }
                break;
            case R.id.cb_fri:
                if (checked) {

                    cb_fri.setTag("1");

                } else {

                    cb_fri.setTag("0");
                }
                break;
            case R.id.cb_sat:
                if (checked) {
                    cb_sat.setTag("1");

                } else {

                    cb_sat.setTag("0");
                }
                break;
            case R.id.cb_sun:
                if (checked) {

                    cb_sun.setTag("1");
                } else {

                    cb_sun.setTag("0");
                }
                break;
        }
    }

    @Override
    @SuppressWarnings({"MissingPermission"})
    public void onConnected(@Nullable Bundle bundle) {

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            //lblLocation.setText(String.valueOf(mLastLocation.getLatitude())+String.valueOf(mLastLocation.getLongitude()));
            lat = mLastLocation.getLatitude();
            lng = mLastLocation.getLongitude();

        } else {
            //Toast.makeText(this,"no servce",Toast.LENGTH_LONG).show();
            lat = 17.312040;
            lng = 76.800076;

        }
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this);

        try {
            addresses = geocoder.getFromLocation(lat,
                    lng, 1);
            String address = addresses.get(0).getAddressLine(0);
            et_area.setText(address);
            String city = addresses.get(0).getAddressLine(1);
            String state=addresses.get(0).getAddressLine(2);
            String country = addresses.get(0).getAddressLine(3);
            String c_loc = address + " - " + city + " - " + country;
            //Toast.makeText(getActivity(), country, Toast.LENGTH_LONG).show();
          //  et_address.setText(c_loc);
            //System.out.println(address+" - "+city+" - "+country);
        } catch (IOException e) {
            e.printStackTrace();
        }


        LatLng keonics = new LatLng(lat, lng);
        googleMap.addMarker(new MarkerOptions().position(keonics).title("Marker Title").snippet("Marker Description"));



        // For zooming automatically to the location of the marker
        CameraPosition cameraPosition = new CameraPosition.Builder().target(keonics).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

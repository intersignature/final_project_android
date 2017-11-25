package kmitl.final_project.sirichai.eventontheday.view;

import android.app.Dialog;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import kmitl.final_project.sirichai.eventontheday.R;
import kmitl.final_project.sirichai.eventontheday.controller.DatabaseAdapter;
import kmitl.final_project.sirichai.eventontheday.model.EventInfo;

public class ViewEventActivity extends AppCompatActivity implements OnMapReadyCallback {
    private TextView viewTitle;
    private TextView viewLocation;
    private TextView viewStartDate;
    private TextView viewEndDate;
    private TextView viewAlertDate;
    private TextView viewDetail;
    private DatabaseAdapter databaseAdapter;
    private GoogleMap mgoogleMap;
    private Marker marker;
    private EventInfo data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);

        initInstances();
        setValues();
    }

    private void setValues() {
        data = databaseAdapter.getEachDataEvent(getIntent().getStringExtra("id"));
        viewTitle.setText(data.getTitle());
        viewLocation.setText("AT : " + data.getLocation().split(" : ")[0]);
        viewStartDate.setText("Start date : " + data.getStart_date() + " AT : " + data.getStart_time());
        viewEndDate.setText("End date : " + data.getEnd_date() + " AT : " + data.getEnd_time());
        viewAlertDate.setText("Alert date : " + data.getAlert_date() + " AT : " + data.getAlert_time());
        viewDetail.setText("Detail : " + data.getDetail());
    }

    private void initInstances() {
        databaseAdapter = new DatabaseAdapter(getApplicationContext());
        viewTitle = (TextView) findViewById(R.id.viewTitle);
        viewLocation = (TextView) findViewById(R.id.viewLocation);
        viewStartDate = (TextView) findViewById(R.id.viewStartDate);
        viewEndDate = (TextView) findViewById(R.id.viewEndDate);
        viewAlertDate = (TextView) findViewById(R.id.viewAlertDate);
        viewDetail = (TextView) findViewById(R.id.viewDetail);
        if (googleServicesAvailable()) {
            initMap();
        }
    }

    private void setMarker(double lat, double lng) {
        if (marker != null) {
            marker.remove();
        }

        MarkerOptions options = new MarkerOptions()
                .draggable(false)
                .position(new LatLng(lat, lng))
                .snippet("I select here");
        marker = mgoogleMap.addMarker(options);
    }

    private void goToLocationZoom(double lat, double lng, float zoom) {
        LatLng l1 = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(l1, zoom);
        mgoogleMap.moveCamera(update);
    }

    private void goToLocation(double lat, double lng) {
        LatLng l1 = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLng(l1);
        mgoogleMap.moveCamera(update);
    }

    private void initMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }

    private boolean googleServicesAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(this, "Can't connect to play service", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mgoogleMap = googleMap;
        mgoogleMap.getUiSettings().setAllGesturesEnabled(true);

        mgoogleMap.getUiSettings().setScrollGesturesEnabled(true);
        mgoogleMap.getUiSettings().setZoomControlsEnabled(true);
        mgoogleMap.getUiSettings().setMapToolbarEnabled(true);
        mgoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        double lat;
        double lng;
        try {
            lat = Double.parseDouble(data.getLocation().split(" : ")[2]);
            lng = Double.parseDouble(data.getLocation().split(" : ")[3]);
            goToLocationZoom(lat, lng, 17.0f);
            setMarker(lat, lng);
        } catch (Exception e) {
            Geocoder gc = new Geocoder(this);
            try {
                List<Address> addressList = gc.getFromLocationName(data.getLocation(), 1);
                if (addressList.size() > 0) {
                    Address address = addressList.get(0);
                    lat = address.getLatitude();
                    lng = address.getLongitude();
                    goToLocationZoom(lat, lng, 17.0f);
                    setMarker(lat, lng);
                } else {
                    Toast.makeText(getApplicationContext(), "Can't find place in google map", Toast.LENGTH_SHORT);
                    return;
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}

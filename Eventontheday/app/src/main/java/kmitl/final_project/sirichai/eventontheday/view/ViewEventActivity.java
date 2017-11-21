package kmitl.final_project.sirichai.eventontheday.view;

import android.app.Dialog;
import android.app.Fragment;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import kmitl.final_project.sirichai.eventontheday.model.DatabaseAdapter;

public class ViewEventActivity extends AppCompatActivity implements OnMapReadyCallback {
    private TextView viewTitle;
    private TextView viewLocation;
    private TextView viewStartDate;
    private TextView viewEndDate;
    private TextView viewStartTime;
    private TextView viewEndTime;
    private TextView viewAlertDate;
    private TextView viewDetail;
    private DatabaseAdapter databaseAdapter;
    GoogleMap mgoogleMap;
    Marker marker;
    List<String> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);

        viewTitle = (TextView) findViewById(R.id.viewTitle);
        viewLocation = (TextView) findViewById(R.id.viewLocation);
        viewStartDate = (TextView) findViewById(R.id.viewStartDate);
        viewEndDate = (TextView) findViewById(R.id.viewEndDate);
        viewAlertDate = (TextView) findViewById(R.id.viewAlertDate);
        viewDetail = (TextView) findViewById(R.id.viewDetail);
        databaseAdapter = new DatabaseAdapter(getApplicationContext());
        data = databaseAdapter.getEachDataEvent(getIntent().getStringExtra("id"));
        Log.i("viewecent", data.toString());
        viewTitle.setText(data.get(0));
        viewLocation.setText("AT : "+data.get(1).split(" : ")[0]);
        viewStartDate.setText("Start date : "+data.get(2) + " AT : " + data.get(4));
        viewEndDate.setText("End date : "+data.get(3) + " AT : " + data.get(5));
        viewAlertDate.setText("Alert date : " + data.get(6) + " AT : " + data.get(7));
        viewDetail.setText("Detail : "+data.get(8));
        if (googleServicesAvailable()) {
            Log.i("play service", "Perfect!!");
            initMap();
        }
    }

    private void setMarker(double lat, double lng) {
        if(marker!=null){
            marker.remove();
        }

        MarkerOptions options = new MarkerOptions()
                .draggable(true)
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

    public boolean googleServicesAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        } else {
            Log.i("play service", "cant connect to play service");
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mgoogleMap = googleMap;
        mgoogleMap.getUiSettings().setScrollGesturesEnabled(false);
        double lat;
        double lng;
        try {
            lat = Double.parseDouble(data.get(1).split(" : ")[2]);
            lng = Double.parseDouble(data.get(1).split(" : ")[3]);
            goToLocationZoom(lat, lng, 17.0f);
            setMarker(lat, lng);
        }catch (Exception e){
            Geocoder gc = new Geocoder(this);
            try {
                List<Address> addressList = gc.getFromLocationName(data.get(1), 1);
                if(addressList.size()>0){
                    Address address = addressList.get(0);
                    lat = address.getLatitude();
                    lng = address.getLongitude();
                    goToLocationZoom(lat, lng, 17.0f);
                    setMarker(lat, lng);
                }
                else {
                    // cannot find location in google map
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }
}

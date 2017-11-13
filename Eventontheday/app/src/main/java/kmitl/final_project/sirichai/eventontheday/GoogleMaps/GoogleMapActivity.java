package kmitl.final_project.sirichai.eventontheday.GoogleMaps;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import kmitl.final_project.sirichai.eventontheday.R;

public class GoogleMapActivity extends AppCompatActivity implements OnMapReadyCallback , GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    GoogleMap mgoogleMap;
    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (googleServicesAvailable()) {
            Log.i("play service", "Perfect!!");
            setContentView(R.layout.activity_google_map);
            initMap();
        } else {
            // no google map layout
        }
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

        if(mgoogleMap != null){

            mgoogleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                @Override
                public void onMarkerDragStart(Marker marker) {

                }

                @Override
                public void onMarkerDrag(Marker marker) {

                }

                @Override
                public void onMarkerDragEnd(Marker marker) {
                    Geocoder gc = new Geocoder(GoogleMapActivity.this);
                    LatLng ll = marker.getPosition();
                    double lat = ll.latitude;
                    double lng = ll.longitude;
                    List<Address> list = null;
                    try {
                        list = gc.getFromLocation(lat, lng, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Address address = list.get(0);
                    marker.setTitle(address.getLocality());
                    marker.showInfoWindow();

                }
            });

            mgoogleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    View view = getLayoutInflater().inflate(R.layout.info_map_window, null);
                    TextView map_window_locality = view.findViewById(R.id.map_window_locality);
                    TextView map_window_lat = view.findViewById(R.id.map_window_lat);
                    TextView map_window_lng = view.findViewById(R.id.map_window_lng);
                    TextView map_window_snippet = view.findViewById(R.id.map_window_snippet);

                    LatLng ll = marker.getPosition();
                    map_window_locality.setText(marker.getTitle());
                    map_window_lat.setText("Latitude: " + ll.latitude);
                    map_window_lng.setText("longitude: " + ll.longitude);
                    map_window_snippet.setText(marker.getSnippet());
                    return view;
                }
            });
        }

        goToLocationZoom(13.730906, 100.781214, 17.0f);

//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return;
//            }
//        }
//        mgoogleMap.setMyLocationEnabled(true);



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

    Marker marker;
    public void onsubmitSetLocationMaps(View view) throws IOException {
        EditText setLocationMaps = findViewById(R.id.setLocationMaps);
        String location = setLocationMaps.getText().toString();

        Geocoder gc = new Geocoder(this);
        List<Address> list = gc.getFromLocationName(location,1);
        if (list.size() > 0){
            Address address = list.get(0);
            String locality = address.getLocality();

//        Log.i("locality", locality.toString());

            double lat = address.getLatitude();
            double lng = address.getLongitude();
            goToLocationZoom(lat, lng, 17.0f);

            setMarker(locality, lat, lng);
        }
        else {
            Log.i("mapError", "cannot find map");
        }

    }

    private void setMarker(String locality, double lat, double lng) {
        if(marker!=null){
            marker.remove();
        }

        MarkerOptions options = new MarkerOptions()
                .title(locality)
                .draggable(true)
                .position(new LatLng(lat, lng))
                .snippet("I select here");
        marker = mgoogleMap.addMarker(options);
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

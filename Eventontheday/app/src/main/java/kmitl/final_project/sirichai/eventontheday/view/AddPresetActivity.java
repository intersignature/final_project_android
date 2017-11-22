package kmitl.final_project.sirichai.eventontheday.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import kmitl.final_project.sirichai.eventontheday.R;
import kmitl.final_project.sirichai.eventontheday.controller.DatabaseAdapter;

public class AddPresetActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText setTitle;
    private EditText setLocation;
    private EditText setDetail;
    private DatabaseAdapter databaseAdapter;
    private Button submitAddEvent;
    private final int PLACE_PICKER_REQUEST = 1;
    private Button PlacePickerBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_preset);

        initInstances();
    }

    private void initInstances() {
        setTitle = (EditText) findViewById(R.id.setTitlePreset);
        setLocation = (EditText) findViewById(R.id.setLocationPreset);
        setDetail = (EditText) findViewById(R.id.setDetailPreset);
        submitAddEvent = (Button) findViewById(R.id.submitAddPreset);
        PlacePickerBTN = (Button) findViewById(R.id.PlacePickerBTNPreset);
        submitAddEvent.setOnClickListener(this);
        PlacePickerBTN.setOnClickListener(this);
        databaseAdapter = new DatabaseAdapter(getApplicationContext());
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                setLocation.setText(place.getName() + " : " + place.getAddress());
            }
        }
    }

    private void startPlacePicker() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        Intent intent;
        try {
            intent = builder.build(this);
            startActivityForResult(intent, PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    private void savePreset() {
        String title = setTitle.getText().toString();
        String location = setLocation.getText().toString();
        String detail = setDetail.getText().toString();
        if (title.equals("") || location.equals("") || detail.equals("")) {
            Toast.makeText(getApplicationContext(), "Enter empty field", Toast.LENGTH_SHORT).show();
        } else {
            String result = databaseAdapter.insertDataPreset(title, location, detail);
            if (!result.equals("success")) {
                Toast.makeText(getApplicationContext(), "Insertion unsucessfull!" + result, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Insertion success!!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.submitAddPreset) {
            savePreset();
        } else if (v.getId() == R.id.PlacePickerBTNPreset) {
            startPlacePicker();
        }
    }
}

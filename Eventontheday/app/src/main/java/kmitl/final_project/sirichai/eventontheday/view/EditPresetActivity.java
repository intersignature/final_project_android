package kmitl.final_project.sirichai.eventontheday.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.List;

import kmitl.final_project.sirichai.eventontheday.R;
import kmitl.final_project.sirichai.eventontheday.controller.DatabaseAdapter;
import kmitl.final_project.sirichai.eventontheday.model.ListPreset;

public class EditPresetActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText setTitle;
    private EditText setLocation;
    private EditText setDetail;
    private DatabaseAdapter databaseAdapter;
    private final int PLACE_PICKER_REQUEST = 1;
    private String oldId;
    private Button submitEditPreset;
    private Button placePickerBTNNewPreset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_preset);

        initInstances();
        setValues();
    }

    private void setValues() {
        oldId = getIntent().getStringExtra("oldIdPreset");
        ListPreset datas = databaseAdapter.getEachDataPreset(oldId);
        setTitle.setText(datas.getPresetTitle());
        setLocation.setText(datas.getPresetLocation());
        setDetail.setText(datas.getPresetDetail());
    }

    private void initInstances() {
        setTitle = (EditText) findViewById(R.id.setNewTitlePreset);
        setLocation = (EditText) findViewById(R.id.setNewLocationPreset);
        setDetail = (EditText) findViewById(R.id.setNewDetailPreset);
        databaseAdapter = new DatabaseAdapter(getApplicationContext());
        submitEditPreset = (Button) findViewById(R.id.submitEditPreset);
        placePickerBTNNewPreset = (Button) findViewById(R.id.PlacePickerBTNNewPreset);
        submitEditPreset.setOnClickListener(this);
        placePickerBTNNewPreset.setOnClickListener(this);
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                setLocation.setText(place.getName() + " : " + place.getAddress());
            }
        }
    }

    private void editPreset() {
        String title = setTitle.getText().toString();
        String location = setLocation.getText().toString();
        String detail = setDetail.getText().toString();
        if (title.equals("") || location.equals("") || detail.equals("")) {
            Toast.makeText(getApplicationContext(), "Enter empty field", Toast.LENGTH_SHORT).show();
        } else {
            String result = databaseAdapter.updateDataPreset(title, location, detail, oldId);
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
        if (v.getId() == R.id.submitEditPreset) {
            editPreset();
        } else if (v.getId() == R.id.PlacePickerBTNNewPreset) {
            startPlacePicker();
        }
    }
}

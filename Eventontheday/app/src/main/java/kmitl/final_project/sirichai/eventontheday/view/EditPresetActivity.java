package kmitl.final_project.sirichai.eventontheday.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.List;

import kmitl.final_project.sirichai.eventontheday.R;
import kmitl.final_project.sirichai.eventontheday.model.DatabaseAdapter;

public class EditPresetActivity extends AppCompatActivity {

    private EditText setTitle;
    private EditText setLocation;
    private EditText setDetail;
    private DatabaseAdapter databaseAdapter;
    private final int PLACE_PICKER_REQUEST = 1;
    private String oldId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_preset);
        setTitle = (EditText) findViewById(R.id.setNewTitlePreset);
        setLocation = (EditText) findViewById(R.id.setNewLocationPreset);
        setDetail = (EditText) findViewById(R.id.setNewDetailPreset);
        databaseAdapter = new DatabaseAdapter(getApplicationContext());
        oldId = getIntent().getStringExtra("oldIdPreset");
        List<String> datas = databaseAdapter.getEachDataPreset(oldId);
        setTitle.setText(datas.get(0));
        setLocation.setText(datas.get(1));
        setDetail.setText(datas.get(2));

    }

    public void onPlacePicker(View view) {
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode==PLACE_PICKER_REQUEST){
            if (resultCode==RESULT_OK){
                Place place = PlacePicker.getPlace(data, this);
                setLocation.setText(place.getName() + " : " + place.getAddress());
            }
        }
    }

    public void onSubmitAddEvent(View view) {
        String title = setTitle.getText().toString();
        String location = setLocation.getText().toString();
        String detail = setDetail.getText().toString();
        if (title.equals("") || location.equals("") || detail.equals("")){
            Toast.makeText(getApplicationContext(),"Enter empty field", Toast.LENGTH_SHORT).show();
        }
        else {
            String result = databaseAdapter.updateDataPreset(title,location,detail,oldId);
            if(!result.equals("success")){
                Toast.makeText(getApplicationContext(),"Insertion unsucessfull!"+result,Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"Insertion success!!",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}

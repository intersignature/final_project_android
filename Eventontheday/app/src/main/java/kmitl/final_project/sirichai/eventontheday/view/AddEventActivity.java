package kmitl.final_project.sirichai.eventontheday.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import kmitl.final_project.sirichai.eventontheday.R;
import kmitl.final_project.sirichai.eventontheday.controller.DatabaseAdapter;

public class AddEventActivity extends AppCompatActivity {
    private Calendar calendar;
    private EditText setStartDate;
    private EditText setEndDate;
    private EditText setStartTime;
    private EditText setEndTime;
    private EditText setDetail;
    private EditText setTitle;
    private EditText setLocation;
    private EditText setAlertDate;
    private EditText setAlertTime;
    private String Clickbtn = "";
    private String strStartDate = "";
    private String strEndDate = "";
    private String strStartTime = "";
    private String strEndTime = "";
    private String strAlertTime = "";
    private String strAlertDate = "";
    private Button PlacePickerBTN;
    private String locationToDb = "";
    private DatabaseAdapter databaseAdapter;
    private final int PLACE_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        setTitle = (EditText) findViewById(R.id.setTitle);
        setLocation = (EditText) findViewById(R.id.setLocation);
        setStartDate = (EditText) findViewById(R.id.setStartDate);
        setEndDate = (EditText) findViewById(R.id.setEndDate);
        setStartTime = (EditText) findViewById(R.id.setStartTime);
        setEndTime = (EditText) findViewById(R.id.setEndTime);
        setDetail = (EditText) findViewById(R.id.setDetail);
        setAlertDate = (EditText) findViewById(R.id.setAlertDate);
        setAlertTime = (EditText) findViewById(R.id.setAlertTime);
        PlacePickerBTN = (Button) findViewById(R.id.PlacePickerBTN);
        databaseAdapter = new DatabaseAdapter(getApplicationContext());
        if(getIntent().getStringExtra("titlePreset") != null && !getIntent().getStringExtra("titlePreset").isEmpty()){
            setTitle.setText(getIntent().getStringExtra("titlePreset"));
            setDetail.setText(getIntent().getStringExtra("detailPreset"));
            setLocation.setText(getIntent().getStringExtra("locationPreset"));
        }
        calendar = Calendar.getInstance();

        setDatePart();
        setTimePart();
    }

    private void setTimePart(){
        final TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int h, int m) {
                calendar.set(Calendar.HOUR_OF_DAY, h);
                calendar.set(Calendar.MINUTE, m);
                updateLabel();
            }
        };
        setStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clickbtn = "setStartTime";
                if(strStartTime.equals("")){
                    new TimePickerDialog(AddEventActivity.this, time, calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();
                }
                else{
                    String[] parts = strStartTime.split(":");
                    new TimePickerDialog(AddEventActivity.this, time, Integer.parseInt(parts[0]),  Integer.parseInt(parts[1]),true).show();
                }
            }
        });
        setAlertTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clickbtn = "setAlertTime";
                if(strAlertTime.equals("")){
                    new TimePickerDialog(AddEventActivity.this, time, calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();
                }
                else{
                    String[] parts = strAlertTime.split(":");
                    new TimePickerDialog(AddEventActivity.this, time, Integer.parseInt(parts[0]),  Integer.parseInt(parts[1]),true).show();
                }
            }
        });
        setEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clickbtn = "setEndTime";
                if(strEndTime.equals("")){
                    new TimePickerDialog(AddEventActivity.this, time, calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();
                }
                else{
                    String[] parts = strEndTime.split(":");
                    new TimePickerDialog(AddEventActivity.this, time, Integer.parseInt(parts[0]),  Integer.parseInt(parts[1]),true).show();
                }
            }
        });
    }

    private void setDatePart(){
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                calendar.set(Calendar.YEAR, y);
                calendar.set(Calendar.MONTH, m);
                calendar.set(Calendar.DAY_OF_MONTH, d);
                updateLabel();
            }
        };

        setStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clickbtn = "setStartDate";
                if(strStartDate.equals("")){
                    Toast.makeText(getApplicationContext(),setStartDate.getText(),Toast.LENGTH_LONG).show();
                    new DatePickerDialog(AddEventActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                }
                else{
                    String[] parts = strStartDate.split("/");
                    new DatePickerDialog(AddEventActivity.this, date, Integer.parseInt(parts[2]), Integer.parseInt(parts[1])-1, Integer.parseInt(parts[0])).show();
                }

            }
        });
        setAlertDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clickbtn = "setAlertDate";
                if(strAlertDate.equals("")){
                    Toast.makeText(getApplicationContext(),setStartDate.getText(),Toast.LENGTH_LONG).show();
                    new DatePickerDialog(AddEventActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                }
                else{
                    String[] parts = strAlertDate.split("/");
                    new DatePickerDialog(AddEventActivity.this, date, Integer.parseInt(parts[2]), Integer.parseInt(parts[1])-1, Integer.parseInt(parts[0])).show();
                }
            }
        });
        setEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clickbtn = "setEndDate";
                if(strEndDate.equals("")){
                    new DatePickerDialog(AddEventActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                }
                else{
                    String[] parts = strEndDate.split("/");
                    new DatePickerDialog(AddEventActivity.this, date, Integer.parseInt(parts[2]), Integer.parseInt(parts[1])-1, Integer.parseInt(parts[0])).show();
                }
            }
        });
    }

    private void updateLabel() {
        if (Clickbtn.equals("setStartDate")) {
            setStartDate.setText("start date is : " + formatTh().format(calendar.getTime()));
            strStartDate = formatTh().format(calendar.getTime());
        }
        else if (Clickbtn.equals("setEndDate")){
            setEndDate.setText("end date is : " + formatTh().format(calendar.getTime()));
            strEndDate = formatTh().format(calendar.getTime());
        }
        else if (Clickbtn.equals("setAlertDate")){
            setAlertDate.setText("alert date is : " + formatTh().format(calendar.getTime()));
            strAlertDate = formatTh().format(calendar.getTime());
        }
        else if (Clickbtn.equals("setStartTime")){
            setStartTime.setText("start time is : " + formatTh().format(calendar.getTime()));
            strStartTime = formatTh().format(calendar.getTime());
        }
        else if (Clickbtn.equals("setEndTime")){
            setEndTime.setText("end time is : " + formatTh().format(calendar.getTime()));
            strEndTime = formatTh().format(calendar.getTime());
        }
        else if (Clickbtn.equals("setAlertTime")){
            setAlertTime.setText("alert time is : " + formatTh().format(calendar.getTime()));
            strAlertTime = formatTh().format(calendar.getTime());
        }
    }

    @Nullable
    private Format formatTh(){
        Format formatter;
        if(Clickbtn.equals("setStartDate") || Clickbtn.equals("setEndDate") || Clickbtn.equals("setAlertDate")){
                formatter = new SimpleDateFormat("dd/MM/yyyy", new Locale("en", "TH"));
            return formatter;
        }
        else if(Clickbtn.equals("setStartTime") || Clickbtn.equals("setEndTime") || Clickbtn.equals("setAlertTime")){
            formatter = new SimpleDateFormat("HH:mm", new Locale("en", "TH"));
            return formatter;
        }
        return null;
    }

    public void onSubmitAddEvent(View view) {
        String title = setTitle.getText().toString();
        String location;
        try {
            String lat = setLocation.getText().toString().split(" : ")[1];
            location = locationToDb;
        }catch (Exception e){
            location = setLocation.getText().toString();
        }
        String start_date = strStartDate;
        String end_date = strEndDate;
        String start_time = strStartTime;
        String end_time = strEndTime;
        String alert_time = strAlertTime;
        String alert_date = strAlertDate;
        String detail = setDetail.getText().toString();
        if(title.equals("") || location.equals("")  || start_date.equals("") || end_date.equals("") ||
                start_time.equals("") || end_time.equals("") || alert_time.equals("") || detail.equals("") || alert_date.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Enter empty field", Toast.LENGTH_SHORT).show();
        }
        else {
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
            Date date = new Date();
            try {
                Date start = sdf1.parse(start_date + " - " + start_time);
                Date end = sdf1.parse(end_date + " - " + end_time);
                if (end.compareTo(start) <= 0){
                    Toast.makeText(getApplicationContext(),"Wrong start and end event", Toast.LENGTH_SHORT).show();
                    return;
                }
                Date currentDate = sdf1.parse(sdf1.format(date));
                Date alert = sdf1.parse(alert_date + " - " + alert_time);
                if (alert.compareTo(currentDate) <= 0){
                    Toast.makeText(getApplicationContext(),"Wrong alert time", Toast.LENGTH_SHORT).show();
                    return;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String result = databaseAdapter.insertDataEvent(title,location,start_date,end_date,start_time,end_time,alert_date,alert_time,detail);
            if(!result.equals("success")){
                Toast.makeText(getApplicationContext(),"Insertion unsucessfull!"+result,Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"Insertion success!!",Toast.LENGTH_SHORT).show();
                setTitle.setText("");
                setLocation.setText("");
                setStartDate.setText("");
                setEndDate.setText("");
                setStartTime.setText("");
                setEndTime.setText("");
                setDetail.setText("");
                setAlertDate.setText("");
                setAlertTime.setText("");
                finish();
            }
        }
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
                locationToDb = place.getName() + " : " + place.getAddress() + " : "+ place.getLatLng().latitude + " : " + place.getLatLng().longitude;
            }
        }
    }
}

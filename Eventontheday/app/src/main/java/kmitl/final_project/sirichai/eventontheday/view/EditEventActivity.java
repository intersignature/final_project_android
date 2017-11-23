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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kmitl.final_project.sirichai.eventontheday.R;
import kmitl.final_project.sirichai.eventontheday.controller.DatabaseAdapter;
import kmitl.final_project.sirichai.eventontheday.model.EventInfo;

public class EditEventActivity extends AppCompatActivity implements View.OnClickListener {
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
    private String strAlertDate = "";
    private String strAlertTime = "";
    private String locationToDb = "";
    private DatabaseAdapter databaseAdapter;
    private String oldId;
    private final int PLACE_PICKER_REQUEST = 1;
    private Button placePickerUPDATEBTN;
    private Button submitEditEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        initInstances();
        setValues();
        setDatePart();
        setTimePart();
    }

    private void setValues() {
        oldId = getIntent().getStringExtra("oldId");
        EventInfo data = databaseAdapter.getEachDataEvent(oldId);
        locationToDb = data.getLocation();
        setTitle.setText(data.getTitle());
        try {
            setLocation.setText(data.getLocation().split(" : ")[0] + " : " + data.getLocation().split(" : ")[1]);
        } catch (Exception e) {
            setLocation.setText(data.getLocation());
        }
        setStartDate.setText("start date is : " + data.getStart_date());
        setEndDate.setText("end date is : " + data.getEnd_date());
        setStartTime.setText("start time is : " + data.getStart_time());
        setEndTime.setText("end time is : " + data.getEnd_time());
        setDetail.setText(data.getDetail());
        setAlertDate.setText("alert date is : " + data.getAlert_date());
        setAlertTime.setText("alert time is : " + data.getAlert_time());
        strStartDate = data.getStart_date();
        strEndDate = data.getEnd_date();
        strStartTime = data.getStart_time();
        strEndTime = data.getEnd_time();
        strAlertDate = data.getAlert_date();
        strAlertTime = data.getAlert_time();
    }

    private void initInstances() {
        calendar = Calendar.getInstance();
        databaseAdapter = new DatabaseAdapter(getApplicationContext());
        setTitle = (EditText) findViewById(R.id.setNewTitle);
        setLocation = (EditText) findViewById(R.id.setNewLocation);
        setStartDate = (EditText) findViewById(R.id.setNewStartDate);
        setEndDate = (EditText) findViewById(R.id.setNewEndDate);
        setStartTime = (EditText) findViewById(R.id.setNewStartTime);
        setEndTime = (EditText) findViewById(R.id.setNewEndTime);
        setDetail = (EditText) findViewById(R.id.setNewDetail);
        setAlertDate = (EditText) findViewById(R.id.setNewAlertDate);
        setAlertTime = (EditText) findViewById(R.id.setNewAlertTime);
        placePickerUPDATEBTN = (Button) findViewById(R.id.PlacePickerUPDATEBTN);
        submitEditEvent = (Button) findViewById(R.id.submitEditEvent);
        placePickerUPDATEBTN.setOnClickListener(this);
        submitEditEvent.setOnClickListener(this);
    }

    private void setTimePart() {
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
                if (strStartTime.equals("")) {
                    new TimePickerDialog(EditEventActivity.this, time, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
                } else {
                    String[] parts = strStartTime.split(":");
                    new TimePickerDialog(EditEventActivity.this, time, Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), true).show();
                }
            }
        });
        setEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clickbtn = "setEndTime";
                if (strEndTime.equals("")) {
                    new TimePickerDialog(EditEventActivity.this, time, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
                } else {
                    String[] parts = strEndTime.split(":");
                    new TimePickerDialog(EditEventActivity.this, time, Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), true).show();
                }
            }
        });
        setAlertTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clickbtn = "setAlertTime";
                if (strAlertTime.equals("")) {
                    new TimePickerDialog(EditEventActivity.this, time, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
                } else {
                    String[] parts = strAlertTime.split(":");
                    new TimePickerDialog(EditEventActivity.this, time, Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), true).show();
                }
            }
        });
    }

    private void setDatePart() {
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
                if (strStartDate.equals("")) {
                    new DatePickerDialog(EditEventActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                } else {
                    String[] parts = strStartDate.split("/");
                    new DatePickerDialog(EditEventActivity.this, date, Integer.parseInt(parts[2]), Integer.parseInt(parts[1]) - 1, Integer.parseInt(parts[0])).show();
                }

            }
        });
        setEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clickbtn = "setEndDate";
                if (strEndDate.equals("")) {
                    new DatePickerDialog(EditEventActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                } else {
                    String[] parts = strEndDate.split("/");
                    new DatePickerDialog(EditEventActivity.this, date, Integer.parseInt(parts[2]), Integer.parseInt(parts[1]) - 1, Integer.parseInt(parts[0])).show();
                }
            }
        });

        setAlertDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clickbtn = "setAlertDate";
                if (strAlertDate.equals("")) {
                    new DatePickerDialog(EditEventActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                } else {
                    String[] parts = strAlertDate.split("/");
                    new DatePickerDialog(EditEventActivity.this, date, Integer.parseInt(parts[2]), Integer.parseInt(parts[1]) - 1, Integer.parseInt(parts[0])).show();
                }
            }
        });
    }

    @Nullable
    private Format formatTh() {
        Format formatter;
        if (Clickbtn.equals("setStartDate") || Clickbtn.equals("setEndDate") || Clickbtn.equals("setAlertDate")) {
            formatter = new SimpleDateFormat("dd/MM/yyyy", new Locale("en", "TH"));
            return formatter;
        } else if (Clickbtn.equals("setStartTime") || Clickbtn.equals("setEndTime") || Clickbtn.equals("setAlertTime")) {
            formatter = new SimpleDateFormat("HH:mm", new Locale("en", "TH"));
            return formatter;
        }
        return null;
    }

    private void updateLabel() {
        if (Clickbtn.equals("setStartDate")) {
            setStartDate.setText("start date is : " + formatTh().format(calendar.getTime()));
            strStartDate = formatTh().format(calendar.getTime());
        } else if (Clickbtn.equals("setEndDate")) {
            setEndDate.setText("end date is : " + formatTh().format(calendar.getTime()));
            strEndDate = formatTh().format(calendar.getTime());
        } else if (Clickbtn.equals("setAlertDate")) {
            setAlertDate.setText("alert date is : " + formatTh().format(calendar.getTime()));
            strAlertDate = formatTh().format(calendar.getTime());
        } else if (Clickbtn.equals("setStartTime")) {
            setStartTime.setText("start time is : " + formatTh().format(calendar.getTime()));
            strStartTime = formatTh().format(calendar.getTime());
        } else if (Clickbtn.equals("setEndTime")) {
            setEndTime.setText("end time is : " + formatTh().format(calendar.getTime()));
            strEndTime = formatTh().format(calendar.getTime());
        } else if (Clickbtn.equals("setAlertTime")) {
            setAlertTime.setText("alert time is : " + formatTh().format(calendar.getTime()));
            strAlertTime = formatTh().format(calendar.getTime());
        }
    }

    private boolean checkTitleString(String title){ //reture true if title don't contain special characters
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
        Matcher matcher = pattern.matcher(title);
        return matcher.matches();
    }

    private void editEvent() {
        String title = setTitle.getText().toString();
        String location;
        try {
            String lat = setLocation.getText().toString().split(" : ")[1];
            location = locationToDb;
        } catch (Exception e) {
            location = setLocation.getText().toString();
        }
        String start_date = strStartDate;
        String end_date = strEndDate;
        String start_time = strStartTime;
        String end_time = strEndTime;
        String alert_time = strAlertTime;
        String alert_date = strAlertDate;
        String detail = setDetail.getText().toString();

        if (title.equals("") || location.equals("") || start_date.equals("") || end_date.equals("") ||
                start_time.equals("") || end_time.equals("") || alert_date.equals("") || alert_time.equals("") || detail.equals("")) {
            Toast.makeText(getApplicationContext(), "Enter empty field", Toast.LENGTH_SHORT).show();
            return;
        } else if(!checkTitleString(title)){
            Toast.makeText(getApplicationContext(), "Title must not contain special characters", Toast.LENGTH_SHORT).show();
            return;
        } else {
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
            Date date = new Date();
            try {
                Date start = sdf1.parse(start_date + " - " + start_time);
                Date end = sdf1.parse(end_date + " - " + end_time);
                if (end.compareTo(start) <= 0) {
                    Toast.makeText(getApplicationContext(), "Wrong start and end event", Toast.LENGTH_SHORT).show();
                    return;
                }
                Date currentDate = sdf1.parse(sdf1.format(date));
                Date alert = sdf1.parse(alert_date + " - " + alert_time);
                if (alert.compareTo(currentDate) <= 0) {
                    Toast.makeText(getApplicationContext(), "Wrong alert time", Toast.LENGTH_SHORT).show();
                    return;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        String result = databaseAdapter.updateDataEvent(title, location, start_date, end_date, start_time, end_time, alert_date, alert_time, detail, oldId);
        if (!result.equals("success")) {
            Toast.makeText(getApplicationContext(), "update unsuccessful!!" + result, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "update successful!!", Toast.LENGTH_SHORT).show();
            finish();
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                setLocation.setText(place.getName() + " : " + place.getAddress());
                locationToDb = place.getName() + " : " + place.getAddress() + " : " + place.getLatLng().latitude + " : " + place.getLatLng().longitude;
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.submitEditEvent) {
            editEvent();
        } else if (v.getId() == R.id.PlacePickerUPDATEBTN) {
            startPlacePicker();
        }
    }
}
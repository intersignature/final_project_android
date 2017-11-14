package kmitl.final_project.sirichai.eventontheday.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import kmitl.final_project.sirichai.eventontheday.R;
import kmitl.final_project.sirichai.eventontheday.model.DatabaseAdapter;
import kmitl.final_project.sirichai.eventontheday.model.ListEvent;

public class EditEventActivity extends AppCompatActivity {
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
    private DatabaseAdapter databaseAdapter;
    private Button delete;
    List<ListEvent> listAllEvents = new ArrayList<>();
    private String oldId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);
        //set findViewById to all elements
        setTitle = findViewById(R.id.setNewTitle);
        setLocation = findViewById(R.id.setNewLocation);
        setStartDate = findViewById(R.id.setNewStartDate);
        setEndDate = findViewById(R.id.setNewEndDate);
        setStartTime = findViewById(R.id.setNewStartTime);
        setEndTime = findViewById(R.id.setNewEndTime);
        setDetail = findViewById(R.id.setNewDetail);
        setAlertDate = findViewById(R.id.setNewAlertDate);
        setAlertTime = findViewById(R.id.setNewAlertTime);
        databaseAdapter = new DatabaseAdapter(getApplicationContext());
        List<List> datas = databaseAdapter.getData();
        oldId = getIntent().getStringExtra("oldId");

        //Log.i("Drink", oldTitle);
        listAllEvents = new ArrayList<>();
        for (int i=0; i<datas.size();i++){
            List<String> eachEvent = datas.get(i);
            if (eachEvent.get(9).equals(oldId)){
                //Log.i("Drink", eachEvent.toString());
                setTitle.setText(eachEvent.get(0));
                setLocation.setText(eachEvent.get(1));
                setStartDate.setText("start date is : " + eachEvent.get(2));
                setEndDate.setText("end date is : " + eachEvent.get(3));
                setStartTime.setText("start time is : " + eachEvent.get(4));
                setEndTime.setText("end time is : " + eachEvent.get(5));
                setDetail.setText(eachEvent.get(8));
                setAlertDate.setText("alert date is : "+eachEvent.get(6));
                setAlertTime.setText("alert time is : "+eachEvent.get(7));
                strStartDate = eachEvent.get(2);
                strEndDate = eachEvent.get(3);
                strStartTime = eachEvent.get(4);
                strEndTime = eachEvent.get(5);
                strAlertDate = eachEvent.get(6);
                strAlertTime = eachEvent.get(7);
//                radioGroupSetAlertTime.getChildAt(Integer.parseInt(checkekId)).;
//                radioGroupSetAlertTime.check(Integer.parseInt(checkekId));

            }

        }
        calendar = Calendar.getInstance();
        //set date start and end
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
                    new DatePickerDialog(EditEventActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                }
                else{
                    String[] parts = strStartDate.split("/");
                    new DatePickerDialog(EditEventActivity.this, date, Integer.parseInt(parts[2]), Integer.parseInt(parts[1])-1, Integer.parseInt(parts[0])).show();
                }

            }
        });
        setEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clickbtn = "setEndDate";
                if(strEndDate.equals("")){
                    new DatePickerDialog(EditEventActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                }
                else{
                    String[] parts = strEndDate.split("/");
                    new DatePickerDialog(EditEventActivity.this, date, Integer.parseInt(parts[2]), Integer.parseInt(parts[1])-1, Integer.parseInt(parts[0])).show();
                }
            }
        });

        setAlertDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clickbtn = "setAlertDate";
                if(strAlertDate.equals("")){
                    new DatePickerDialog(EditEventActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                }
                else{
                    String[] parts = strAlertDate.split("/");
                    new DatePickerDialog(EditEventActivity.this, date, Integer.parseInt(parts[2]), Integer.parseInt(parts[1])-1, Integer.parseInt(parts[0])).show();
                }
            }
        });

        //set time start and end

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
                    new TimePickerDialog(EditEventActivity.this, time, calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();
                }
                else{
                    String[] parts = strStartTime.split(":");
                    new TimePickerDialog(EditEventActivity.this, time, Integer.parseInt(parts[0]),  Integer.parseInt(parts[1]),true).show();
                }
            }
        });
        setEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clickbtn = "setEndTime";
                if(strEndTime.equals("")){
                    new TimePickerDialog(EditEventActivity.this, time, calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();
                }
                else{
                    String[] parts = strEndTime.split(":");
                    new TimePickerDialog(EditEventActivity.this, time, Integer.parseInt(parts[0]),  Integer.parseInt(parts[1]),true).show();
                }
            }
        });
        setAlertTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clickbtn = "setAlertTime";
                if(strAlertTime.equals("")){
                    new TimePickerDialog(EditEventActivity.this, time, calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();
                }
                else{
                    String[] parts = strAlertTime.split(":");
                    new TimePickerDialog(EditEventActivity.this, time, Integer.parseInt(parts[0]),  Integer.parseInt(parts[1]),true).show();
                }
            }
        });
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

    public void viewdata(View view) {
        //int data = databaseAdapter.clearDB();
        List<List> datas = databaseAdapter.getData();
        Log.i("b", datas.toString());
    }
    public void onSubmitEditEvent(View view) {
        String title = setTitle.getText().toString();
        String location = setLocation.getText().toString();
        String start_date = strStartDate;
        String end_date = strEndDate;
        String start_time = strStartTime;
        String end_time = strEndTime;
        String alert_time = strAlertTime;
        String alert_date = strAlertDate;
        String detail = setDetail.getText().toString();
        Log.i("add",title+" "+location+" "+start_date+" "+end_date+" "+start_time+" "+ end_time+ " "+alert_date+" "+alert_time+" "+detail);

        if(title.equals("") || location.equals("")  || start_date.equals("") || end_date.equals("") ||
                start_time.equals("") || end_time.equals("") || alert_date.equals("") || alert_time.equals("") || detail.equals("") ){

            Toast.makeText(getApplicationContext(),"Enter empty field", Toast.LENGTH_SHORT).show();
        }
        else {
            int id = databaseAdapter.update(title, location, start_date, end_date, start_time, end_time,alert_date ,alert_time, detail, oldId);
            if(id <=0){

                Toast.makeText(getApplicationContext(),"update unsucessfull!",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(),"update success!1!",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

}
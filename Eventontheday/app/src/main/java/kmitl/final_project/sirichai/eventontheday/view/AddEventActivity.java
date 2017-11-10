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

public class AddEventActivity extends AppCompatActivity {
    private Calendar calendar;
    private EditText setStartDate;
    private EditText setEndDate;
    private EditText setStartTime;
    private EditText setEndTime;
    private EditText setDetail;
    private EditText setTitle;
    private EditText setLocation;
    private String Clickbtn = "";
    private String strStartDate = "";
    private String strEndDate = "";
    private String strStartTime = "";
    private String strEndTime = "";
    private RadioGroup radioGroupSetAlertTime;
    private RadioButton rb;
    private DatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        //set findViewById to all elements
        setTitle = findViewById(R.id.setTitle);
        setLocation = findViewById(R.id.setLocation);
        setStartDate = findViewById(R.id.setStartDate);
        setEndDate = findViewById(R.id.setEndDate);
        setStartTime = findViewById(R.id.setStartTime);
        setEndTime = findViewById(R.id.setEndTime);
        setDetail = findViewById(R.id.setDetail);
        radioGroupSetAlertTime = findViewById(R.id.setAlertTime);
        databaseAdapter = new DatabaseAdapter(getApplicationContext());

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
                    new DatePickerDialog(AddEventActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                }
                else{
                    String[] parts = strStartDate.split("/");
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
                    new TimePickerDialog(AddEventActivity.this, time, calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();
                }
                else{
                    String[] parts = strStartTime.split(":");
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
    private void updateLabel() {
        if (Clickbtn.equals("setStartDate")) {
            setStartDate.setText("start date is : " + formatTh().format(calendar.getTime()));
            strStartDate = formatTh().format(calendar.getTime());
        }
        else if (Clickbtn.equals("setEndDate")){
            setEndDate.setText("end date is : " + formatTh().format(calendar.getTime()));
            strEndDate = formatTh().format(calendar.getTime());
        }
        else if (Clickbtn.equals("setStartTime")){
            setStartTime.setText("start time is : " + formatTh().format(calendar.getTime()));
            strStartTime = formatTh().format(calendar.getTime());
        }
        else if (Clickbtn.equals("setEndTime")){
            setEndTime.setText("end time is : " + formatTh().format(calendar.getTime()));
            strEndTime = formatTh().format(calendar.getTime());
        }
    }

    @Nullable
    private Format formatTh(){
        Format formatter;
        if(Clickbtn.equals("setStartDate") || Clickbtn.equals("setEndDate")){
                formatter = new SimpleDateFormat("dd/MM/yyyy", new Locale("en", "TH"));
            return formatter;
        }
        else if(Clickbtn.equals("setStartTime") || Clickbtn.equals("setEndTime")){
            formatter = new SimpleDateFormat("HH:mm", new Locale("en", "TH"));
            return formatter;
        }
        return null;
    }


    public void onGetAlert(View view) {
        //get view of radioGroup alert time
//        radioGroupSetAlertTime = findViewById(R.id.setAlertTime);
//        int radioButtonId = radioGroupSetAlertTime.getCheckedRadioButtonId();
//        rb = findViewById(radioButtonId);
        radioGroupSetAlertTime = findViewById(R.id.setAlertTime);
        int radioButtonID = radioGroupSetAlertTime.getCheckedRadioButtonId();
        View radioButton = radioGroupSetAlertTime.findViewById(radioButtonID);
        int idx = radioGroupSetAlertTime.indexOfChild(radioButton);
        Toast.makeText(getApplicationContext(), String.valueOf(idx),Toast.LENGTH_SHORT).show();
    }

    public String getAlert(){
        radioGroupSetAlertTime = findViewById(R.id.setAlertTime);
        int radioButtonID = radioGroupSetAlertTime.getCheckedRadioButtonId();
        View radioButton = radioGroupSetAlertTime.findViewById(radioButtonID);
        int idx = radioGroupSetAlertTime.indexOfChild(radioButton);
        Toast.makeText(getApplicationContext(), String.valueOf(idx),Toast.LENGTH_SHORT).show();
        return String.valueOf(idx);
    }
    public void clearAlert(){
        radioGroupSetAlertTime = findViewById(R.id.setAlertTime);
        int radioButtonId = radioGroupSetAlertTime.getCheckedRadioButtonId();
        rb = findViewById(radioButtonId);
        rb.setChecked(false);
    }
    public void onSubmitAddEvent(View view) {
        String title = setTitle.getText().toString();
        String location = setLocation.getText().toString();
        String start_date = strStartDate;
        String end_date = strEndDate;
        String start_time = strStartTime;
        String end_time = strEndTime;
        String alert_time = getAlert();
        String detail = setDetail.getText().toString();
        if(title.equals("") || location.equals("")  || start_date.equals("") || end_date.equals("") ||
                start_time.equals("") || end_time.equals("") || alert_time.equals("") || detail.equals("") ){
            Toast.makeText(getApplicationContext(),"Enter empty field", Toast.LENGTH_SHORT).show();
        }
        else {
            long id = databaseAdapter.insertData(title,location,start_date,end_date,start_time,end_time,alert_time,detail);
            if((int)id <=0){
                Toast.makeText(getApplicationContext(),"Insertion unsucessfull!",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"Insertion success!1!",Toast.LENGTH_SHORT).show();
                setTitle.setText("");
                setLocation.setText("");
                setStartDate.setText("");
                setEndDate.setText("");
                setStartTime.setText("");
                setEndTime.setText("");
                setDetail.setText("");
                clearAlert();
//                Intent intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
                finish();
            }
        }

//        Toast.makeText(getApplicationContext(), "title:"+title+"\n"+"location:"+location+"\n"+"start_date:"+start_date+"\n"+
//                "end_date:"+end_date+"\n"+"start_time:"+start_time+"\n"+"end_time:"+end_time+"\n"+"alert:"+alert_time+"\n"+
//                "detail:"+detail+"\n", Toast.LENGTH_SHORT).show();
    }

    public void viewdata(View view) {
        int data = databaseAdapter.clearDB();
        Log.i("b", Integer.toString(data));
        //Log.i("a",data);
        //Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();
    }
}

package kmitl.final_project.sirichai.eventontheday.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import kmitl.final_project.sirichai.eventontheday.R;

public class AddEvent extends AppCompatActivity {
    private Calendar calendar;
    private EditText setStartDate;
    private EditText setEndDate;
    private EditText setStartTime;
    private EditText setEndTime;
    private String Clickbtn = "";
    private String strStartDate = "";
    private String strEndDate = "";
    private String strStartTime = "";
    private String strEndTime = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        calendar = Calendar.getInstance();

        //set date start and end
        setStartDate = findViewById(R.id.setStartDate);
        setEndDate = findViewById(R.id.setEndDate);
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
                    new DatePickerDialog(AddEvent.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                }
                else{
                    String[] parts = strStartDate.split("/");
                    new DatePickerDialog(AddEvent.this, date, Integer.parseInt(parts[2]), Integer.parseInt(parts[1]), Integer.parseInt(parts[0])).show();
                }

            }
        });
        setEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clickbtn = "setEndDate";
                if(strEndDate.equals("")){
                    new DatePickerDialog(AddEvent.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                }
                else{
                    String[] parts = strEndDate.split("/");
                    new DatePickerDialog(AddEvent.this, date, Integer.parseInt(parts[2]), Integer.parseInt(parts[1]), Integer.parseInt(parts[0])).show();
                }
            }
        });

        //set time start and end
        setStartTime = findViewById(R.id.setStartTime);
        setEndTime = findViewById(R.id.setEndTime);
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
                    new TimePickerDialog(AddEvent.this, time, calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();
                }
                else{
                    String[] parts = strStartTime.split(":");
                    new TimePickerDialog(AddEvent.this, time, Integer.parseInt(parts[0]),  Integer.parseInt(parts[1]),true).show();
                }
            }
        });
        setEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clickbtn = "setEndTime";
                if(strEndTime.equals("")){
                    new TimePickerDialog(AddEvent.this, time, calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();
                }
                else{
                    String[] parts = strEndTime.split(":");
                    new TimePickerDialog(AddEvent.this, time, Integer.parseInt(parts[0]),  Integer.parseInt(parts[1]),true).show();
                }
            }
        });
    }

    private void updateLabel() {
        if (Clickbtn.equals("setStartDate")) {
            setStartDate.setText("start date is : " + formatTh().format(calendar.getTime()));
            setStartDate.setTextColor(Color.BLACK);
            setStartDate.setTextSize(15);
            setStartDate.setGravity(Gravity.CENTER);
            strStartDate = formatTh().format(calendar.getTime());
        }
        else if (Clickbtn.equals("setEndDate")){
            setEndDate.setText("end date is : " + formatTh().format(calendar.getTime()));
            setEndDate.setTextColor(Color.BLACK);
            setEndDate.setTextSize(15);
            setEndDate.setGravity(Gravity.CENTER);
            strEndDate = formatTh().format(calendar.getTime());
        }
        else if (Clickbtn.equals("setStartTime")){
            setStartTime.setText("start time is : " + formatTh().format(calendar.getTime()));
            setStartTime.setTextColor(Color.BLACK);
            setStartTime.setTextSize(15);
            setStartTime.setGravity(Gravity.CENTER);
            strStartTime = formatTh().format(calendar.getTime());
        }
        else if (Clickbtn.equals("setEndTime")){
            setEndTime.setText("end time is : " + formatTh().format(calendar.getTime()));
            setEndTime.setTextColor(Color.BLACK);
            setEndTime.setTextSize(15);
            setEndTime.setGravity(Gravity.CENTER);
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


}

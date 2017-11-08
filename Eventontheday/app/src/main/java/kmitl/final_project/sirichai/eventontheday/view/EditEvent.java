package kmitl.final_project.sirichai.eventontheday.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import kmitl.final_project.sirichai.eventontheday.R;
import kmitl.final_project.sirichai.eventontheday.model.DatabaseAdapter;
import kmitl.final_project.sirichai.eventontheday.model.ListEvent;
import kmitl.final_project.sirichai.eventontheday.model.OldTitle;

public class EditEvent extends AppCompatActivity {
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
    private Button delete;
    List<ListEvent> listAllEvents = new ArrayList<>();
    private String oldTitle;
    private String checkekId;

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
        radioGroupSetAlertTime = findViewById(R.id.setNewAlertTime);
        databaseAdapter = new DatabaseAdapter(getApplicationContext());
        List<List> datas = databaseAdapter.getData();
        oldTitle = getIntent().getStringExtra("oldTitle");

        Log.i("Drink", oldTitle);
        listAllEvents = new ArrayList<>();
        for (int i=0; i<datas.size();i++){
            List<String> eachEvent = datas.get(i);
            if (eachEvent.get(0).equals(oldTitle)){
                Log.i("Drink", eachEvent.toString());
                setTitle.setText(eachEvent.get(0));
                setLocation.setText(eachEvent.get(1));
                setStartDate.setText("start date is : " + eachEvent.get(2));
                setEndDate.setText("end date is : " + eachEvent.get(3));
                setStartTime.setText("start time is : " + eachEvent.get(4));
                setEndTime.setText("end time is : " + eachEvent.get(5));
                setDetail.setText(eachEvent.get(7));
                checkekId = eachEvent.get(6);
                radioGroupSetAlertTime = findViewById(R.id.setNewAlertTime);
                radioGroupSetAlertTime.getChildAt(Integer.parseInt(checkekId)).setPressed(true);
//                radioGroupSetAlertTime.getChildAt(Integer.parseInt(checkekId)).;
//                radioGroupSetAlertTime.check(Integer.parseInt(checkekId));
            }

        }
    }

    public void onGetAlert(View view) {
    }

    public void onSubmitAddEvent(View view) {
    }

    public void viewdata(View view) {
    }
}

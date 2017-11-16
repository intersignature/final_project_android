package kmitl.final_project.sirichai.eventontheday.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import kmitl.final_project.sirichai.eventontheday.R;
import kmitl.final_project.sirichai.eventontheday.model.DatabaseAdapter;

public class ViewEventActivity extends AppCompatActivity {
    private TextView viewTitle;
    private TextView viewLocation;
    private TextView viewStartDate;
    private TextView viewEndDate;
    private TextView viewStartTime;
    private TextView viewEndTime;
    private TextView viewAlertDate;
    private TextView viewAlertTime;
    private TextView viewDetail;
    private DatabaseAdapter databaseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
        viewTitle = findViewById(R.id.viewTitle);
        viewLocation = findViewById(R.id.viewLocation);
        viewStartDate = findViewById(R.id.viewStartDate);
        viewEndDate = findViewById(R.id.viewEndDate);
        viewStartTime = findViewById(R.id.viewStartTime);
        viewEndTime = findViewById(R.id.viewEndTime);
        viewAlertDate = findViewById(R.id.viewAlertDate);
        viewAlertTime = findViewById(R.id.viewAlertTime);
        viewDetail = findViewById(R.id.viewDetail);
        //viewTitle.setText(getIntent().getStringExtra("title"));
        databaseAdapter = new DatabaseAdapter(getApplicationContext());
        List<String> data = databaseAdapter.getEachDataEvent(getIntent().getStringExtra("id"));
        Log.i("viewecent", data.toString());
        viewTitle.setText(data.get(0));
        viewLocation.setText(data.get(1));
        viewStartDate.setText("Start date is : "+data.get(2));
        viewEndDate.setText("End date is : "+data.get(3));
        viewStartTime.setText("Start time is : "+data.get(4));
        viewEndTime.setText("End time is : "+data.get(5));
        viewAlertDate.setText("Alert date is : " + data.get(6));
        viewAlertTime.setText("Alert time is : "+data.get(7));
        viewDetail.setText(data.get(8));
    }
}

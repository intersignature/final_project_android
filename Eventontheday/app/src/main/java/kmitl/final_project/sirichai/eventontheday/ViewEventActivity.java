package kmitl.final_project.sirichai.eventontheday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import kmitl.final_project.sirichai.eventontheday.model.DatabaseAdapter;

public class ViewEventActivity extends AppCompatActivity {
    private TextView viewTitle;
    private TextView viewLocation;
    private TextView viewStartDate;
    private TextView viewEndDate;
    private TextView viewStartTime;
    private TextView viewEndTime;
    private TextView viewAlert;
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
        viewAlert = findViewById(R.id.viewAlert);
        viewDetail = findViewById(R.id.viewDetail);
        //viewTitle.setText(getIntent().getStringExtra("title"));
        databaseAdapter = new DatabaseAdapter(getApplicationContext());
        List<String> data = databaseAdapter.getEachData(getIntent().getStringExtra("id"));
        Log.i("viewecent", data.toString());
        viewTitle.setText(data.get(0));
        viewLocation.setText(data.get(1));
        viewStartDate.setText("Start date is : "+data.get(2));
        viewEndDate.setText("End date is : "+data.get(3));
        viewStartTime.setText("Start time is : "+data.get(4));
        viewEndTime.setText("End time is : "+data.get(5));
        if (data.get(6).equals("0")){
            viewAlert.setText("Before event start date 2 days (09:00)");
        }
        else if (data.get(6).equals("1")){
            viewAlert.setText("Before event start date 1 day (09:00)");
        }
        else if (data.get(6).equals("2")){
            viewAlert.setText("Before event start date 1 week");
        }
        else if (data.get(6).equals("3")){
            viewAlert.setText("At event start date");
        }
        viewDetail.setText(data.get(7));
    }
}

package kmitl.final_project.sirichai.eventontheday.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import kmitl.final_project.sirichai.eventontheday.R;
import kmitl.final_project.sirichai.eventontheday.model.DatabaseAdapter;

public class ViewPresetActivity extends AppCompatActivity {
    private TextView viewTitle;
    private TextView viewLocation;
    private TextView viewDetail;
    private DatabaseAdapter databaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_preset);
        databaseAdapter = new DatabaseAdapter(getApplicationContext());
        viewTitle = findViewById(R.id.viewTitlePreset);
        viewLocation = findViewById(R.id.viewLocationPreset);
        viewDetail = findViewById(R.id.viewDetailPreset);
        List<String> data = databaseAdapter.getEachDataPreset(getIntent().getStringExtra("idPreset"));
        viewTitle.setText(data.get(0));
        viewLocation.setText(data.get(1));
        viewDetail.setText(data.get(2));
    }
}

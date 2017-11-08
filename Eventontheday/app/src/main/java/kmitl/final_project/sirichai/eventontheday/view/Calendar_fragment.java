package kmitl.final_project.sirichai.eventontheday.view;

/**
 * Created by atomiz on 6/11/2560.
 */


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;


import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import kmitl.final_project.sirichai.eventontheday.MessageForDev;
import kmitl.final_project.sirichai.eventontheday.R;
import kmitl.final_project.sirichai.eventontheday.model.DatabaseAdapter;
import kmitl.final_project.sirichai.eventontheday.model.ListEvent;
import kmitl.final_project.sirichai.eventontheday.model.RecyclerAdapter;

public class Calendar_fragment extends Fragment {
    CalendarView mCalendarView;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<ListEvent> listEvents;
    TextView textView;
    private DatabaseAdapter databaseAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
        mCalendarView = rootView.findViewById(R.id.calendarView);
        recyclerView = rootView.findViewById(R.id.showEvent);

        textView = rootView.findViewById(R.id.emptyEventCal);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        databaseAdapter = new DatabaseAdapter(getContext());
        Calendar calendar = Calendar.getInstance();
        String formatter = new SimpleDateFormat("dd/MM/yyyy", new Locale("en", "TH")).format(calendar.getTime());
        int currentDay = Integer.parseInt(formatter.split("/")[0]);
        int currentMonth = Integer.parseInt(formatter.split("/")[1]);
        int currentYear = Integer.parseInt(formatter.split("/")[2]);
        createRecylerView(currentYear,currentMonth,currentDay);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                month +=1;
                Toast.makeText(calendarView.getContext(), "Year=" + year + " Month=" + month + " Day=" + dayOfMonth, Toast.LENGTH_LONG).show();
                createRecylerView(year, month, dayOfMonth);
            }
        });
        return rootView;
    }

    public void createRecylerView(int year, int month, int dayOfMonth){
        listEvents = new ArrayList<>();
        final List<List> datas = databaseAdapter.getData();
        for (int i=0; i<datas.size();i++){
            List<String> eachEvent = datas.get(i);
            int eachEventYear = Integer.parseInt(eachEvent.get(2).split("/")[2]);
            int eachEventMonth = Integer.parseInt(eachEvent.get(2).split("/")[1]);
            int eachEventDay= Integer.parseInt(eachEvent.get(2).split("/")[0]);
            if (year == eachEventYear && month == eachEventMonth && dayOfMonth == eachEventDay){

                ListEvent listEvent = new ListEvent(
                        "eventTitle: "+eachEvent.get(0),
                        "eventDate: "+ eachEvent.get(2),
                        "eventLocation: "+eachEvent.get(1)
                );
                textView.setText("");
                listEvents.add(listEvent);
            }
            adapter = new RecyclerAdapter(listEvents,getContext()); // add list of event to recycler view
            recyclerView.setAdapter(adapter);
        }
        MessageForDev m = new MessageForDev();
        m.Log(listEvents.size()+"");
        if (listEvents.size()==0){
            textView.setText("Empty Event");
        }
    }


}
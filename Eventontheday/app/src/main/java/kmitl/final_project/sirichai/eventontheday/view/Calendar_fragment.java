package kmitl.final_project.sirichai.eventontheday.view;

/**
 * Created by atomiz on 6/11/2560.
 */


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kmitl.final_project.sirichai.eventontheday.R;
import kmitl.final_project.sirichai.eventontheday.model.DatabaseAdapter;
import kmitl.final_project.sirichai.eventontheday.model.ListEvent;
import kmitl.final_project.sirichai.eventontheday.model.RecyclerEventAdapter;

public class Calendar_fragment extends Fragment {
    CalendarView mCalendarView;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<ListEvent> listAllEvents;
    String selectedDay;
    String selectedMonth;
    String selectedYear;
    TextView empTv;
    private DatabaseAdapter databaseAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
        mCalendarView = (CalendarView) rootView.findViewById(R.id.calendarView);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.showEvent);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String selected = sdf.format(new Date(mCalendarView.getDate()));
        selectedDay = selected.split("/")[0];
        selectedMonth = selected.split("/")[1];
        selectedYear = selected.split("/")[2];
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        databaseAdapter = new DatabaseAdapter(getContext());
        empTv = (TextView) rootView.findViewById(R.id.empTvCalendar);
//        Calendar calendar = Calendar.getInstance();
//        String formatter = new SimpleDateFormat("dd/MM/yyyy", new Locale("en", "TH")).format(calendar.getTime());
//        int currentDay = Integer.parseInt(formatter.split("/")[0]);
//        int currentMonth = Integer.parseInt(formatter.split("/")[1]);
//        int currentYear = Integer.parseInt(formatter.split("/")[2]);
        createRecylerView(Integer.parseInt(selectedYear),Integer.parseInt(selectedMonth),Integer.parseInt(selectedDay));
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                month +=1;
                Toast.makeText(calendarView.getContext(), "Year=" + year + " Month=" + month + " Day=" + dayOfMonth, Toast.LENGTH_LONG).show();
                selectedDay =  String.valueOf(dayOfMonth);
                selectedMonth = String.valueOf(month);
                selectedYear = String.valueOf(year);
                createRecylerView(year, month, dayOfMonth);
            }
        });
        return rootView;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case 0:
                int position = item.getGroupId();
                ListEvent listEvent = listAllEvents.get(position);
                String id = listEvent.getEventId();
                String result = databaseAdapter.deleteDataEvent(id);
                removeAt(position);
                Log.i("e1","e2");
                break;
            case 1:
                int position1 = item.getGroupId();
                ListEvent listEvent1 = listAllEvents.get(position1);
                String id1 = listEvent1.getEventId();
                Intent intent1;
                intent1 = new Intent(getContext(), EditEventActivity.class);
                intent1.putExtra("oldId",id1);
                getContext().startActivities(new Intent[]{intent1});
                getContext().stopService(intent1);
                break;
            case 2:
                int position2 = item.getGroupId();
                ListEvent listEvent2 = listAllEvents.get(position2);
                String id2 = listEvent2.getEventId();
                Intent intent2;
                intent2 = new Intent(getContext(), ViewEventActivity.class);
                intent2.putExtra("id", id2);
                getContext().startActivities(new Intent[]{intent2});
                break;
            case 3:
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void removeAt(int position) {
        listAllEvents.remove(position);
        if (listAllEvents.size()>0){
            empTv.setVisibility(View.INVISIBLE);
        }
        else {
            empTv.setVisibility(View.VISIBLE);
        }
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, listAllEvents.size());
    }

    public void createRecylerView(int year, int month, int dayOfMonth){
        listAllEvents = new ArrayList<>();
        List<List> datas = databaseAdapter.getDataEvent();
        for (int i=0; i<datas.size();i++){
            List<String> eachEvent = datas.get(i);
            int eachEventYear = Integer.parseInt(eachEvent.get(2).split("/")[2]);
            int eachEventMonth = Integer.parseInt(eachEvent.get(2).split("/")[1]);
            int eachEventDay= Integer.parseInt(eachEvent.get(2).split("/")[0]);
            Log.i("aaaa",year+" "+month+" "+dayOfMonth + " "+eachEventYear+" " +eachEventMonth+" " +eachEventDay );
            if (year == eachEventYear && month == eachEventMonth && dayOfMonth == eachEventDay){
                ListEvent listEvent = new ListEvent(
                        "Title: "+eachEvent.get(0),
                        "Date: "+ eachEvent.get(2),
                        "Location: "+eachEvent.get(1).split(" : ")[0], eachEvent.get(9)
                );
                listAllEvents.add(listEvent);
            }
        }
        if (listAllEvents.size()>0){
            empTv.setVisibility(View.INVISIBLE);
        }
        else {
            empTv.setVisibility(View.VISIBLE);
        }
        adapter = new RecyclerEventAdapter(listAllEvents,getContext(),"Cal"); // add list of event to recycler view
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
//        Log.i("b",listAllEvents.toString());
        createRecylerView(Integer.parseInt(selectedYear),Integer.parseInt(selectedMonth),Integer.parseInt(selectedDay));
//        adapter = new RecyclerEventAdapter(listAllEvents,getContext(),"Cal");
//        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

        if(getView()!=null){
            createRecylerView(Integer.parseInt(selectedYear),Integer.parseInt(selectedMonth),Integer.parseInt(selectedDay));
            adapter.notifyDataSetChanged();
        }
        else {

        }
    }
}
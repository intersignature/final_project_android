package kmitl.final_project.sirichai.eventontheday.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kmitl.final_project.sirichai.eventontheday.R;
import kmitl.final_project.sirichai.eventontheday.model.DatabaseAdapter;
import kmitl.final_project.sirichai.eventontheday.model.ListEvent;
import kmitl.final_project.sirichai.eventontheday.model.RecyclerAdapter;

/**
 * Created by atomiz on 6/11/2560.
 */

public class Event_fragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<ListEvent> listAllEvents = new ArrayList<>();
    TextView textView;
    private DatabaseAdapter databaseAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event, container, false);

        databaseAdapter = new DatabaseAdapter(getContext());
        List<List> datas = databaseAdapter.getData();
        recyclerView = rootView.findViewById(R.id.showAllEvent);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        textView = rootView.findViewById(R.id.emptyEvent);
        listAllEvents = new ArrayList<>();
        for (int i=0; i<datas.size();i++){
            List<String> eachEvent = datas.get(i);
            ListEvent listEvent = new ListEvent(
                    "Title: "+ eachEvent.get(0),
                    "Date: "+ eachEvent.get(2),
                    "Location: "+eachEvent.get(1)
            );
            listAllEvents.add(listEvent);
        }
        //Log.i("b",listAllEvents.toString());
        adapter = new RecyclerAdapter(listAllEvents,getContext()); // add list of event to recycler view
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        if (listAllEvents.size()==0){
            textView.setText("Empty Event");
        }
        else{
            textView.setText("");
        }
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        databaseAdapter = new DatabaseAdapter(getContext());
        List<List> datas = databaseAdapter.getData();
        listAllEvents = new ArrayList<>();
        for (int i=0; i<datas.size();i++){
            List<String> eachEvent = datas.get(i);
            ListEvent listEvent = new ListEvent(
                    "Title: "+ eachEvent.get(0),
                    "Date: "+ eachEvent.get(2),
                    "Location: "+eachEvent.get(1)
            );
            listAllEvents.add(listEvent);
        }
        //Log.i("b",listAllEvents.toString());
        if (listAllEvents.size()==0){
            textView.setText("Empty Event");
        }
        else{
            textView.setText("");
        }
        adapter = new RecyclerAdapter(listAllEvents,getContext());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            //เวลากดdeleteแล้วเปลี่ยนแท็บ อีกแท็บจะไม่เปลี่ยน
            //ให้dbมาใหม่
        }
    }
}

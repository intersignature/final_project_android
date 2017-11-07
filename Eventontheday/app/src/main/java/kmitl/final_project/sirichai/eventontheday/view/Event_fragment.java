package kmitl.final_project.sirichai.eventontheday.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kmitl.final_project.sirichai.eventontheday.R;
import kmitl.final_project.sirichai.eventontheday.model.ListEvent;
import kmitl.final_project.sirichai.eventontheday.model.RecyclerAdapter;

/**
 * Created by atomiz on 6/11/2560.
 */

public class Event_fragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<ListEvent> listAllEvents;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event, container, false);

        recyclerView = rootView.findViewById(R.id.showAllEvent);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        listAllEvents = new ArrayList<>();
        for (int i=0; i<2;i++){
            ListEvent listEvent = new ListEvent(
                    "eventTitle"+String.valueOf(i+1),
                    "eventDate"+ String.valueOf(i+1),
                    "eventDescription"+String.valueOf(i+1)
            );
            listAllEvents.add(listEvent);
        }
        adapter = new RecyclerAdapter(listAllEvents,getContext()); // add list of event to recycler view
        recyclerView.setAdapter(adapter);
        return rootView;
    }
}

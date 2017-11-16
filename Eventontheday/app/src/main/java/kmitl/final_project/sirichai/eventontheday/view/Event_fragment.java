package kmitl.final_project.sirichai.eventontheday.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kmitl.final_project.sirichai.eventontheday.R;
import kmitl.final_project.sirichai.eventontheday.model.DatabaseAdapter;
import kmitl.final_project.sirichai.eventontheday.model.ListEvent;
import kmitl.final_project.sirichai.eventontheday.model.RecyclerEventAdapter;

/**
 * Created by atomiz on 6/11/2560.
 */

public class Event_fragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<ListEvent> listAllEvents = new ArrayList<>();
    private DatabaseAdapter databaseAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event, container, false);
        recyclerView = rootView.findViewById(R.id.showAllEvent);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        createRecyclerView();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        createRecyclerView();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getView()!=null){
            createRecyclerView();
            adapter.notifyDataSetChanged();
        }
    }

    public void createRecyclerView(){
        databaseAdapter = new DatabaseAdapter(getContext());
        List<List> datas = databaseAdapter.getDataEvent();
        listAllEvents = new ArrayList<>();
        for (int i=0; i<datas.size();i++){
            List<String> eachEvent = datas.get(i);
            ListEvent listEvent = new ListEvent(
                    "Title: "+ eachEvent.get(0),
                    "Date: "+ eachEvent.get(2),
                    "Location: "+eachEvent.get(1),eachEvent.get(9)
            );
            listAllEvents.add(listEvent);
        }
        adapter = new RecyclerEventAdapter(listAllEvents,getContext());
        recyclerView.setAdapter(adapter);
    }
}

package kmitl.final_project.sirichai.eventontheday.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
    TextView empTv;
    private DatabaseAdapter databaseAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.showAllEvent);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        empTv = (TextView) rootView.findViewById(R.id.empTvEvent);
        createRecyclerView();


        return rootView;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case 4:
                int position4 = item.getGroupId();
                ListEvent listEvent4 = listAllEvents.get(position4);
                String id4 = listEvent4.getEventId();
                String result = databaseAdapter.deleteDataEvent(id4);
                removeAt(position4);
                Log.i("e1","e1");
                break;
            case 5:
                int position5 = item.getGroupId();
                ListEvent listEvent5 = listAllEvents.get(position5);
                String id5 = listEvent5.getEventId();
                Intent intent5;
                intent5 = new Intent(getContext(), EditEventActivity.class);
                intent5.putExtra("oldId",id5);
                getContext().startActivities(new Intent[]{intent5});
                getContext().stopService(intent5);
                break;
            case 6:
                int position6 = item.getGroupId();
                ListEvent listEvent6 = listAllEvents.get(position6);
                String id6 = listEvent6.getEventId();
                Intent intent6;
                intent6 = new Intent(getContext(), ViewEventActivity.class);
                intent6.putExtra("id", id6);
                getContext().startActivities(new Intent[]{intent6});
                break;
            case 7:
                int position7 = item.getGroupId();
                ListEvent listEvent7 = listAllEvents.get(position7);
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "I have event : "+listEvent7.getEventTitle() + " , " +listEvent7.getEventLocation();
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, listEvent7.getEventTitle());
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));

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
        if (listAllEvents.size()>0){
            empTv.setVisibility(View.INVISIBLE);
        }
        else {
            empTv.setVisibility(View.VISIBLE);
        }
        adapter = new RecyclerEventAdapter(listAllEvents,getContext(),"event");
        recyclerView.setAdapter(adapter);
    }
}

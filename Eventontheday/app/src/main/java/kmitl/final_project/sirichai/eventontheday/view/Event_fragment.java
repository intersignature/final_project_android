package kmitl.final_project.sirichai.eventontheday.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.share.ShareApi;
import com.facebook.share.model.ShareLinkContent;

import java.util.ArrayList;
import java.util.List;

import kmitl.final_project.sirichai.eventontheday.R;
import kmitl.final_project.sirichai.eventontheday.controller.DatabaseAdapter;
import kmitl.final_project.sirichai.eventontheday.model.EventInfo;
import kmitl.final_project.sirichai.eventontheday.model.ListEvent;
import kmitl.final_project.sirichai.eventontheday.controller.RecyclerEventAdapter;

/**
 * Created by atomiz on 6/11/2560.
 */

public class Event_fragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListEvent> listAllEvents = new ArrayList<>();
    private TextView empTv;
    private DatabaseAdapter databaseAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event, container, false);

        initInstances(rootView);
        createRecyclerView();
        return rootView;
    }

    private void initInstances(View rootView) {
        databaseAdapter = new DatabaseAdapter(getContext());
        empTv = (TextView) rootView.findViewById(R.id.empTvEvent);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.showAllEvent);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 4:
                int position4 = item.getGroupId();
                ListEvent listEvent4 = listAllEvents.get(position4);
                String id4 = listEvent4.getEventId().split(": ")[1];
                String result = databaseAdapter.deleteDataEvent(id4);
                removeAt(position4);
                break;
            case 5:
                int position5 = item.getGroupId();
                ListEvent listEvent5 = listAllEvents.get(position5);
                String id5 = listEvent5.getEventId().split(": ")[1];
                Intent intent5;
                intent5 = new Intent(getContext(), EditEventActivity.class);
                intent5.putExtra("oldId", id5);
                getContext().startActivities(new Intent[]{intent5});
                getContext().stopService(intent5);
                break;
            case 6:
                int position6 = item.getGroupId();
                ListEvent listEvent6 = listAllEvents.get(position6);
                String id6 = listEvent6.getEventId().split(": ")[1];
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

    private void removeAt(int position) {
        listAllEvents.remove(position);
        if (listAllEvents.size() > 0) {
            empTv.setVisibility(View.INVISIBLE);
        } else {
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
        if (getView() != null) {
            createRecyclerView();
            adapter.notifyDataSetChanged();
        }
    }

    private void createRecyclerView() {
        List<EventInfo> datas = databaseAdapter.getDataEvent();
        listAllEvents = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            ListEvent listEvent = new ListEvent(
                    "Title: " + datas.get(i).getTitle(),
                    "Date: " + datas.get(i).getStart_date(),
                    "Location: " + datas.get(i).getLocation().split(" : ")[0],
                    "id: " + datas.get(i).getId()
            );
            listAllEvents.add(listEvent);
        }
        if (listAllEvents.size() > 0) {
            empTv.setVisibility(View.INVISIBLE);
        } else {
            empTv.setVisibility(View.VISIBLE);
        }
        adapter = new RecyclerEventAdapter(listAllEvents, getContext(), "event");
        recyclerView.setAdapter(adapter);
    }
}

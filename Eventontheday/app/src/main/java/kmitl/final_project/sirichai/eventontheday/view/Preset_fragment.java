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

import java.util.ArrayList;
import java.util.List;

import kmitl.final_project.sirichai.eventontheday.R;
import kmitl.final_project.sirichai.eventontheday.model.DatabaseAdapter;
import kmitl.final_project.sirichai.eventontheday.model.ListPreset;
import kmitl.final_project.sirichai.eventontheday.model.RecyclerPresetAdapter;

/**
 * Created by atomiz on 6/11/2560.
 */

public class Preset_fragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<ListPreset> listAllPresets = new ArrayList<>();
    TextView empTv;
    private DatabaseAdapter databaseAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_preset, container, false);
        recyclerView = rootView.findViewById(R.id.showAllPreset);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        empTv = rootView.findViewById(R.id.empTvPreset);
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
    public boolean onContextItemSelected(MenuItem item) {
        int position = item.getGroupId();
        ListPreset listPreset = listAllPresets.get(position);
        String id = listPreset.getPresetId();
        Intent intent;
        switch (item.getItemId()){
            case 8:
                String result = databaseAdapter.deleteDataPreset(id);
                removeAt(position);
                break;
            case 9:
                intent = new Intent(getContext(), EditPresetActivity.class);
                intent.putExtra("oldIdPreset",id);
                getContext().startActivities(new Intent[]{intent});
                getContext().stopService(intent);
                break;
            case 10:
                intent = new Intent(getContext(), ViewPresetActivity.class);
                intent.putExtra("idPreset", listPreset.getPresetId());
                getContext().startActivities(new Intent[]{intent});
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void removeAt(int position) {
        listAllPresets.remove(position);
        if (listAllPresets.size()>0){
            empTv.setVisibility(View.INVISIBLE);
        }
        else {
            empTv.setVisibility(View.VISIBLE);
        }
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, listAllPresets.size());
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
        List<List> datas = databaseAdapter.getDataPreset();
        listAllPresets = new ArrayList<>();
        for (int i=0; i<datas.size();i++){
            List<String> eachPreset = datas.get(i);
            ListPreset listPreset = new ListPreset(
                    "Title: "+ eachPreset.get(0),
                    "Location: "+ eachPreset.get(1),
                    "Detail: "+eachPreset.get(2),eachPreset.get(3)
            );
            listAllPresets.add(listPreset);
        }
        Log.i("presetFrag", listAllPresets.toString());
        if (listAllPresets.size()>0){
            empTv.setVisibility(View.INVISIBLE);
        }
        else {
            empTv.setVisibility(View.VISIBLE);
        }
        adapter = new RecyclerPresetAdapter(listAllPresets,getContext());
        recyclerView.setAdapter(adapter);
    }
}

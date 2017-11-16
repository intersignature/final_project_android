package kmitl.final_project.sirichai.eventontheday.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import kmitl.final_project.sirichai.eventontheday.model.ListPreset;
import kmitl.final_project.sirichai.eventontheday.model.RecyclerEventAdapter;
import kmitl.final_project.sirichai.eventontheday.model.RecyclerPresetAdapter;

/**
 * Created by atomiz on 6/11/2560.
 */

public class Preset_fragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<ListPreset> listAllPresets = new ArrayList<>();
    private DatabaseAdapter databaseAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_preset, container, false);
        recyclerView = rootView.findViewById(R.id.showAllPreset);
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
        adapter = new RecyclerPresetAdapter(listAllPresets,getContext());
        recyclerView.setAdapter(adapter);
    }
}

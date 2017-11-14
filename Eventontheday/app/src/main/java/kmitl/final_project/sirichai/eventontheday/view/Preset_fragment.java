package kmitl.final_project.sirichai.eventontheday.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kmitl.final_project.sirichai.eventontheday.R;

/**
 * Created by atomiz on 6/11/2560.
 */

public class Preset_fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_preset, container, false);
        return rootView;
    }
}

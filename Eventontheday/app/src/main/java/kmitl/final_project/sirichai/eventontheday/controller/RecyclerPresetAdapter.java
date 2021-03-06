package kmitl.final_project.sirichai.eventontheday.controller;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kmitl.final_project.sirichai.eventontheday.R;
import kmitl.final_project.sirichai.eventontheday.model.ListPreset;

/**
 * Created by atomiz on 16/11/2560.
 */

public class RecyclerPresetAdapter extends RecyclerView.Adapter<RecyclerPresetAdapter.ViewHolder> {
    private List<ListPreset> listAllPresets;
    private Context context;
    private DatabaseAdapter databaseAdapter;

    public RecyclerPresetAdapter(List<ListPreset> listAllPresets, Context context) {
        this.listAllPresets = listAllPresets;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView presetTitle;
        public TextView presetDetail;
        public TextView presetLocation;
        public TextView presetId;
        public ConstraintLayout infoLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            presetTitle = (TextView) itemView.findViewById(R.id.eventTitle);
            presetDetail = (TextView) itemView.findViewById(R.id.eventDate);
            presetLocation = (TextView) itemView.findViewById(R.id.eventLocation);
            presetId = (TextView) itemView.findViewById(R.id.eventId);
            infoLayout = (ConstraintLayout) itemView.findViewById(R.id.infoLayout);
        }
    }

    @Override
    public RecyclerPresetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_event, parent, false);
        databaseAdapter = new DatabaseAdapter(parent.getContext());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerPresetAdapter.ViewHolder holder, final int position) {
        final ListPreset listPreset = listAllPresets.get(position);

        holder.presetTitle.setText(listPreset.getPresetTitle());
        holder.presetDetail.setText(listPreset.getPresetDetail());
        holder.presetLocation.setText(listPreset.getPresetLocation());
        holder.presetId.setText(listPreset.getPresetId());
        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(holder.getAdapterPosition(), 8, 0, "DELETE");
                menu.add(holder.getAdapterPosition(), 9, 0, "UPDATE");
                menu.add(holder.getAdapterPosition(), 10, 0, "USE THIS PRESET");
            }
        });
    }

    @Override
    public int getItemCount() {
        return listAllPresets.size();
    }


}

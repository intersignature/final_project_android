package kmitl.final_project.sirichai.eventontheday.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kmitl.final_project.sirichai.eventontheday.R;

/**
 * Created by atomiz on 7/11/2560.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    private List<ListEvent> listEvents;
    private Context context;

    public RecyclerAdapter(List<ListEvent> listEvents, Context context) {
        this.listEvents = listEvents;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView eventTitle;
        public TextView eventDate;
        public TextView eventDescription;
        public ViewHolder(View itemView) {
            super(itemView);
            eventTitle = itemView.findViewById(R.id.eventTitle);
            eventDate = itemView.findViewById(R.id.eventDate);
            eventDescription = itemView.findViewById(R.id.eventDescription);
        }
    }
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        ListEvent listEvent = listEvents.get(position);

        holder.eventTitle.setText(listEvent.getEventTitle());
        holder.eventDate.setText(listEvent.getEventDate());
        holder.eventDescription.setText(listEvent.getEventDescription());
    }

    @Override
    public int getItemCount() {
        return listEvents.size();
    }
}

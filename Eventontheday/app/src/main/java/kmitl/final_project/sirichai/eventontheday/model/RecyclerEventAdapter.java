package kmitl.final_project.sirichai.eventontheday.model;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import kmitl.final_project.sirichai.eventontheday.R;
import kmitl.final_project.sirichai.eventontheday.view.ViewEventActivity;
import kmitl.final_project.sirichai.eventontheday.view.EditEventActivity;

/**
 * Created by atomiz on 7/11/2560.
 */

public class RecyclerEventAdapter extends RecyclerView.Adapter<RecyclerEventAdapter.ViewHolder>{

    private List<ListEvent> listAllEvents;
    private Context context;
    private DatabaseAdapter databaseAdapter;

    public RecyclerEventAdapter(List<ListEvent> listAllEvents, Context context) {
        this.listAllEvents = listAllEvents;
        this.context = context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView eventTitle;
        public TextView eventDate;
        public TextView eventLocation;
        public Button btnDelete;
        public Button btnUpdate;
        public TextView eventId;
        public ConstraintLayout infoLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            eventTitle = itemView.findViewById(R.id.eventTitle);
            eventDate = itemView.findViewById(R.id.eventDate);
            eventLocation = itemView.findViewById(R.id.eventLocation);
            eventId = itemView.findViewById(R.id.eventId);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            infoLayout = itemView.findViewById(R.id.infoLayout);
        }

    }
    @Override
    public RecyclerEventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_event, parent, false);
        databaseAdapter = new DatabaseAdapter(parent.getContext());
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final RecyclerEventAdapter.ViewHolder holder, final int position) {
        final ListEvent listEvent = listAllEvents.get(position);

        holder.eventTitle.setText(listEvent.getEventTitle());
        holder.eventDate.setText(listEvent.getEventDate());
        holder.eventLocation.setText(listEvent.getEventLocation());
        holder.eventId.setText(listEvent.getEventId());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = listEvent.getEventId();
                String result = databaseAdapter.deleteDataEvent(id);
                removeAt(position);
            }
        });

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = listEvent.getEventId();
                Intent intent;
                intent =  new Intent(context, EditEventActivity.class);
                intent.putExtra("oldId",id);
                context.startActivities(new Intent[]{intent});
                context.stopService(intent);

            }
        });

        holder.infoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("RecyclerAdapeter", listEvent.getEventId());
                Intent intent;
                intent = new Intent(context, ViewEventActivity.class);
                intent.putExtra("id", listEvent.getEventId());
                context.startActivities(new Intent[]{intent});
            }
        });

    }

    @Override
    public int getItemCount() {
        return listAllEvents.size();
    }
    public void removeAt(int position) {
        listAllEvents.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listAllEvents.size());
    }
}

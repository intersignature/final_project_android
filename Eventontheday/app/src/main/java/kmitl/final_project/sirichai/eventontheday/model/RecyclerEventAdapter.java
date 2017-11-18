package kmitl.final_project.sirichai.eventontheday.model;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import kmitl.final_project.sirichai.eventontheday.R;

/**
 * Created by atomiz on 7/11/2560.
 */

public class RecyclerEventAdapter extends RecyclerView.Adapter<RecyclerEventAdapter.ViewHolder>{

    private List<ListEvent> listAllEvents;
    private Context context;
    private DatabaseAdapter databaseAdapter;
    private String page;

    public RecyclerEventAdapter(List<ListEvent> listAllEvents, Context context, String page) {
        this.listAllEvents = listAllEvents;
        this.context = context;
        this.page = page;
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
        if (page.equals("Cal")) {
            holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                    menu.add(holder.getAdapterPosition(), 0, 0, "DELETE");
                    menu.add(holder.getAdapterPosition(), 1, 0, "UPDATE");
                    menu.add(holder.getAdapterPosition(), 2, 0, "VIEW");
                    menu.add(holder.getAdapterPosition(), 3, 0, "SHARE");
                }
            });
        }
        else {
            holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                    menu.add(holder.getAdapterPosition(), 4, 0, "DELETE");
                    menu.add(holder.getAdapterPosition(), 5, 0, "UPDATE");
                    menu.add(holder.getAdapterPosition(), 6, 0, "VIEW");
                    menu.add(holder.getAdapterPosition(), 7, 0, "SHARE");
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return listAllEvents.size();
    }
}

package kmitl.final_project.sirichai.eventontheday.controller;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import kmitl.final_project.sirichai.eventontheday.R;
import kmitl.final_project.sirichai.eventontheday.model.ListEvent;

/**
 * Created by atomiz on 7/11/2560.
 */

public class RecyclerEventAdapter extends RecyclerView.Adapter<RecyclerEventAdapter.ViewHolder> {

    private List<ListEvent> listAllEvents;
    private Context context;
    private DatabaseAdapter databaseAdapter;
    private String page;

    public RecyclerEventAdapter(List<ListEvent> listAllEvents, Context context, String page) {
        this.listAllEvents = listAllEvents;
        this.context = context;
        this.page = page;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView eventTitle;
        public TextView eventDate;
        public TextView eventLocation;
        public TextView eventId;
        public ConstraintLayout infoLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            eventTitle = (TextView) itemView.findViewById(R.id.eventTitle);
            eventDate = (TextView) itemView.findViewById(R.id.eventDate);
            eventLocation = (TextView) itemView.findViewById(R.id.eventLocation);
            eventId = (TextView) itemView.findViewById(R.id.eventId);
            infoLayout = (ConstraintLayout) itemView.findViewById(R.id.infoLayout);
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
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        try {
            Date dateFromDb = sdf1.parse(listEvent.getEventDate().split(": ")[1]);
            Date currentDate = sdf1.parse(sdf1.format(date));
            if (dateFromDb.compareTo(currentDate) >= 0) {
                holder.eventTitle.setTextColor(Color.rgb(80,223,48));
                holder.eventDate.setTextColor(Color.rgb(80,223,48));
                holder.eventLocation.setTextColor(Color.rgb(80,223,48));
            } else if (dateFromDb.compareTo(currentDate) < 0) {
                holder.eventTitle.setTextColor(Color.RED);
                holder.eventDate.setTextColor(Color.RED);
                holder.eventLocation.setTextColor(Color.RED);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

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
        } else if (page.equals("event")) {
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

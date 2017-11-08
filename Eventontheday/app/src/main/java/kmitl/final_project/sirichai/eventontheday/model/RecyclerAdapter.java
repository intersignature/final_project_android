package kmitl.final_project.sirichai.eventontheday.model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import kmitl.final_project.sirichai.eventontheday.MainActivity;
import kmitl.final_project.sirichai.eventontheday.MessageForDev;
import kmitl.final_project.sirichai.eventontheday.R;
import kmitl.final_project.sirichai.eventontheday.view.AddEvent;
import kmitl.final_project.sirichai.eventontheday.view.EditEvent;
import kmitl.final_project.sirichai.eventontheday.view.Event_fragment;

/**
 * Created by atomiz on 7/11/2560.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    private List<ListEvent> listEvents;
    private Context context;
    private DatabaseAdapter databaseAdapter;
    private Event_fragment event_fragment;
    public RecyclerAdapter(List<ListEvent> listEvents, Context context) {
        this.listEvents = listEvents;
        this.context = context;

    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView eventTitle;
        public TextView eventDate;
        public TextView eventLocation;
        public Button btnDelete;
        public Button btnUpdate;
        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            eventTitle = itemView.findViewById(R.id.eventTitle);
            eventDate = itemView.findViewById(R.id.eventDate);
            eventLocation = itemView.findViewById(R.id.eventLocation);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
        }

    }
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_event, parent, false);
        databaseAdapter = new DatabaseAdapter(parent.getContext());
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, final int position) {
        final ListEvent listEvent = listEvents.get(position);

        holder.eventTitle.setText(listEvent.getEventTitle());
        holder.eventDate.setText(listEvent.getEventDate());
        holder.eventLocation.setText(listEvent.getEventLocation());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageForDev  messageForDev = new MessageForDev();
                String title = listEvent.getEventTitle().split(": ")[1];
                int cnt = databaseAdapter.delete(title);
                messageForDev.Log("POS : "+ cnt);
                Intent intent;
                intent =  new Intent(context, MainActivity.class);
                context.startActivities(new Intent[]{intent});
                context.stopService(intent);
            }
        });
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = listEvent.getEventTitle().split(": ")[1];
                OldTitle oldTitle = new OldTitle();
                oldTitle.setOldTitle(title);
                Intent intent;
                intent =  new Intent(context, EditEvent.class);
                intent.putExtra("oldTitle",title);
                context.startActivities(new Intent[]{intent});
                context.stopService(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listEvents.size();
    }
}

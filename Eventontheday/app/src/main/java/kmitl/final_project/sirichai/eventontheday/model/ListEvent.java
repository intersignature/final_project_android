package kmitl.final_project.sirichai.eventontheday.model;

/**
 * Created by atomiz on 7/11/2560.
 */

public class ListEvent {
    private String eventTitle;
    private String eventDate;
    private String eventDescription;

    public ListEvent(String eventTitle, String eventDate, String eventDescription) {
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
        this.eventDescription = eventDescription;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }
}

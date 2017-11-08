package kmitl.final_project.sirichai.eventontheday.model;

/**
 * Created by atomiz on 7/11/2560.
 */

public class ListEvent {
    private String eventTitle;
    private String eventDate;
    private String eventLocation;

    public ListEvent(String eventTitle, String eventDate, String eventLocation) {
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
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

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

}

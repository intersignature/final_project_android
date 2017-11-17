package kmitl.final_project.sirichai.eventontheday.model;

/**
 * Created by atomiz on 7/11/2560.
 */

public class ListEvent {
    private String eventTitle;
    private String eventDate;
    private String eventLocation;
    private String eventId;

//    @Override
//    public String toString() {
//        return String.format("%s %s", eventDate.split(": ")[1], eventId);
//    }

    public ListEvent(String eventTitle, String eventDate, String eventLocation, String eventId) {
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.eventId = eventId;
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

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}

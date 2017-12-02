package kmitl.final_project.sirichai.eventontheday;

import org.junit.Test;

import kmitl.final_project.sirichai.eventontheday.model.ListEvent;

import static junit.framework.Assert.assertEquals;

/**
 * Created by atomiz on 3/12/2560.
 */

public class ListEventTest {

    @Test
    public void getterTest(){
        String eventTitle = "eventTitle";
        String eventDate = "eventDate";
        String eventLocation = "eventLocation";
        String eventId = "eventId";
        ListEvent listEvent = new ListEvent(eventTitle, eventDate, eventLocation, eventId);
        assertEquals("eventTitle", listEvent.getEventTitle());
        assertEquals("eventDate", listEvent.getEventDate());
        assertEquals("eventLocation", listEvent.getEventLocation());
        assertEquals("eventId", listEvent.getEventId());
    }

    @Test
    public void setterTest(){
        String eventTitle = "eventTitle";
        String eventDate = "eventDate";
        String eventLocation = "eventLocation";
        String eventId = "eventId";
        ListEvent listEvent = new ListEvent(eventTitle, eventDate, eventLocation, eventId);
        listEvent.setEventTitle("eventTitle2");
        listEvent.setEventDate("eventDate2");
        listEvent.setEventLocation("eventLocation2");
        listEvent.setEventId("eventId2");
        assertEquals("eventTitle2", listEvent.getEventTitle());
        assertEquals("eventDate2", listEvent.getEventDate());
        assertEquals("eventLocation2", listEvent.getEventLocation());
        assertEquals("eventId2", listEvent.getEventId());
    }
}

package kmitl.final_project.sirichai.eventontheday;

import org.junit.Test;

import kmitl.final_project.sirichai.eventontheday.model.EventInfo;

import static junit.framework.Assert.assertEquals;

/**
 * Created by atomiz on 3/12/2560.
 */

public class EventInfoTest {

    @Test
    public void getterTest(){
        String title = "title1";
        String location = "lcation1";
        String start_date = "start_date";
        String end_date = "end_date";
        String start_time = "start_time";
        String end_time = "end_time";
        String alert_date = "alert_date";
        String alert_time = "alert_time";
        String detail = "detail";
        String id = "1";
        EventInfo eventInfo = new EventInfo(title, location, start_date, end_date, start_time, end_time, alert_date, alert_time,
                detail, id);
        assertEquals("title1", eventInfo.getTitle());
        assertEquals("lcation1", eventInfo.getLocation());
        assertEquals("start_date", eventInfo.getStart_date());
        assertEquals("end_date", eventInfo.getEnd_date());
        assertEquals("start_time", eventInfo.getStart_time());
        assertEquals("end_time", eventInfo.getEnd_time());
        assertEquals("alert_date", eventInfo.getAlert_date());
        assertEquals("alert_time", eventInfo.getAlert_time());
        assertEquals("detail", eventInfo.getDetail());
        assertEquals("1", eventInfo.getId());
    }

    @Test
    public void setterTest(){
        String title = "title1";
        String location = "lcation1";
        String start_date = "start_date";
        String end_date = "end_date";
        String start_time = "start_time";
        String end_time = "end_time";
        String alert_date = "alert_date";
        String alert_time = "alert_time";
        String detail = "detail";
        String id = "1";
        EventInfo eventInfo = new EventInfo(title, location, start_date, end_date, start_time, end_time, alert_date, alert_time,
                detail, id);
        eventInfo.setTitle("title2");
        eventInfo.setLocation("location2");
        eventInfo.setStart_date("start_date2");
        eventInfo.setEnd_date("end_date2");
        eventInfo.setStart_time("start_time2");
        eventInfo.setEnd_time("end_time2");
        eventInfo.setAlert_date("alert_date2");
        eventInfo.setAlert_time("alert_time2");
        eventInfo.setDetail("detail2");
        eventInfo.setId("2");
        assertEquals("title2", eventInfo.getTitle());
        assertEquals("location2", eventInfo.getLocation());
        assertEquals("start_date2", eventInfo.getStart_date());
        assertEquals("end_date2", eventInfo.getEnd_date());
        assertEquals("start_time2", eventInfo.getStart_time());
        assertEquals("end_time2", eventInfo.getEnd_time());
        assertEquals("alert_date2", eventInfo.getAlert_date());
        assertEquals("alert_time2", eventInfo.getAlert_time());
        assertEquals("detail2", eventInfo.getDetail());
        assertEquals("2", eventInfo.getId());

    }
}

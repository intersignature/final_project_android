package kmitl.final_project.sirichai.eventontheday;

import org.junit.Test;

import kmitl.final_project.sirichai.eventontheday.model.ListDate;

import static junit.framework.Assert.assertEquals;

/**
 * Created by atomiz on 3/12/2560.
 */

public class ListDateTest {

    @Test
    public void getterTest(){
        String id = "1";
        String date = "2017/12/02";
        ListDate listDate = new ListDate(id, date);
        assertEquals("1", listDate.getId());
        assertEquals("2017/12/02", listDate.getDate());
    }

    @Test
    public void setterTest(){
        String id = "1";
        String date = "2017/12/02";
        ListDate listDate = new ListDate(id, date);
        listDate.setId("2");
        listDate.setDate("2019/10/03");
        assertEquals("2", listDate.getId());
        assertEquals("2019/10/03", listDate.getDate());
    }
}

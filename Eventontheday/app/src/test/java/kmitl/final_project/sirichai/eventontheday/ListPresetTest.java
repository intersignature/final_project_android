package kmitl.final_project.sirichai.eventontheday;

import org.junit.Test;

import kmitl.final_project.sirichai.eventontheday.model.ListPreset;

import static junit.framework.Assert.assertEquals;

/**
 * Created by atomiz on 3/12/2560.
 */

public class ListPresetTest {

    @Test
    public void getterTest(){
        String presetTitle = "presetTitle";
        String presetLocation = "presetLocation";
        String presetDetail = "presetDetail";
        String presettId = "presetId";
        ListPreset listPreset = new ListPreset(presetTitle, presetLocation, presetDetail, presettId);
        assertEquals("presetTitle", listPreset.getPresetTitle());
        assertEquals("presetLocation", listPreset.getPresetLocation());
        assertEquals("presetDetail", listPreset.getPresetDetail());
        assertEquals("presetId", listPreset.getPresetId());
    }

    @Test
    public void setterTest(){
        String presetTitle = "presetTitle";
        String presetLocation = "presetLocation";
        String presetDetail = "presetDetail";
        String presettId = "presetId";
        ListPreset listPreset = new ListPreset(presetTitle, presetLocation, presetDetail, presettId);
        listPreset.setPresetTitle("presetTitle2");
        listPreset.setPresetLocation("presetLocation2");
        listPreset.setPresetDetail("presetDetail2");
        listPreset.setPresetId("presetId2");
        assertEquals("presetTitle2", listPreset.getPresetTitle());
        assertEquals("presetLocation2", listPreset.getPresetLocation());
        assertEquals("presetDetail2", listPreset.getPresetDetail());
        assertEquals("presetId2", listPreset.getPresetId());
    }
}

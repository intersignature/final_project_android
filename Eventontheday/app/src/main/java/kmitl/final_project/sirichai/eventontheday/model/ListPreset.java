package kmitl.final_project.sirichai.eventontheday.model;

/**
 * Created by atomiz on 16/11/2560.
 */

public class ListPreset {
    private String presetTitle;
    private String presetLocation;
    private String presetDetail;
    private String presetId;

    public ListPreset(String presetTitle, String presetLocation, String presetDetail, String presetId) {
        this.presetTitle = presetTitle;
        this.presetLocation = presetLocation;
        this.presetDetail = presetDetail;
        this.presetId = presetId;
    }

    public String getPresetTitle() {
        return presetTitle;
    }

    public void setPresetTitle(String presetTitle) {
        this.presetTitle = presetTitle;
    }

    public String getPresetLocation() {
        return presetLocation;
    }

    public void setPresetLocation(String presetLocation) {
        this.presetLocation = presetLocation;
    }

    public String getPresetDetail() {
        return presetDetail;
    }

    public void setPresetDetail(String presetDetail) {
        this.presetDetail = presetDetail;
    }

    public String getPresetId() {
        return presetId;
    }

    public void setPresetId(String presetId) {
        this.presetId = presetId;
    }
}

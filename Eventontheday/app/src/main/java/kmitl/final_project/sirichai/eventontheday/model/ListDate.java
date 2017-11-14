package kmitl.final_project.sirichai.eventontheday.model;

/**
 * Created by atomiz on 11/11/2560.
 */

public class ListDate {
    private String id;
    private String date;

    @Override
    public String toString() {
        return String.format("%s %s", id, date);
    }

    public ListDate(String id, String date) {
        this.id = id;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

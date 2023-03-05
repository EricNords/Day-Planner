package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an assignment with a name, time (in 24 hour time, 100 being 1am, 1230 being 12:30pm, 2400 being 12am),
// and date (mm/dd/yyyy)
public class Assignment implements Writable {
    private final String name;
    private final String date;
    private final int time;

    // REQUIRES: assignmentName have a non-zero length
    // assignmentTime be greater than 0 and less than or equal to 2400
    // EFFECTS: name of the assignment is set to assignmentName;
    // date of the assignment is set to assignmentDate;
    // time of the assignment is set to assignmentTime;
    public Assignment(String assignmentName, String assignmentDate, int assignmentTime) {

        this.name = assignmentName;
        this.date = assignmentDate;
        this.time = assignmentTime;
    }

    public String getAssignmentName() {
        return name;
    }

    public String getAssignmentDate() {
        return date;
    }

    public double getAssignmentTime() {
        return time;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("date", date);
        json.put("time", time);

        return json;
    }

}

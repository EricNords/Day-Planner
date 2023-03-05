package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Objects;

// Represents a commitment with a name, time (in 24 hour time, 100 being 1am, 1230 being 12:30pm, 2400 being 12am),
// date, location, and list of assignments
public class Commitment implements Writable {
    private final String name;
    private final int time;
    private final String location;
    private final String date;
    private final ArrayList<Assignment> assignments;

    // REQUIRES: commitmentName have a non-zero length
    // commitmentTime be greater than 0 and less than or equal to 2400
    // commitmentDate have a non-zero length
    // EFFECTS: name of the assignment is set to assignmentName;
    // date of the assignment is set to assignmentDate;
    // time of the assignment is set to assignmentTime;
    public Commitment(String commitmentName, int commitmentTime, String commitmentLocation,
                      String commitmentDate, ArrayList<Assignment> listOfAssignments) {

        this.name = commitmentName;
        this.time = commitmentTime;
        this.location = commitmentLocation;
        this.date = commitmentDate;
        this.assignments = listOfAssignments;

    }

    //MODIFIES: this
    //EFFECTS: new assignment is added to assignments
    public void addAssignment(String assignmentName, String assignmentDate, int assignmentTime) {
        Assignment assignment = new Assignment(assignmentName, assignmentDate, assignmentTime);
        assignments.add(assignment);
        EventLog.getInstance().logEvent(new Event("Added assignment to " + this.name));
    }

    //MODIFIES: this
    //EFFECTS: assignment is removed from assignments
    public void removeAssignment(String name) {
        int size = assignments.size();

        if (size != 0) {
            for (int i = 0; i < size; i++) {
                if (Objects.equals(name, assignments.get(i).getAssignmentName())) {
                    assignments.remove(i);
                    i = size;
                    EventLog.getInstance().logEvent(new Event("Removed assignment "
                            + name + " from " + this.name));
                }
            }
        }
    }

    public ArrayList<Assignment> getCommitmentAssignments() {
        EventLog.getInstance().logEvent(new Event("Collected assignments for "
                + this.name + " to be displayed"));
        return assignments;

    }

    public String getCommitmentName() {
        return name;
    }

    public int getCommitmentTime() {
        return time;
    }

    public String getCommitmentLocation() {
        return location;
    }

    public String getCommitmentDate() {
        return date;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("time", time);
        json.put("location", location);
        json.put("date", date);
        json.put("assignments", assignmentsToJson());
        return json;
    }

    // EFFECTS: returns assignments in this commitment as a JSON array
    private JSONArray assignmentsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Assignment a : assignments) {
            jsonArray.put(a.toJson());
        }

        return jsonArray;
    }
}

package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Objects;

//Represents a day planner with a list of commitments
public class DayPlanner implements Writable {
    private final ArrayList<Commitment> commitments;

    //EFFECTS: commitments is set to commitments
    public DayPlanner(ArrayList<Commitment> commitments) {
        this.commitments = commitments;
    }

    //MODIFIES: this
    //EFFECTS: commitment is added to commitments
    public void addCommitment(String commitmentName, int commitmentTime, String commitmentLocation,
                              String commitmentDate, ArrayList<Assignment> commitmentAssignments) {
        Commitment commitment = new Commitment(commitmentName, commitmentTime, commitmentLocation,
                commitmentDate, commitmentAssignments);
        commitments.add(commitment);
        EventLog.getInstance().logEvent(new Event("Added commitment: " + commitmentName + " to day planner"));
    }

    //MODIFIES: this
    //EFFECTS: commitment with given name is removed from commitments
    public void removeCommitment(String name) {
        int size = commitments.size();

        if (size != 0) {
            for (int i = 0; i < size; i++) {
                if (Objects.equals(name, commitments.get(i).getCommitmentName())) {
                    commitments.remove(i);
                    i = size;
                    EventLog.getInstance().logEvent(new Event("Removed commitment: "
                            + name + " from day planner"));
                }
            }
        }
    }

    public ArrayList<Commitment> getCommitments() {
        EventLog.getInstance().logEvent(new Event("Collected commitments for day planner to be displayed"));
        return commitments;
    }

    public ArrayList<Commitment> getCommitments2() {
        return commitments;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("commitments", commitmentsToJson());
        return json;
    }

    // EFFECTS: returns commitments in this day planner as a JSON array
    private JSONArray commitmentsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Commitment c : commitments) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}

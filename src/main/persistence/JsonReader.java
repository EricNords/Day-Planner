package persistence;

import model.Assignment;
import model.Commitment;
import model.DayPlanner;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.*;

//NOTE: JsonReader structure is largely based off of JsonSerializationDemo

// Represents a reader that reads a day planner from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads day planner from file and returns it;
    // throws IOException if an error occurs reading data from file
    public DayPlanner read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDayPlanner(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses day planner from JSON object and returns it
    private DayPlanner parseDayPlanner(JSONObject jsonObject) {
        ArrayList<Commitment> c = new ArrayList<>();
        DayPlanner dp = new DayPlanner(c);
        addCommitments(dp, jsonObject);
        return dp;
    }

    // MODIFIES: dp
    // EFFECTS: parses commitments from JSON object and adds them to day planner
    private void addCommitments(DayPlanner dp, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("commitments");
        for (Object json : jsonArray) {
            JSONObject nextCommitment = (JSONObject) json;
            addComm(dp, nextCommitment);
        }
    }

    // MODIFIES: dp
    // EFFECTS: parses commitment from JSON object and adds it to day planner
    private void addComm(DayPlanner dp, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int time = jsonObject.getInt("time");
        String location = jsonObject.getString("location");
        String date = jsonObject.getString("date");

        ArrayList<Assignment> a = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("assignments");

        for (Object json: jsonArray) {
            JSONObject nextAssignment = (JSONObject) json;
            addAssi(a, nextAssignment);
        }

        dp.addCommitment(name, time, location, date, a);
    }

    // MODIFIES: a
    // EFFECTS: parses assignment from JSON object and adds it a list of assignments
    private void addAssi(ArrayList<Assignment> a, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String date = jsonObject.getString("date");
        int time = jsonObject.getInt("time");
        Assignment assi = new Assignment(name, date, time);

        a.add(assi);
    }
}

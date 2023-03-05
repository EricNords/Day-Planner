package persistence;

import org.json.JSONObject;

//based off of same interface from JsonSerializationDemo

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

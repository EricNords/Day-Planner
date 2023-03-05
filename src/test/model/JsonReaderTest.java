package model;

import persistence.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//NOTE: JsonReaderTest structure is largely based off of JsonSerializationDemo

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            DayPlanner dp = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDayPlanner.json");
        try {
            DayPlanner dp = reader.read();
            assertEquals(0, dp.getCommitments().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDayPlanner.json");
        ArrayList<Assignment> assignments1 = new ArrayList<>();
        ArrayList<Assignment> assignments2 = new ArrayList<>();
        Assignment a1 = new Assignment("Buy some wine", "10/23/2022", 1500);
        assignments2.add(a1);
        try {
            DayPlanner dp = reader.read();
            List<Commitment> commitments = dp.getCommitments();
            assertEquals(2, commitments.size());
            checkCommitment("CPSC 210", 1100, "CIRS", "10/21/2022", assignments1,
                    commitments.get(0));
            checkCommitment("Dinner with the fam", 1800, "Casa", "10/23/2022", assignments2,
                    commitments.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

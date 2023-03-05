package model;

import model.*;
import persistence.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//NOTE: JsonReaderTest structure is largely based off of JsonSerializationDemo

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ArrayList<Commitment> c = new ArrayList<>();
            DayPlanner dp = new DayPlanner(c);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            ArrayList<Commitment> c = new ArrayList<>();
            DayPlanner dp = new DayPlanner(c);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDayPlanner.json");
            writer.open();
            writer.write(dp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDayPlanner.json");
            dp = reader.read();
            assertEquals(0, dp.getCommitments().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            ArrayList<Commitment> c = new ArrayList<>();
            ArrayList<Assignment> a = new ArrayList<>();
            DayPlanner dp = new DayPlanner(c);
            Assignment test = new Assignment("testa", "testb", 1);
            a.add(test);
            dp.addCommitment("CPSC 210", 1100, "CIRS",
                    "10/21/2022", a);
            dp.addCommitment("Dinner with the fam", 1800, "Casa",
                    "10/23/2022", a);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDayPlanner.json");
            writer.open();
            writer.write(dp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDayPlanner.json");
            dp = reader.read();
            List<Commitment> commitments = dp.getCommitments();
            assertEquals(2, commitments.size());
            checkCommitment("CPSC 210", 1100, "CIRS", "10/21/2022", a,
                    commitments.get(0));
            checkCommitment("Dinner with the fam", 1800, "Casa", "10/23/2022", a,
                    commitments.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

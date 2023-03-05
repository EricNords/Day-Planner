package model;

import model.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

//NOTE: JsonTest structure is largely based off of JsonSerializationDemo
public class JsonTest {
    protected void checkCommitment(String name, int time, String location, String date,
                                   ArrayList<Assignment> assignments, Commitment commitment) {

        assertEquals(name, commitment.getCommitmentName());
        assertEquals(time, commitment.getCommitmentTime());
        assertEquals(location, commitment.getCommitmentLocation());
        assertEquals(date, commitment.getCommitmentDate());

        for (int i = 0; i < assignments.size(); i++) {
            assertEquals(assignments.get(i).getAssignmentName(),
                    commitment.getCommitmentAssignments().get(i).getAssignmentName());
            assertEquals(assignments.get(i).getAssignmentDate(),
                    commitment.getCommitmentAssignments().get(i).getAssignmentDate());
            assertEquals(assignments.get(i).getAssignmentTime(),
                    commitment.getCommitmentAssignments().get(i).getAssignmentTime());
        }
    }
}
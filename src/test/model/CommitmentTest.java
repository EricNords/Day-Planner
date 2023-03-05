package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CommitmentTest {
    private Commitment testCommitment;
    private Assignment testAssignment1;
    private Assignment testAssignment2;
    private Assignment testAssignment3;


    @BeforeEach
    void runBefore(){
        testAssignment1 = new Assignment("CPSC Midterm 1",
                "10/13/2022", 1900);
        testAssignment2 = new Assignment("CPSC Midterm 2",
                "11/07/2022", 1800);
        testAssignment3 = new Assignment("P1", "10/14/2022", 2399);
        ArrayList<Assignment> assignmentArray = new ArrayList<>();
        assignmentArray.add(testAssignment1);
        assignmentArray.add(testAssignment3);

        testCommitment = new Commitment("CPSC 210", 1100, "CIRS - 1250",
                "08/13/2022", assignmentArray);
    }

    @Test
    void commitmentTest(){
        assertEquals("CPSC 210", testCommitment.getCommitmentName());
        assertEquals(1100, testCommitment.getCommitmentTime());
        assertEquals("CIRS - 1250", testCommitment.getCommitmentLocation());
        assertEquals("08/13/2022", testCommitment.getCommitmentDate());
        assertEquals(testAssignment1, testCommitment.getCommitmentAssignments().get(0));
        assertEquals(testAssignment3, testCommitment.getCommitmentAssignments().get(1));

    }

    @Test
    void addAssignmentTest(){
        testCommitment.addAssignment("CPSC Midterm 2", "11/07/2022", 1800);
        assertEquals(testAssignment2.getAssignmentDate(), testCommitment.getCommitmentAssignments().get(2).getAssignmentDate());
        assertEquals(testAssignment2.getAssignmentName(), testCommitment.getCommitmentAssignments().get(2).getAssignmentName());
        assertEquals(testAssignment2.getAssignmentTime(), testCommitment.getCommitmentAssignments().get(2).getAssignmentTime());

    }

    @Test
    void removeAssignmentWhenIsFirst(){
        testCommitment.removeAssignment("CPSC Midterm 1");
        assertEquals(1, testCommitment.getCommitmentAssignments().size());
        assertEquals(testAssignment3, testCommitment.getCommitmentAssignments().get(0));
    }

    @Test
    void removeAssignmentWhenIsLast(){
        testCommitment.removeAssignment("P1");
        assertEquals(1, testCommitment.getCommitmentAssignments().size());
        assertEquals(testAssignment1, testCommitment.getCommitmentAssignments().get(0));
    }

    @Test
    void removeAssignmentAll(){
        testCommitment.addAssignment("CPSC Midterm 2", "11/07/2022", 1800);
        testCommitment.removeAssignment("P1");
        assertEquals(2, testCommitment.getCommitmentAssignments().size());
        assertEquals(testAssignment1, testCommitment.getCommitmentAssignments().get(0));
        testCommitment.removeAssignment("CPSC Midterm 2");
        assertEquals(1, testCommitment.getCommitmentAssignments().size());
        assertEquals(testAssignment1, testCommitment.getCommitmentAssignments().get(0));
        testCommitment.removeAssignment("CPSC Midterm 1");
        assertEquals(0, testCommitment.getCommitmentAssignments().size());
        testCommitment.removeAssignment("None Left");
    }

}

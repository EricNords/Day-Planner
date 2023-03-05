package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DayPlannerTest {
    private DayPlanner testDayPlanner;
    private ArrayList<Assignment> assignmentArray;
    private Commitment testCommitment1;
    private Commitment testCommitment3;

    @BeforeEach
    void runBefore(){
        Assignment testAssignment1 = new Assignment("TEST", "01/01/2022", 1);
        assignmentArray = new ArrayList<>();
        assignmentArray.add(testAssignment1);

        testCommitment1 = new Commitment("CPSC 210", 1100, "CIRS - 1250",
                "10/13/2022", assignmentArray);
        testCommitment3 = new Commitment("UBC Rocket", 2000, "HENN",
                "10/13/2022", assignmentArray);
        ArrayList<Commitment> commitmentsArray = new ArrayList<>();
        commitmentsArray.add(testCommitment1);
        commitmentsArray.add(testCommitment3);

        testDayPlanner = new DayPlanner(commitmentsArray);
    }

    @Test
    void dayPlannerTest(){
        assertEquals(testCommitment1, testDayPlanner.getCommitments().get(0));
        assertEquals(testCommitment3, testDayPlanner.getCommitments().get(1));
    }

    @Test
    void addCommitmentTest(){
        testDayPlanner.addCommitment("Math 221", 1300, "LSK - 200",
                "10/13/2022", assignmentArray);
        assertEquals("Math 221",
                testDayPlanner.getCommitments().get(2).getCommitmentName());
        assertEquals(1300,
                testDayPlanner.getCommitments().get(2).getCommitmentTime());
        assertEquals("LSK - 200",
                testDayPlanner.getCommitments().get(2).getCommitmentLocation());
        assertEquals("10/13/2022",
                testDayPlanner.getCommitments().get(2).getCommitmentDate());
        assertEquals(assignmentArray,
                testDayPlanner.getCommitments().get(2).getCommitmentAssignments());
    }

    @Test
    void removeCommitmentWhenIsFirst(){
        testDayPlanner.removeCommitment("CPSC 210");
        assertEquals(1, testDayPlanner.getCommitments().size());
        assertEquals(testCommitment3, testDayPlanner.getCommitments().get(0));
    }

    @Test
    void removeCommitmentWhenIsLast(){
        testDayPlanner.removeCommitment("UBC Rocket");
        assertEquals(1, testDayPlanner.getCommitments().size());
        assertEquals(testCommitment1, testDayPlanner.getCommitments().get(0));
    }

    @Test
    void removeAssignmentAll(){
        testDayPlanner.removeCommitment("UBC Rocket");
        assertEquals(1, testDayPlanner.getCommitments().size());
        assertEquals(testCommitment1, testDayPlanner.getCommitments().get(0));
        testDayPlanner.removeCommitment("CPSC 210");
        assertEquals(0, testDayPlanner.getCommitments().size());
        testDayPlanner.removeCommitment("None Left");
    }
}

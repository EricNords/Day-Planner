package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoggingTest {

    private DayPlanner testDayPlanner;
    private DayPlanner testDayPlanner2;
    private ArrayList<Assignment> assignmentArray;
    private Commitment testCommitment1;
    private Assignment testAssignment1;

    @BeforeEach
    void runBefore(){
        Assignment testAssignment1 = new Assignment("TEST", "01/01/2022", 1);
        assignmentArray = new ArrayList<>();
        assignmentArray.add(testAssignment1);
        testCommitment1 = new Commitment("CPSC 210", 1100, "CIRS - 1250",
                "10/13/2022", assignmentArray);
        ArrayList<Commitment> commitmentsArray = new ArrayList<>();
        ArrayList<Commitment> commitmentsArray2 = new ArrayList<>();
        testDayPlanner = new DayPlanner(commitmentsArray);
        commitmentsArray2.add(testCommitment1);
        testDayPlanner2 = new DayPlanner(commitmentsArray2);
    }

    @Test
    void addCommitAndAssignment() {
        EventLog el = EventLog.getInstance();
        el.clear();

        testDayPlanner.addCommitment("CPSC 210", 1100, "CIRS - 1250",
                "10/13/2022", assignmentArray);
        testCommitment1.addAssignment("TESTA", "01/02/2022", 1200);

        List<Event> l = new ArrayList<>();

        EventLog el2 = EventLog.getInstance();
        for (Event next : el2) {
            l.add(next);
        }

        el2.clear();
        assertEquals("Added commitment: CPSC 210 to day planner", l.get(1).getDescription());
        assertEquals("Added assignment to CPSC 210", l.get(2).getDescription());
        el.clear();
    }

    @Test
    void removeCommitAndAssignment() {

        testDayPlanner2.getCommitments2().get(0).removeAssignment("TEST");
        testDayPlanner2.removeCommitment("CPSC 210");

        List<Event> l = new ArrayList<>();

        EventLog el = EventLog.getInstance();
        for (Event next : el) {
            l.add(next);
            System.out.println(next);
        }

        el.clear();
        assertEquals("Removed assignment TEST from CPSC 210", l.get(1).getDescription());
        assertEquals("Removed commitment: CPSC 210 from day planner", l.get(2).getDescription());
        el.clear();

    }

    @Test
    void displayCommitAndAssignment() {

        testDayPlanner.getCommitments();
        testCommitment1.getCommitmentAssignments();

        List<Event> l = new ArrayList<>();

        EventLog el = EventLog.getInstance();
        for (Event next : el) {
            l.add(next);
            System.out.println(next);
        }

        el.clear();
        assertEquals("Collected commitments for day planner to be displayed", l.get(1).getDescription());
        assertEquals("Collected assignments for CPSC 210 to be displayed", l.get(2).getDescription());
        el.clear();
    }



}

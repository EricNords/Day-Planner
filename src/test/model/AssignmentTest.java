package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssignmentTest {

    @Test
    void assignmentTest() {
        Assignment assignment = new Assignment("CPSC Midterm 1",
                "10/13/2022", 1900);
        assertEquals("CPSC Midterm 1", assignment.getAssignmentName());
        assertEquals("10/13/2022", assignment.getAssignmentDate());
        assertEquals(1900, assignment.getAssignmentTime());
    }

}

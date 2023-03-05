package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

// Day planner application
public class DayPlannerApp {
    private static final String JSON_STORE = "./data/dayPlanner.json";
    private DayPlanner dp;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the day planner app
    public DayPlannerApp() {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        runDayPlanner();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runDayPlanner() {
        boolean keepGoing = true;
        String command = null;

        initialization();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    //MODIFIES: this
    //EFFECTS: initializes the day planner
    private void initialization() {
        ArrayList<Commitment> arc = new ArrayList<>();
        dp = new DayPlanner(arc);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    //EFFECTS: display assignments menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tac -> add commitment");
        System.out.println("\trc -> remove commitment");
        System.out.println("\tdc -> display commitments");
        System.out.println("\taa -> add assignment");
        System.out.println("\tra -> remove assignment");
        System.out.println("\tda -> display assignment");
        System.out.println("\ts -> save day planner to file");
        System.out.println("\tl -> load day planner from file");
        System.out.println("\tq -> quit");
    }

    //MODIFIES: this
    //EFFECTS: process user command
    private void processCommand(String command) {
        processCommand2(command);
        switch (command) {
            case "aa":
                System.out.println("Input name of commitment you wish to add an assignment to: ");
                command = input.next();
                doAddAssignments(command);
                break;
            case "ra":
                System.out.println("Input name of commitment you wish to remove an assignment from: ");
                command = input.next();
                doRemoveAssignments(command);
                break;
            case "da":
                System.out.println("Input name of commitment's assignments you wish to view: ");
                command = input.next();
                printAssignments(command);
                break;
        }
    }

    //MODIFIES: this
    //EFFECTS: process user command
    private void processCommand2(String command) {
        switch (command) {
            case "s":
                saveDayPlanner();
                break;
            case "l":
                loadDayPlanner();
                break;
            case "ac":
                doAddCommitments();
                break;
            case "rc":
                System.out.println("Input name of commitment you wish to remove: ");
                command = input.next();
                doRemoveCommitments(command);
                break;
            case "dc":
                printCommitments();
                break;
        }
    }

    // EFFECTS: saves the day planner to file
    private void saveDayPlanner() {
        try {
            jsonWriter.open();
            jsonWriter.write(dp);
            jsonWriter.close();
            System.out.println("Saved day planner to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads day planner from file
    private void loadDayPlanner() {
        try {
            dp = jsonReader.read();
            System.out.println("Loaded day planner from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: adds commitments to dp
    private void doAddCommitments() {
        System.out.println("Enter commitment name: ");
        String name = input.next();
        System.out.println("Enter commitment time: ");
        int time = input.nextInt();
        System.out.println("Enter commitment location: ");
        String location = input.next();
        System.out.println("Enter commitment date: ");
        String date = input.next();
        ArrayList<Assignment> assignments = new ArrayList<>();

        dp.addCommitment(name, time, location, date, assignments);
        System.out.println("Commitment added");
    }

    //MODIFIES: this
    //EFFECTS: removes desired commitments from dp
    public void doRemoveCommitments(String command) {
        dp.removeCommitment(command);
        System.out.println("Commitment removed");
    }

    // EFFECTS: prints commitments to screen
    private void printCommitments() {
        System.out.println("Commitments: ");
        for (int i = 0; i < dp.getCommitments().size(); i++) {
            System.out.println(dp.getCommitments().get(i).getCommitmentName());
        }
    }

    //MODIFIES: this
    //EFFECTS: adds assignments to desired commitment
    private void doAddAssignments(String command) {
        for (int i = 0; i < dp.getCommitments().size(); i++) {
            if (Objects.equals(command, dp.getCommitments().get(i).getCommitmentName())) {
                System.out.println("Input the name of the assignment: ");
                String name = input.next();
                System.out.println("Input the date of the assignment: ");
                String date = input.next();
                System.out.println("Input the time of the assignment: ");
                int time = input.nextInt();

                dp.getCommitments().get(i).addAssignment(name, date, time);
                System.out.println("Assignment added");
                break;
            }
        }
        System.out.println("Error: no commitment with given name");
    }


    //MODIFIES: this
    //EFFECTS: removes desired assignment to commitment
    private void doRemoveAssignments(String command) {
        for (int i = 0; i < dp.getCommitments().size(); i++) {
            if (Objects.equals(command, dp.getCommitments().get(i).getCommitmentName())) {
                System.out.println("Input the name of the assignment you wish to remove: ");
                String name = input.next();
                dp.getCommitments().get(i).removeAssignment(name);
                System.out.println("Assignment removed");
                break;
            } else {
                System.out.println("Error: no commitment with given name");
            }
        }
    }

    // EFFECTS: prints assignments of selected commitment to screen
    private void printAssignments(String command) {
        for (int i = 0; i < dp.getCommitments().size(); i++) {
            if (Objects.equals(command, dp.getCommitments().get(i).getCommitmentName())) {
                System.out.println("Assignments: ");
                for (int j = 0; j < dp.getCommitments().get(i).getCommitmentAssignments().size(); j++) {
                    System.out.println(dp.getCommitments().get(i).getCommitmentAssignments()
                            .get(j).getAssignmentName());
                }
                break;
            } else {
                System.out.println("Error: no commitment with given name");
            }
        }
    }

}

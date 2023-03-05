# *Scheduling Application*

## Basic Information

With this project I seek to solve a problem I have been experiencing for the better part of the past 7 years.
That problem being my struggle to adequately schedule my day to my satisfaction with any scheduling appilcation that 
I have trued to date. Generally, I have resorted to using Google Calendar, a physical 
agenda, and a notebook to track my classes, midterms, extracurriculars and other assorted activities. I have yet to 
find a single system that can fill the gaping void that is my obsession for a one-size fits-all scheduling and day 
planning solution. As I surely can't be the only student who feels this way, I hope this project will be used by those 
who share my borderline unhealthy obsession with clean structure and scheduling. 

This application will let a user add and remove "commitments" to a day planner which will have a day view. If time 
permits, I would ideally like to include both a week and month view to see multiple days; however, this may be
impossible due to time constraints. Each commitment will have a name, time, date, and a location. 
Moreover, commitments can also have a list of "assignments" attached to them. "Assignments" will have a name, time, and
date. As an example, if a user's "commitment" is a class, the "assignments" could be the homework and
midterms for that class. If the "commitment" is a club, the "assignments" could a deadline for a project that the club 
is working on.

## User Stories

- As a user I want to be able to add and remove "commitments" from the day planner

- As a user I want to be able to add and remove "assignments" from a "commitment" 

- As a user I want to be able to see all the "commitments" I have for the day

- As a user I want to be able to see all the "assignments" I have for a given commitment 

- As a user I want to be able to save my day planner to a file

- As a user I want to be able to load my day planner from a file 

## Instructions for Grader

Adding an X to a Y
- Click "Add Commitment" button
- Click text box below "Commitment Name:", type in commitment name
- Click text box below "Commitment Time:", type in commitment time in 24 hour time without the colon (ex. 1339 would be 1:39pm)
- Click text box below "Commitment Location:", type in commitment location
- Click text box below "Commitment Date:", type in commitment date in format (mm/dd/yy)
- Click "OK"
- Added popup will appear, click "OK"

Removing an X from a Y
- Click "Remove Commitment" button
- Type in name of commitment
- Click "OK"
- Removed popup will appear, click "OK"

Display Commitments
- Click "Display Commitments"
- Type in the date (mm/dd/yy) of the commitments you would like to see or "all" for all
- Click "OK"

Add Assignment
- Click "Add Assignment"
- Type in commitment name that you would like to add an assignment to
- Click text box below "Assignment Name:", type in assignment name
- Click text box below "Assignment Date:", type in commitment date in format (mm/dd/yy)
- Click text box below "Assignment Time:", type in commitment time in 24 hour time without the colon (ex. 1339 would be 1:39pm)
- Click "OK"

Remove Assignment
- Click "Remove Assignment"
- Type in commitment name that you would like to remove an assignment from
- Type in name of assignment that you would like to remove
- Click "OK"

Display Assignments
- Click "Display Assignments"
- Type in name of commitment for the assignments you would like to see
- Click "OK"

Display Image
- Click "Image Requirement"

Save the application to file
- Click "Save"
- Saved popup will appear, click "OK"

Load the application to file
- Click "Load"
- Loaded popup will appear, click "OK"

## Phase 4: Task 2

When the program runs, nothing related to the events log immediately occurs. 
This is because the events log is only triggered when commitments/assignments
are added, removed, or displayed. When the program initially runs, a day planner
is created, however, it has no elements. The only scenario where the events log 
would immediately have entries is if saved data was automatically loaded on start up.

## Phase 4: Task 3

If I was to refactor my code, I would have done the following:
- create an abstract class with add, remove, and display methods. DayPlanner and 
Commitment classes would then extend this abstract class.

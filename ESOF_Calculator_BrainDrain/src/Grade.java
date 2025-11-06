import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Grade {

    private String assignment;
    private int count = 0;
    public int assignmentAmount;
    private double grade;
    private double percentGrade;
    private HashMap<String, HashSet> grades = new HashMap<>();
    private HashSet assign = new HashSet();

    public Grade(String assignmentName, double newGrade, int amount,  double weight) {
        this.assignment = assignmentName;
        this.grade = newGrade;
        this.assignmentAmount = amount;
        this.percentGrade = weight / 100.0;
        grades.put(assignment, assign);
    }

    // getter/setter methods
    public int getAssignmentAmount() {
        return this.assignmentAmount;
    }

    public double getWeight() {
        return this.percentGrade;
    }

    public double getGrade() {
        return this.grade;
    }
    public void setGrade(double newGrade) {

        if(this.count < this.assignmentAmount) {
            assign.add(newGrade);
            this.count++;
            newGrade += this.grade;
            this.grade = newGrade / count;
        }

        else {
            System.out.println("You have entered a grade for every " + this.assignment);
        }
        grades.put(assignment, assign);
    }


    // calculates grade for future grade
    public double calculateGrade(String newAssignment, double newGrade) {
        for(String s: grades.keySet()) {
            if(s.equals(newAssignment)) {
                double unweighted = this.getGrade() + newGrade;
                double weighted = unweighted / (this.getAssignmentAmount() + 1);
                newGrade = weighted;
            }
        }
        return newGrade;
    }

    // get's input for future grade
    public double futureGrade(String answer) {
        Scanner sc = new Scanner(System.in);
        for(String s: grades.keySet()) {
            if(s.equals(answer)) {
                System.out.println("What grade do you want?");
                double newGrade = sc.nextDouble();
                calculateGrade(s, newGrade);
                return newGrade;
            }
        }
        return this.grade;
    }

    // rounding method
    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}

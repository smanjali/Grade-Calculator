import java.util.HashMap;
import java.util.Scanner;

public class Class {

    private double currentGrade;
    private String currentName;
    private int amount;
    private HashMap<String,HashMap> classes =  new HashMap<>();
    protected HashMap<String,Grade> assignments =  new HashMap<>();

    public Class(String newClass, double currentGrade) {
        this.currentGrade = currentGrade;
        this.currentName = newClass;
    }

    // get's class info and creates a new grade class for it
    public void receiveClassInfo(String newAssignment, int newAmount, double weight) {
        this.amount = newAmount;
        Grade newGrade = new Grade(newAssignment, 0, newAmount, weight);

        assignments.put(newAssignment, newGrade);
        classes.put(currentName, assignments);

    }

    // adds a new assignment grade
    public void addGrade(String newAssignment, double newGrade) {
        Grade getter = assignments.get(newAssignment);
        getter.setGrade(newGrade);
    }

    // calculates the final grade
    public String finalGrade() {
        double finalGrade = 0;
        for(String s: assignments.keySet()) {
            double smallGrade = assignments.get(s).getGrade() * assignments.get(s).getWeight();
            finalGrade += smallGrade;
        }


        return letterGrade(finalGrade) + " (%" + String.format("%.2f", finalGrade) + ")";
    }

    // prints out assignment grades
    public void assignmentGrade() {
        for(String s: assignments.keySet()) {
            System.out.println(s + ": " + assignments.get(s).getGrade());
        }
    }

    // calculate future grade
    public double futureGrade() {
        Scanner sc = new Scanner(System.in);

        System.out.println("What type of assignment is this for?");
        System.out.println(classes.get(this.currentName).keySet());
        String assign = sc.nextLine();
        while(!classes.get(this.currentName).keySet().contains(assign)) {
            System.out.println("Please enter a valid assignment");
            System.out.println(classes.get(this.currentName).keySet());
            assign = sc.nextLine();
        }
        double future = assignments.get(assign).futureGrade(assign);
        double finale = 0;
        for (String s : assignments.keySet()) {
            if (s.equals(assign)) {
                double currentGrade = future;
                double assignmentGrade = currentGrade * assignments.get(s).getWeight();
                finale += assignmentGrade;
            } else {
                double currentGrade = assignments.get(s).getGrade();
                double assignmentGrade = currentGrade * assignments.get(s).getWeight();
                finale += assignmentGrade;
            }

        }
        double futureGrade = assignments.get(assign).round(finale, 2);

        return futureGrade;

    }

    // translates final grade to a letter
    public String letterGrade(double newGrade) {
        if(newGrade >= 93) {
            return "A+";
        }
        else if(newGrade >= 90) {
            return "A-";
        }
        else if(newGrade >= 87) {
            return "B+";
        }
        else if(newGrade >= 83) {
            return "B";
        }
        else if(newGrade >= 80) {
            return "B-";
        }
        else if(newGrade >= 77) {
            return "C+";
        }
        else if(newGrade >= 73) {
            return "C";
        }
        else if(newGrade >= 70) {
            return "C-";
        }
        else if(newGrade >= 67) {
            return "D+";
        }
        else if(newGrade >= 63) {
            return "D";
        }
        else {
            return "D-";
        }
    }
}



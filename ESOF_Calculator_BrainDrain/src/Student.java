import java.util.HashMap;
import java.util.Scanner;

public class Student{

    private HashMap<String, Class> info = new HashMap<>();

    //creating a class
    public void makeClass() {

        Scanner sc = new Scanner(System.in);
        System.out.println("What is the name of your new class?");
        String assign = sc.nextLine();

        Class newClass = new Class(assign, 0);
        info.put(assign, newClass);

        System.out.println("Let's add some class information");
        System.out.println();

        enterClassInfo(true, newClass);

    }

    //entering class information
    public void enterClassInfo(boolean y, Class currentClass) {

        Scanner sc = new Scanner(System.in);

        while (y == true) {
            System.out.println("What is the name of this category?");
            String name = sc.nextLine();
            System.out.println("Please enter the number of " + name + "'s for the full semester");
            int amount = Integer.parseInt(sc.nextLine());
            System.out.println("What percent of your grade is " + name);
            double weight = Double.parseDouble(sc.nextLine());

            currentClass.receiveClassInfo(name, amount, weight);

            System.out.println("Do you want to enter more class information? (yes/no)");
            String option = sc.nextLine();
            y = false;
            if(option.equals("yes")) {
                y = true;
            }
        }
    }

    // adding a new grade
    public void inputGrade(Class currentClass) {
        Scanner sc = new Scanner(System.in);

        System.out.println("What category of assignment is this grade for?");
        System.out.println(currentClass.assignments.keySet());
        String assign = sc.nextLine();
        while(currentClass.assignments.get(assign) == null) {
            System.out.println("Please enter a valid category");
            System.out.println(currentClass.assignments.keySet());
            assign = sc.nextLine();
        }

        System.out.println("Enter new grade: ");
        int grade = sc.nextInt();
        currentClass.addGrade(assign, grade);
    }

    // menu
    public void askForInput() {
        System.out.println("1. Add a new class ");
        System.out.println("2. See current classes");
        System.out.println("3. Put in a new grade");
        System.out.println("4. See final grade");
        System.out.println("5. See assignment grades");
        System.out.println("6. Input future assignment grade ");
        System.out.println("7. I'm all good :) ");

        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();

        if(answer.equals("1")) {
            makeClass();

            System.out.println();
            askForInput();
        }

        else if(answer.equals("2")) {
            for(String classes: info.keySet()) {
                System.out.println(classes);
            }
            System.out.println();
            askForInput();
        }

        else if(answer.equals("3")) {
            Scanner s = new Scanner(System.in);
            System.out.println("Which class is this grade for?");
            System.out.println(info.keySet());
            String className = s.nextLine();
            while(!info.containsKey(className)) {
                System.out.println("Please enter a valid class");
                System.out.println(info.keySet());
                className = s.nextLine();
            }

            Class chosenClass = info.get(className);
            inputGrade(chosenClass);
            System.out.println();
            askForInput();
        }

        else if(answer.equals("4")) {
            Scanner s = new Scanner(System.in);

            System.out.println("What class is this for?");
            System.out.println(info.keySet());
            String assign = s.nextLine();
            while (!info.containsKey(assign)) {
                System.out.println("Please enter a valid class");
                System.out.println(info.keySet());
                assign = s.nextLine();
            }

            Class className = info.get(assign);
            System.out.println("This is your grade for " + assign + ": " + className.finalGrade());
            System.out.println();
            askForInput();
        }
        else if(answer.equals("5")) {
            Scanner s = new Scanner(System.in);

            System.out.println("What class is this for?");
            System.out.println(info.keySet());
            String assign = s.nextLine();
            while (!info.containsKey(assign)) {
                System.out.println("Please enter a valid class");
                System.out.println(info.keySet());
                assign = s.nextLine();
            }

            Class chosenClass = info.get(assign);
            chosenClass.assignmentGrade();

            System.out.println();
            askForInput();
        }
       else if(answer.equals("6")) {
            Scanner s = new Scanner(System.in);
            System.out.println("What class is this for?");
            System.out.println(info.keySet());
            String classy = s.nextLine();
            while (!info.containsKey(classy)) {
                System.out.println("Please enter a valid class");
                System.out.println(info.keySet());
                classy = s.nextLine();
            }
            Class chosenClass = info.get(classy);
            double future = chosenClass.futureGrade();
            System.out.println("This would be your future grade for " + classy + ": %" + future);
            askForInput();
        }
       else if(answer.equals("7")) {
           return;
        }
       else {
           System.out.println("Please enter a valid option");
           askForInput();
        }


    }

}

/*
pseudo code:
1. create 2D array of 7x2 strings
2. while(true):
3. inputIsCorrect = false
3. sout("Please, input the day of the week or input "change [day of the week]": ")
4. take input as string
5. input = input.trim().toLowerCase()
5. if input=="exit": break
6. elif (input.split(" ")[0]=="change" or "reschedule")):
8. for(int i=0; i<schedule.length; i++):
9. if(input.split(" ")[1]==schedule[i][0].lowercase):
10. inputIsCorrect=true
11. sout("Please, input new tasks for "+schedule[i][0]+".")
12. schedule[i][1] = input
13. break
14. end for
14. else:
15. for(int i=0; i<schedule.length; i++):
16. if(input==schedule[i][0].lowercase): inputIsCorrect = true, sout(schedule[i][1]), break
17. if(!inputIsCorrect): sout("Sorry, I don't understand you, please try again.")
 */


import java.util.Scanner;

public class WeekPlannerUsingLoop {
    public static void main(String[] args) {
        //schedule array declaration and initialization
        String[][] schedule = new String [7][2];
        schedule[0][0] = "Sunday";
        schedule[0][1] = "do home work";
        schedule[1][0] = "Monday";
        schedule[1][1] = "go to courses; watch a film";

        //add the rest of the days of the week
        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        for(int i=2; i<7; i++) {
            schedule[i][0] = daysOfWeek[i];
        }

        boolean inputIsCorrect; //to check the input at the end of each iteration and print warning message if the input is not acceptable

        String input; //raw input from the user
        String dayName; //to assign the day when the change command is input

        Scanner in = new Scanner(System.in);

        while(true) {
            inputIsCorrect = false; //make it false at the beginning of each iteration, if the input will be correct, true will be assigned to below

            System.out.println("Please, input the day of the week: ");
            input = in.nextLine().trim().toLowerCase(); //trim white spaces from the beginning and end, make the input lowercase


            if(input.equals("exit")) {
                break;
            }
            else if(input.split(" ")[0].equals("change") || input.split(" ")[0].equals("reschedule")) { //if reschedule or change command is input as "change [the day of the wek]"

                for(int i=0; i<schedule.length; i++) { //iterate through the days of the week
                    dayName = input.split(" ")[1]; //assign the day of the input=["change", "day of the week"]

                    if(dayName.equals(schedule[i][0].toLowerCase())) { //if the expected day part of the command matches one of the days
                        inputIsCorrect=true;
                        System.out.println("Please, input new tasks for "+schedule[i][0]+".");
                        schedule[i][1] = in.nextLine(); //change the tasks of the day to the input tasks
                        break;
                    }

                }
            }
            else { //either a day is input correctly, otherwise inputIsCorrect will stay false, the last if statement will be executed
                for(var day: schedule) {
                    if(input.equals(day[0].toLowerCase())) { //check if the input matches one of the days
                        inputIsCorrect = true;

                        if (day[1] == null) { //if there is no tasks for the day
                            System.out.println("The day has no tasks.. You can add using \"change [the day of the week]\"");
                            break;
                        }

                        System.out.println("Your tasks for " + day[0] + ": " + day[1]);
                        break;
                    }
                }
            }
            if(!inputIsCorrect) { //if none of the conditional statements above were executed, inputIsCorrect remained false
                System.out.println("Sorry, I don't understand you, please try again.");
            }
        }
    }
}

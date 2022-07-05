/*
a program called "numbers",
which makes a random number or chooses a random historical event and offers the player to guess it
 */

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class numbersV2 {
    public static void main(String[] args) {
        //declaration of the variables to be used
        String name; //the name of the user
        int game; //1 - for number guessing, 2 - for "well-known event" game
        int randomNum; //the random number generated

        int correctAnswer; //in game-1, equals random number generated,
        // in game-2, equals the year of the random event from 2D array of events

        String[][] historicalEvents = null; //the 2D array to store the names and corresponding years of events
        //initialized beforehand not to get "Variable might not be initialized" error thrown

        int inputNum; //input integer by the user,


        /*
        firstly input will be assigned to "input" String variable.
        if it is an integer,
        then it will be assigned to "inputNum";
        */
        String input;
        String checkStr = "-0123456789"; //the characters of the input will be checked against this string to check type


        boolean inputIsCorrect = false; //will be used to check if input is integer
        int[] arrInputNums = new int[0]; //to save the inputs



        //invite the user to input a name, then assign it to the String var "name".
        System.out.print("Enter your name, please: ");
        Scanner in = new Scanner(System.in);
        name = in.nextLine();


        /*
        ask the user whether he or she wants to play 1-the simple number guessing game,
        or 2-"well-known event date guessing" game
         */
        System.out.printf("%s,\nPlease enter:\n1 - to play number guessing game; \n2 - to play well-known event date guessing: ", name);
        game = in.nextInt();


        //generate the random number differently depending on the game chosen and assign to randomNum variable
        Random rnd = new Random();
        if(game==1) {
            randomNum = rnd.nextInt(101);
            correctAnswer = randomNum;
        }
        else {
            //if game is 2, create the 2D array of Historical Events
            historicalEvents = new String [][]{{"1926","U.S. Starts Numbered Highway System"},
                    {"1943", "Hitler Consolidates Power"},
                    {"1939", "World War II Starts"}};

            randomNum = rnd.nextInt(historicalEvents.length); //random event position
            correctAnswer = Integer.parseInt(historicalEvents[randomNum][0]); //corresponding year of the random event
        }




        //Let the game begin!
        System.out.println("Let the game begin!");
        //loop and ask the user for input till he or she guessed correctly
        while(true) {
            //after each iteration, create a new array which is the copy of previous but longer to add the new input
            arrInputNums = Arrays.copyOf(arrInputNums, arrInputNums.length+1);


            //if the game is 2, print the corresponding name (question) of the random event initially and  each time the guess is wrong
            if(game==2) {
                System.out.printf("When %s ?",historicalEvents[randomNum][1]);
                System.out.println();
            }

            /*
            Ask the user to input the number or year. If the number is not an integer, catch the error, print a warning message.
            Loop until the user enters an integer which is the acceptable type.
             */

            do {
                System.out.print("Enter the number or year, please: ");
                in = new Scanner(System.in);
                input = in.nextLine(); //assigned to String variable

                for (int i = 0; i < input.length(); i++) {
                    if (i == 0 && checkStr.indexOf(input.charAt(i)) == -1) { //the first character can be "-" negative sign
                        inputIsCorrect = false;
                        System.out.println("You did not enter an integer, please, enter an integer..");
                        break; //inputIsCorrect is false, continue to do while loop
                    } else if (i > 0 && checkStr.substring(1).indexOf(input.charAt(i)) == -1) { //the rest should be decimal numbers
                        inputIsCorrect = false;
                        System.out.println("You did not enter an integer, please, enter an integer..");
                        break; //inputIsCorrect is false, continue to do while loop
                    }
                    else {
                        inputIsCorrect = true; //if the conditions above are false, i'th character is a number, continue to for loop
                    }
                }
            } while(!inputIsCorrect); //exit if an integer was assigned to inputNum successfully

            inputNum = Integer.parseInt(input); //parse String input to integer
            inputIsCorrect = false; //if the guess will be wrong below, do while loop above will start as new

            //save the input number to the last position of the array, which has zero as value
            arrInputNums[arrInputNums.length-1] = inputNum;


            if(inputNum<correctAnswer) {
                System.out.println("Your number is too small. Please, try again.");
            }
            else if(inputNum>correctAnswer) {
                System.out.println("Your number is too big. Please, try again.");
            }
            else {
                System.out.printf("Congratulations, %s", name);
                System.out.println();
                break; //terminates the infinite loop
            }
        }
        System.out.println("Your numbers: "+Arrays.toString(arrInputNums)); //print the collection of the inputs
    }
}

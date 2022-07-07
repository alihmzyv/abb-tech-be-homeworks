//pseudo code I wrote before coding
/*
PSEUDO CODE I WROTE BEFORE CODING
each time program runs:
1. create an array [5][5]
2. set one of the cells to 1 randomly
print "game starts message"
3. enter an infinite loop:
4 enter an infinite loop which asks for the line
5 use try and catch
6 in try:
ask for input, assign it.
if number not in range (1, 5), print "warning", continue
7 in catch: print wrong input message
8 enter an infinite loop which asks for the bar
9 use try and catch
10 in try:
ask for input, assign it.
if number not in range (1, 5), print "warning", continue
11 in catch: print wrong input message
12. if "user shot-1" was at cell -1, print out "warning message", continue
13. if "user shot-1" was at 0, change arr["user shot"]=-1
14. if "user shot-1" was at 1, change arr["user shot"]=2;
14. print out the field:
print "-" for 0 and 1 cells,
print "*" for -1 cells,
print "x" for 2 cells.
if arr["user shot-1"]==2, print "win", break
 */



import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class ShootingAtTheSquare {
    public static void main(String[] args) {
        /*in most of the cases where could have used [5,5] for limits, used .length,
        so the size of the square can be asked to user
         */

        //ask user to input the size of the square
        System.out.print("The size of the square you want to play on: ");
        Scanner in = new Scanner(System.in);
        int size = in.nextInt(); //size of the square


        int[][] gameField = new int[size][size]; //create 2D array of zeros of given size which represents field

        Random rnd = new Random();
        gameField[rnd.nextInt(size)][rnd.nextInt(size)] = 1; //to set a target randomly, changes one of the cells of the array to 1, which is target afterwards

        int line = 0; //line to be shooted, input by user; initialized beforehand not to get "Variable might not be initialized" error thrown
        int bar = 0; //bar to be shooted, input by user; initialized beforehand not to get "Variable might not be initialized" error thrown

        while(true) {

            //display the updated game field
            for(int i=0; i<=size; i++) {
                System.out.print(i+" | ");
            }
            System.out.println();

            for(int i=0; i<size; i++) {
                System.out.print((i+1)+" | ");
                for(int j=0; j<size; j++) {
                    if(gameField[i][j]==0 || gameField[i][j]==1) {//cells with 0 or 1 values are the ones not got shot yet
                        System.out.print("- | ");
                    }
                    if(gameField[i][j]==-1) {//cells with -1 value are the ones already shot
                        System.out.print("* | ");
                    }
                    if(gameField[i][j]==2) {//cell with 2 value is the one that was 1 before, got shot, changed to 2 at the end of the previous iteration
                        System.out.print("x | ");
                    }
                }
                System.out.println();
            }

            /*
            if user "shot" at target which is the already the cell with 2 value
            print "win" message, break the outer while loop
             */
            if(gameField[line][bar]==2) {
                System.out.println("You have won!");
                break; //breaks outer while loop
            }


            //loop to set line to shoot at
            while(true) {
                try {
                    System.out.print("Enter a line for fire: ");
                    in = new Scanner(System.in);
                    line = in.nextInt()-1;
                    /*
                    line numbers entered in range[1, gameField.length] converted to corresponding position index of game field array which is in range [0,gameField.length-1], by subtracting 1,
                    the same for bar number is applied below in the next nested while loop (starts at line 116)
                     */

                    if(line<0 || line>size-1) { //if line number entered greater than the size, ask for input again
                        System.out.println("Please, enter a number in range [1, "+size+"]");
                        continue; //jumping to next iteration which is basically asking again
                    }
                }
                catch (InputMismatchException exc) { //if not an integer entered, ask for input again
                    System.out.println("Please, enter an integer..");
                    continue;
                }
                break; //if not got into size checking and catch, then number entered is okay, break line number asking while loop
            }


            //loop to set bar to shoot at
            while(true) {
                try {
                    System.out.print("Enter a shooting bar for fire: ");
                    in = new Scanner(System.in);
                    bar = in.nextInt()-1;

                    if(bar<0 || bar>size-1) {//if bar number entered greater than the size, ask for input again
                        System.out.println("Please, enter a number in range [1, "+size+"]");
                        continue; //jumping to next iteration which is basically asking again
                    }
                }
                catch (InputMismatchException exc) { //if not an integer entered, ask for input again
                    System.out.println("Please, enter an integer..");
                    continue;
                }
                break; //if not got into size checking or catch, then number entered is okay, break bar number asking while loop
            }


            if(gameField[line][bar]==-1) { //do not let to shoot at the already shooted cell that have -1 value
                System.out.println("Already shot. Please, shoot at a cell not shot already..");
                continue; //ask for inputs again by going to next iteration of outer while loop
            }
            else if(gameField[line][bar]==0) {//if the cell is empty and not shot yet, set cell to -1, which implies "already shot"
                gameField[line][bar] = -1;
            }
            else {  //else user shot the cell which equals 1 which is the target, set cell to 2, which implies the user won
                gameField[line][bar] = 2;
            }
        }
    }
}

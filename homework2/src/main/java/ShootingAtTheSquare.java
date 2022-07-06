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
        /*in most of the lines where could have used [5,5] for limits, used .length,
        so the size of the square can be asked to user
         */

        //ask user for the size of the square
        System.out.print("The size of the square you want to play on: ");
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();


        //declare variables beforehand
        int[][] gameField = new int[size][size]; //create 2D array of zeros representing field

        Random rnd = new Random();
        gameField[rnd.nextInt(size)][rnd.nextInt(size)] = 1; //sets the target to 1, randomly

        int line = 0; //line to be shooted
        int bar = 0; //bar to be shooted

        while(true) {
            //display the updated game field
            for(int i=0; i<=size; i++) {
                System.out.print(i+" | ");
            }
            System.out.println();

            for(int i=0; i<size; i++) {
                System.out.print((i+1)+" | ");
                for(int j=0; j<size; j++) {
                    if(gameField[i][j]==0 || gameField[i][j]==1) {
                        System.out.print("- | ");
                    }
                    if(gameField[i][j]==-1) {
                        System.out.print("* | ");
                    }
                    if(gameField[i][j]==2) {
                        System.out.print("x | ");
                    }
                }
                System.out.println();
            }

            /*
            if user "shot" at target, target (1) which was already changed to 2,
            print "win" message, break the outer while loop
             */
            if(gameField[line][bar]==2) {
                System.out.println("You have won!");
                break;
            }


            //loop to set line to shoot at
            while(true) {
                try {
                    System.out.print("Enter a line for fire: ");
                    line = in.nextInt()-1;
                    /*
                    line entered [1,5] converted to position index of game field [0,4] by subtracting 1, the same
                    for bar number below is applied
                     */

                    if(line<0 || line>size-1) {
                        System.out.println("Please, enter a number in range [1, "+size+"]");
                        continue;
                    }
                }
                catch (InputMismatchException exc) {
                    System.out.println("Please, enter an integer..");
                    continue;
                }
                break; //if not got into range checking or catch, then number entered is okay, break line input asking
            }


            //loop to set bar to shoot at
            while(true) {
                try {
                    System.out.print("Enter a shooting bar for fire: ");
                    bar = in.nextInt()-1;

                    if(bar<0 || bar>size-1) {
                        System.out.println("Please, enter a number in range [1, "+size+"]");
                        continue;
                    }
                }
                catch (InputMismatchException exc) {
                    System.out.println("Please, enter an integer..");
                    continue;
                }
                break; //if not got into range checking or catch, then number entered is okay, break bar input asking
            }


            if(gameField[line][bar]==-1) { //do not let to shoot at the already shooted cell
                System.out.println("Already shot. Please, shoot at a cell not shot already..");
                continue;
            }
            else if(gameField[line][bar]==0) {//if not shot target, set sell to -1
                gameField[line][bar] = -1;
            }
            else {  //else user shot the cell which equals 1, set the cell to 2
                gameField[line][bar] = 2;
            }
        }
    }
}

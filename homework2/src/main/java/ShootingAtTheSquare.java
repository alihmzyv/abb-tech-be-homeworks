//pseudo code I wrote before coding
/*
PSEUDO CODE I WROTE BEFORE CODING
each time program runs:
1. ask the user: size
2. size = input
2. ask the user: for playing simple version-1, for area shooting 2
3. game = input
4. gameField = int[size][size]
5. //Setting a random target first
6. lineTarget=random(0, size)
7. barTarget=random(0, size)
8. gameField[lineTarget][barTarget]=1
9. horizontalOrVertical=random(0, 1)
10. if horizontalOrVertical=0:
11. try: arr[var2][var3-1]=1, arr[var2][var3+1]=1,
12. catch:
13.if var3=0, arr[var2][var3+1]=1, arr[var2][var3+2]=1,
14.else: arr[var2][var3-1]=1, arr[var2][var3-2]=1
15. else:
16. try: arr[var2-1][var3]=1, arr[var2+1][var3]=1,
17. catch: if var2=0, arr[var2+1][var3]=1, arr[var2+1][var3]=1,
18. else: arr[var2+1][var3]=1, arr[var2+1][var3]=1
19. int count = 0
20. print "game starts message"
21 .enter an infinite loop:
22. print out the field:
(print "-" for 0 and 1 cells,
print "*" for -1 cells,
print "x" for 2 cells.)
arr["user shot-1"]==2:
22. if(gameField[user shot]==2):
23. if game=1, print "win", break
24. else: count++, end if.
25. if(count==3) print "win" break
22. enter an infinite loop which asks for the line
23. //use try and catch
24. try:
25. ask for input, assign it.
26. catch (ArrayBoundsExc): print("range message", continue)
27. catch(MisInput): print wrong input message
28. enter an infinite loop which asks for the bar
29. //use try and catch
30. try:
31. ask for input, assign it.
32. catch (ArrayBoundsExc): print("range message", continue)
33. catch(MisInput): print wrong input message
35. if "user shot-1" was at cell -1 or 2, print out "warning message", continue
36. if "user shot-1" was at 0, change arr["user shot"]=-1
37. if "user shot-1" was at 1, change arr["user shot"]=2;
 */



import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class ShootingAtTheSquare {
    public static void main(String[] args) {

        //ask the user to input the size of the square
        System.out.println("The size of the square you want to play on: ");
        Scanner in = new Scanner(System.in);
        int squareSize = in.nextInt(); //size of the square or field

        //ask the user to input the type of the game he or she wants to play:
        //1-for simple type, 2-for "area shooting" type
        System.out.println("The type of the game that you want to play:\n" +
                "1 - for simple one target\n" +
                "2 - for area shooting type:");
        int gameType = in.nextInt();

        /*
        create the 2D array of zeros of the input size which represents the square
        (zero represents a cell not got shot at)
         */
        int[][] square = new int[squareSize][squareSize];

        /*
        for either type of the game, set a random target first,
        by changing a random element's value from 0 to 1 (1 represents the cell which is the target
        or one of the targets depending on the game type).
         */
        Random rnd = new Random();
        int firstTargetLine = rnd.nextInt(squareSize); //line of the target cell, randomly chose
        int firstTargetBar = rnd.nextInt(squareSize); //bar of the target cell, randomly chose
        square[firstTargetLine][firstTargetBar] = 1; //changed to 1

        /*
        if game is 1 (area shooting):
        1. choose randomly to put neighbour targets horizontally or vertically
        2. depending on the horizontal or vertical neighbouring,
        set the elements in the both sides of the first target to 1, which makes them target as well
         */
        int count = 0; //count the number of targets that got shot in game 2 - area shooting
        if(gameType==2) {
            int horizontalOrVertical = rnd.nextInt(2); //0 = horizontal, 1 = vertical

            if(horizontalOrVertical==0) {
                try {
                    square[firstTargetLine][firstTargetBar-1] = 1;
                    square[firstTargetLine][firstTargetBar+1] = 1;
                }
                catch (ArrayIndexOutOfBoundsException exc) {
                    if (firstTargetBar == 0) {
                        square[firstTargetLine][firstTargetBar + 1] = 1;
                        square[firstTargetLine][firstTargetBar + 2] = 1;
                    }
                    else { //firstTargetBar==squareSize-1
                        square[firstTargetLine][firstTargetBar - 1] = 1;
                        square[firstTargetLine][firstTargetBar - 2] = 1;
                    }
                }
            }

            if (horizontalOrVertical==1) {
                try {
                    square[firstTargetLine-1][firstTargetBar] = 1;
                    square[firstTargetLine+1][firstTargetBar] = 1;
                }
                catch (ArrayIndexOutOfBoundsException exc) {
                    if (firstTargetLine == 0) {
                        square[firstTargetLine+1][firstTargetBar] = 1;
                        square[firstTargetLine+2][firstTargetBar] = 1;
                    }
                    else { //firstTargetLine==squareSize-1
                        square[firstTargetLine-1][firstTargetBar] = 1;
                        square[firstTargetLine-2][firstTargetBar] = 1;
                    }
                }
            }
        }


        int lineShot = 0; //line of the cell to be shot at, to be assigned by user each time he or she inputs to "shoot at";
        // initialized beforehand not to get "Variable might not be initialized" error thrown
        int barShot = 0; //bar of the cell to be shot at, to be assigned by user each time he or she inputs to "shoot at";
        // initialized beforehand not to get "Variable might not be initialized" error thrown


        System.out.println("All set. Get ready to rumble!");


        while(true) {

            //display the updated game field
            for(int i=0; i<=squareSize; i++) {
                System.out.print(i+" | ");
            }
            System.out.println();

            for(int i=0; i<squareSize; i++) {
                System.out.print((i+1)+" | ");
                for(int j=0; j<squareSize; j++) {
                    if(square[i][j]==0 || square[i][j]==1) {//cells with 0 or 1 values are the ones not got shot yet
                        System.out.print("- | ");
                    }
                    if(square[i][j]==-1) {//cells with -1 value are the ones already shot
                        System.out.print("* | ");
                    }
                    if(square[i][j]==2) {//cell with 2 value is the one that was 1 before, got shot, changed to 2 at the end of the previous iteration
                        System.out.print("x | ");
                    }
                }
                System.out.println();
            }


            if(square[lineShot][barShot]==2) { //if the user shot a target
                if(gameType==1) {//game is simple one target game, then user shot the only target
                    System.out.println("You have won!");
                    break; //breaks outer while loop, game stops
                }
                else { //gameType==2, the game is area shooting
                    count++;
                    if(count==3) {
                        System.out.println("You have won!");
                        break; //breaks outer while loop, game stops
                    }
                }
            }


            //loop to set line and bar position of the cell to shoot at
            while(true) {
                try {
                    System.out.print("Enter a line for fire: ");
                    in = new Scanner(System.in);
                    lineShot = in.nextInt()-1; //convert positions from square display to array index positions

                    System.out.print("Enter a shooting bar for fire: ");
                    in = new Scanner(System.in);
                    barShot = in.nextInt()-1;

                    if(square[lineShot][barShot]==-1 || square[lineShot][barShot]==2) { //do not let to shoot at the already shooted cell or target cell that have -1 or 2 as value, respectively
                        System.out.println("Already shot. Please, shoot at a cell not got shot already..");
                        continue; //ask for inputs again
                    }
                    else if(square[lineShot][barShot]==0) {//if the cell is empty and not got shot yet, set cell to -1, which implies "already shot"
                        square[lineShot][barShot] = -1;
                    }
                    else {  //user shot at the cell which equals 1 which is the target, set cell to 2, which implies "already shot target"
                        square[lineShot][barShot] = 2;
                    }
                }
                catch (InputMismatchException exc) { //if not an integer entered, ask for input again
                    System.out.println("Please, enter positions as integers..");
                    continue;
                }
                catch (ArrayIndexOutOfBoundsException exc) {
                    System.out.println("Please enter both the line and bar position in range[1, "+squareSize+"]..");
                    continue;
                }
                break; //if try does not throw any error, then number entered was okay, exit the loop asking for line and bar position
            }
        }
    }
}

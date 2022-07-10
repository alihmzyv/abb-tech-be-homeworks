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
6. lineTarget=random(size)
7. barTarget=random(size)
8. gameField[lineTarget][barTarget]=1
9. horizontalOrVertical=random(2)
10. if horizontalOrVertical=0:
11. try:
12. arr[lineTarget][barTarget-1]=1, arr[lineTarget][barTarget+1]=1
13. catch:
13. if barTarget=0: arr[lineTarget][barTarget+1]=1, arr[lineTarget][barTarget+2]=1,
14. else: arr[lineTarget][barTarget-1]=1, arr[lineTarget][barTarget-2]=1
15. else:
16. try:
17. arr[lineTarget-1][barTarget]=1, arr[lineTarget+1][barTarget]=1,
18. catch:
19. if lineTarget=0: arr[lineTarget+1][barTarget]=1, arr[lineTarget+2][barTarget]=1,
20. else: arr[lineTarget-1][barTarget]=1, arr[lineTarget-2][barTarget]=1
21. int count = 0
22. print "game starts message"
23 .enter an infinite loop:
24. print out the field:
(print "-" for 0 and 1 cells,
print "*" for -1 cells,
print "x" for 2 cells.)
25. arr["user shot-1"]==2:
26. if(gameField[user shot-1]==2):
27. if game=1: print "win", break
28. else: count++
29. if(count==3) print "win", break
30. enter an infinite loop which asks for the line and bar to shoot at
31. //use try and catch
32. try:
33. ask for input, assign it.
34. if "user shot" was at cell -1 or 2, print out "warning message", continue
35. if "user shot" was at 0, change arr["user shot"]=-1
36. if "user shot" was at 1, change arr["user shot"]=2;
37. catch (ArrayBoundsExc): print("range message", continue)
38. catch(MisInput): print wrong input message
39. break //breaks loop at 30
 */



import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class ShootingAtTheSquare {
    public static void main(String[] args) {

        //ask the user to input the type of the game he or she wants to play:
        //1-for simple type, 2-for "area shooting" type
        System.out.println("The type of the game that you want to play:\n" +
                "1 - for simple one target\n" +
                "2 - for area shooting type (the size of the square should be equal to or greater than 3):");
        Scanner in = new Scanner(System.in);
        int gameType = in.nextInt();

        int squareSize = 5; //size of the square or field

        //to ask the user to input the size of the square, you can uncomment the code below
//        System.out.println("The size of the square you want to play on: ");
//        squareSize = in.nextInt(); //size of the square or field


        /*
        create the 2D array of zeros of the input size which represents the square
        (zero represents a cell not got shot at yet)
         */
        int[][] square = new int[squareSize][squareSize];


        /*
        for either type of the game, set a random target first,
        by changing a random element's value from 0 to 1 (1 represents the cell which is the target
        or one of the targets depending on the game type).
         */
        Random rnd = new Random();
        int firstTargetLine = rnd.nextInt(squareSize); //line of the target cell, randomly chosen
        int firstTargetBar = rnd.nextInt(squareSize); //bar of the target cell, randomly chosen
        square[firstTargetLine][firstTargetBar] = 1; //changed to 1


        /*
        if game is 2 (area shooting):
        1. choose randomly to put neighbour targets horizontally or vertically
        2. depending on the horizontal or vertical neighbouring,
        set the elements in the both sides or either side(when the first target is at the boundaries)
        of the first target to 1, which makes them target as well
         */
        int count = 0; //counts the number of targets that got shot in game 2 - area shooting

        if(gameType==2) {
            int horizontalOrVertical = rnd.nextInt(2); //0 = horizontal, 1 = vertical, randomly chosen

            if(horizontalOrVertical==0) {//set horizontal neighbours to 1 as well, creating 3 targets in total
                try {
                    square[firstTargetLine][firstTargetBar-1] = 1;
                    square[firstTargetLine][firstTargetBar+1] = 1;
                }
                catch (ArrayIndexOutOfBoundsException exc) {
                    if (firstTargetBar == 0) { //at boundary
                        square[firstTargetLine][firstTargetBar + 1] = 1;
                        square[firstTargetLine][firstTargetBar + 2] = 1;
                    }
                    else { //firstTargetBar==squareSize-1, at boundary
                        square[firstTargetLine][firstTargetBar - 1] = 1;
                        square[firstTargetLine][firstTargetBar - 2] = 1;
                    }
                }
            }

            if (horizontalOrVertical==1) {//set vertical neighbours to 1, creating again 3 targets in total
                try {
                    square[firstTargetLine-1][firstTargetBar] = 1;
                    square[firstTargetLine+1][firstTargetBar] = 1;
                }
                catch (ArrayIndexOutOfBoundsException exc) {
                    if (firstTargetLine == 0) {//at boundary
                        square[firstTargetLine+1][firstTargetBar] = 1;
                        square[firstTargetLine+2][firstTargetBar] = 1;
                    }
                    else { //firstTargetLine==squareSize-1, at boundary
                        square[firstTargetLine-1][firstTargetBar] = 1;
                        square[firstTargetLine-2][firstTargetBar] = 1;
                    }
                }
            }
        }


        int lineShot = 0; //line of the element to be shot at, to be assigned by user each time he or she inputs to "shoot at";
        // initialized beforehand not to get "Variable might not be initialized" error thrown
        int barShot = 0; //bar of the cell to be shot at, to be assigned by user each time he or she inputs to "shoot at";
        // initialized beforehand not to get "Variable might not be initialized" error thrown


        System.out.println("All set. Get ready to rumble!");

        //infinite game loop
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
                    if(square[i][j]==-1) {//cells with -1 value are the empty ones already got shot
                        System.out.print("* | ");
                    }
                    if(square[i][j]==2) {//cell with 2 value is the one that was target=1 before, got shot, changed to 2 at the end of the previous iteration
                        System.out.print("x | ");
                    }
                }
                System.out.println();
            }


            if(square[lineShot][barShot]==2) { //if the user shot a target
                if(gameType==1) {//game is simple one target game, then user shot the one and only target
                    System.out.println("You have won!");
                    break; //breaks outer while loop, game stops
                }
                else { //gameType==2, the game is area shooting
                    count++;
                    if(count==3) {//shot the all 3 targets
                        System.out.println("You have won!");
                        break; //breaks outer while loop, game stops
                    }
                }
            }


            //loop to set line and bar position of the cell to shoot at
            while(true) {
                try {
                    System.out.println("Enter a line for fire: ");
                    in = new Scanner(System.in);
                    lineShot = in.nextInt()-1; //convert positions from square display to array index positions

                    System.out.println("Enter a shooting bar for fire: ");
                    in = new Scanner(System.in);
                    barShot = in.nextInt()-1;

                    if(square[lineShot][barShot]==-1 || square[lineShot][barShot]==2) { //do not let to shoot at the already shot empty cell or target cell that have -1 or 2 as value, respectively
                        System.out.println("Already shot. Please, shoot at a cell not got shot already..");
                        continue; //ask for inputs again
                    }
                    else if(square[lineShot][barShot]==0) {//if the cell is empty and not got shot yet, set cell to -1, which implies "already shot empty cell"
                        square[lineShot][barShot] = -1;
                    }
                    else {  //user shot at the cell which equals 1 which is the target, set cell to 2, which implies "already shot target"
                        square[lineShot][barShot] = 2;
                    }
                }
                catch (InputMismatchException exc) { //if not an integer entered for line or bar, ask for inputs again
                    System.out.println("Please, enter positions as integers..");
                    continue;
                }
                catch (ArrayIndexOutOfBoundsException exc) {//if the input of line or bar is out of the bounds of the square, ask for inputs again
                    System.out.println("Please enter both the line and bar position in range[1, "+squareSize+"]..");
                    continue;
                }
                break; //if try does not throw any error, then number entered was okay, exit the loop asking for line and bar position
            }
        }
    }
}

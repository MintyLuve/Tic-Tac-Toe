import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char top[] = {' ','|',' ','|',' '};
        char mid[] = {' ','|',' ','|',' '};
        char low[] = {' ','|',' ','|',' '};
        Scanner sc = new Scanner(System.in);
        char oTurn = 'X';
        boolean winner = false;
        boolean draw = false;
        int playAgain = 2;
        
        do {
            resetAll(top, mid, low);
            winner = false;
            draw = false;
            while (winner == false && draw == false){
                oTurn = turn(sc, oTurn, top, mid, low);
                draw = checkDraw(top, mid, low);
                switch (oTurn) {
                    case 'X':
                        winner = checkWinner(top, mid, low, 'O');
                        if (winner == true){
                            oTurn = 'O';
                        }
                        break;
                    case 'O':
                        winner = checkWinner(top, mid, low, 'X');
                        if (winner == true){
                            oTurn = 'X';
                        }
                        break;
                }
            }
            if (winner == true) {
                System.out.println("\nThe winner is "+oTurn+"! Yay");
            }
            else if (draw == true){
                System.out.println("\nMan, u got a draw :/");
            }
            System.out.println("Would you like to play again? (1 for yes, 2 for no)");
            playAgain = sc.nextInt();
        } while (playAgain == 1);

    }

    public static void printAll(char top[], char mid[], char low[]){
        for (int i = 0; i< top.length; i++){
            System.out.print(top[i]);
        }
        System.out.println("\n------");
        for (int i = 0; i< mid.length; i++){
            System.out.print(mid[i]);
        }
        System.out.println("\n------");
        for (int i = 0; i< low.length; i++){
            System.out.print(low[i]);
        }
    }
    public static void resetAll(char top[], char mid[], char low[]){
        int one = 0, two = 2, three = 4;
        for (int i = 0; i <= 4; i+=2){
            top[i] = ' ';
            mid[i] = ' ';
            low[i] = ' ';

        }
    }
    public static int getRow(Scanner sc){
        System.out.println("Enter the row you want to go in. (1 is top, 2 is mid, 3 is bottom)");
        int row;
        row = sc.nextInt();
        if (row == 1 || row == 2 || row == 3){
            return row;
        }
        else {
            return 0;}
    }
    public static int getCube(Scanner sc){
        System.out.println("Enter which cube you want to go in. (1 is left, 2 is mid, 3 is right)");
        int cube;
        cube = sc.nextInt();
        if (cube == 1 ){
            return 0;}
        else if (cube == 2){
            return 2;} 
        else if (cube == 3){
            return 4;}
        else {
            return -1;}
    }
    public static char turn(Scanner sc, char xo, char top[], char mid[], char low[]){
        System.out.println("\nIt is "+xo+"'s turn.\n");
        boolean breaker;

        while (breaker = true) {
            int row = getRow(sc);
            int cube = getCube(sc);
            if (cube == -1){
                System.out.println("\n Spot already taken or invalid input. Try again\n");
            }
            else if (row == 1){
                if (top[cube] == ' ') {
                    top[cube] = xo;
                    break;
                }
                else {System.out.println("\n Spot already taken or invalid input. Try again\n");}
            }
            else if (row == 2){
                if (mid[cube] == ' ') {
                    mid[cube] = xo;
                    break;
                }
                else {System.out.println("\n Spot already taken or invalid input. Try again\n");}
            }
            else if (row == 3){
                if (low[cube] == ' ') {
                    low[cube] = xo;
                    break;
                }
                else {System.out.println("\n Spot already taken or invalid input. Try again\n");}
            }                
            else {System.out.println("\n Spot already taken or invalid input. Try again\n");}

        }                
        printAll(top, mid, low);
        
        if (xo == 'X'){
            return 'O';
        }
        else if (xo == 'O'){
            return 'X';
        }
        else{
            return ' ';
        }
    }

    public static boolean checkWinner(char top[], char mid[], char low[],char ch){
        int one = 0, two = 2, three = 4;
        if (top[one] == ch && top[two] == ch && top[three] == ch){
            // top row horizontal
            return true;}
        else if (mid[one] == ch && mid[two] == ch && mid[three] == ch){
            // mid row horizontal
            return true;}
        else if (low[one] == ch && low[two] == ch && low[three] == ch){
            // low row horizontal
            return true;}
        else if (top[one] == ch && mid[one] == ch && low[one] == ch){
            // left row vertical
            return true;}
        else if (top[two] == ch && mid[two] == ch && low[two] == ch){
            // mid row vertical
            return true;}
        else if (top[three] == ch && mid[three] == ch && low[three] == ch){
            // right row vertical
            return true;}
        else if (top[one] == ch && mid[two] == ch && low[three] == ch){
            // left-right row diagonal
            return true;}
        else if (top[three] == ch && mid[two] == ch && low[one] == ch){
            // right-left row diagonal
            return true;}
        else {return false;}
    }

    public static boolean checkDraw(char top[], char mid[], char low[]){
        int one = 0, two = 2, three = 4;
        char ch = ' ';
        if (top[one] != ch && top[two] != ch && top[three] != ch && mid[one] != ch && mid[two] != ch && mid[three] != ch && low[one] != ch && low[two] != ch && low[three] != ch){
            return true;
        }
        return false;
    }
}
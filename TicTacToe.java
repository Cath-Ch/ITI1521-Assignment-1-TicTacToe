import java.io.Console;
import java.util.*;

public class TicTacToe {
    
    /**
     * Main program of the application. Creates the instance of TicTacToeGame 
     * and starts the game. If two parameters lines and columns
     * are passed, they are used. If the parameters lines, columns
     * and win are passed, they are used.
     * Otherwise, a default value is used. Defaults values (3) are also
     * used if the paramters are too small (less than 2).
     * Here, we assume that the command lines arguments are indeed integers.
     *
     * @param args
     *  command lines parameters
     */
     public static void main(String[] args) {

        StudentInfo.display();

        Console console = System.console();
        TicTacToeGame game;
        int lines, columns, win;
        lines = 6;
        columns = 5;
        win = 4;

        if (args.length >= 2) {
            lines = Integer.parseInt(args[0]);
            if(lines<2){
                System.out.println("Invalid argument, using default...");
                lines = 3;
            }
            columns = Integer.parseInt(args[1]);
            if(columns<2){
                System.out.println("Invalid argument, using default...");
                columns = 3;
            }
        }
        if (args.length >= 3){
            win = Integer.parseInt(args[2]);
            if(win<2){
                System.out.println("Invalid argument, using default...");
                win = 3;
            }
        } 
        if (args.length > 3){
            System.out.println("Too many arguments. Only the first 3 are used.");
        } 
        
        game = new TicTacToeGame(lines, columns, win);
        System.out.println(game.toString());
        System.out.println();

        while (game.getGameState() == GameState.PLAYING){
            Scanner scanner = new Scanner(System.in);
            System.out.print(game.nextCellValue() + " to play: ");
            int i = scanner.nextInt();
            game.play(i-1);

            System.out.println(game.toString());
            System.out.println();

            if (game.getGameState() == GameState.XWIN){
                System.out.println("Result: XWIN");
            } else if (game.getGameState() == GameState.OWIN) {
                System.out.println("Result: OWIN");
            }
        }
    }
}
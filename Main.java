import static java.lang.System.*;
import java.util.Scanner;
import java.io.IOException;

public class Main
{
    public static boolean gameOn = true;

    public static void main (String[] args)
    {
        Scanner keyboard = new Scanner(System.in);

        clearCLI();
        while (gameOn) {
            char myChar = keyboard.next().charAt(0);
            clearCLI();
            tick(myChar);
        }

    }

    public static void tick (char uInput)
    {
        System.out.println(gameOn);
    }

    public static void clearCLI ()
    {
        System.out.println("\n".repeat(69));
    }
}
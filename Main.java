import static java.lang.System.*;
import java.util.Scanner;

public class Main
{
    public static void main (String[] args)
    {
        Scanner keyboard = new Scanner(System.in);

        boolean gameOn = true;

        System.out.println("\n".repeat(123)); // clear console
        while (gameOn) {
            char myChar = keyboard.next().charAt(0);
            System.out.print("\n".repeat(123)); // clear console
            tick(myChar);
        }

    }

    public static void tick (char uInput)
    {
        System.out.println(uInput);
    }
}
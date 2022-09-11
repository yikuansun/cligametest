import static java.lang.System.*;
import java.util.Scanner;

public class Main
{
    public static void main (String[] args)
    {
        Scanner keyboard = new Scanner(System.in);

        char myChar;
		myChar = keyboard.next().charAt(0);
        System.out.println("char = " + myChar );

    }
}
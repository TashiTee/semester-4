import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LetterOccurance
{
    public static void main(String[] args) {

        System.out.print("Enter file name: ");
        File file = new File(new Scanner(System.in).next());

        if (!file.exists())
        {
            System.out.println(file + " doesn't exist");
            System.exit(1);
        }
        String buffer = "";
        int[] letterCount = new int[26];
        try (Scanner input = new Scanner(file))
        {
            // scanner has input
            while (input.hasNext())
            {
                buffer = input.nextLine();
                // converts the given strings into a sequence of characters using for each loop
                for (char ch : buffer.toCharArray())
                {
                    ch = Character.toUpperCase(ch);
                    if (isLetter(ch))
                    {
                        letterCount[ch - 'A']++;
                    }
                }
            }
        }
        catch (FileNotFoundException ex)
        {
            // helps trace the exception giving location of where exception occured
            ex.printStackTrace();
        }

        for (int i = 0; i < letterCount.length; i++)
        {
            System.out.println((char)(i + 'A') + " occurrence = " + letterCount[i]);
        }
    }

    private static boolean isLetter(char ch)
    {
        return (ch >= 'A' && ch <= 'z');
    }
}
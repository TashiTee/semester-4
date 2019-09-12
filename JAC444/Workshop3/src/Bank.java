

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Bank {

    static final int bankID = 0;
    static final int loan = 1;
    static double[][][] mBanks;
    public static void main(String[] args) {

        int n = -1;
        int limit = -1;
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("Number of banks:");
            try {
                n = input.nextInt();
                if (n<0 ) {
                    System.out.println("Not a valid number.");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Must enter an integer! Try again");
                input.next();   // discard the bad input
            }
        } while (n<0);
        System.out.println("You entered " + n);

        do {
            System.out.println("Enter minimum limit:");
            try {
                limit = input.nextInt();
                if (limit<0 ) {
                    System.out.println("Not a valid number.");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Must enter an integer! Try again");
                input.next();   // discard the bad input
            }
        } while (limit<0 );
        System.out.println("You entered " + limit);

        mBanks = new double[n][][];
        System.out.println("Enter the following information for each bank, in the same order as below:");
        System.out.println("Bank #  > Balance > Number of banks Loaned > 1st Bank ID > Amount > 2nd Bank ID > Amount > ...");
        for (int i = 0; i < mBanks.length; i++) {
            System.out.print("Bank #" + i + " > ");
            double balance = input.nextDouble();
            int numBanks = input.nextInt();
            mBanks[i] = new double[++numBanks][2];
            mBanks[i][0][0] = balance;
            for (int bank = 1; bank < mBanks[i].length; bank++) {
                mBanks[i][bank][bankID] = input.nextInt();
                mBanks[i][bank][loan] = input.nextDouble();
            }
        }
        System.out.println("");
        displayMatrix(mBanks);

        boolean[] unsafeIndex = scanBanks(mBanks, limit);
        List<Integer> unsafeArr = new ArrayList<Integer>();

        for (int i = 0; i < unsafeIndex.length; i++) {
            if (unsafeIndex[i] == true) {
                unsafeArr.add(i);
                System.out.println("Bank# " + i + ": unsafe");
            } else {
                System.out.println("Bank# " + i + ": safe");
            }
        }

        if (unsafeArr.size() == 0) {
            System.out.println("All banks are safe");
        } else if (unsafeArr.size() == 1) {
            System.out.print("The only unsafe bank is Bank " + unsafeArr.get(0));
        }
        else {
            System.out.print("***** Unsafe banks are");
            for (int j = 0; j < unsafeArr.size(); j++) {
                System.out.print(" Bank " + unsafeArr.get(j));
                if (j < unsafeArr.size()-1) {
                    System.out.print(" and");
                }
            }
            System.out.print(" *****");
        }

        input.close();
    }

    public static boolean[] scanBanks(double[][][] m, int limit) {

        boolean[] indexUnsafeBanks = new boolean[m.length];
        double total;
        boolean isSafe = false;
        while (!isSafe) {
            isSafe = true;
            for (int banks = 0; banks < m.length; banks++) {
                total = m[banks][0][0];
                for (int LoanedBanks = 1; LoanedBanks < m[banks].length; LoanedBanks++) {
                    int index = (int) m[banks][LoanedBanks][bankID];
                    if (!indexUnsafeBanks[index])
                        total += m[banks][LoanedBanks][loan];
                }
                if (total < limit && !indexUnsafeBanks[banks]) {
                    indexUnsafeBanks[banks] = true;
                    isSafe = false;
                }
            }
        }
        return indexUnsafeBanks;
    }

    public static void displayMatrix(double[][][] m) {

        System.out.printf("%-5s%-5s%-5s%-5s%-5s%-5s%-5s\n",
                "Bank #|", "Balance $|", "Loaned #|", "Bank ID|", "Amount $|", "Bank ID|", "Amount $|");
        for (int banks = 0; banks < m.length; banks++) {
            System.out.printf("%-6d|%9.2f|%8d|", banks, m[banks][0][0], m[banks].length -1);

            for (int LoanedBanks = 1; LoanedBanks < m[banks].length; LoanedBanks++) {
                System.out.printf("%7.0f|%8.2f|", m[banks][LoanedBanks][bankID], m[banks][LoanedBanks][loan]);
            }
            System.out.println("");
        }
    }
}
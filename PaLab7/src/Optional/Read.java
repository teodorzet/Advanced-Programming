package Optional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Read {
    public static int numberToReturn;

    public Read() {

    }

    public static void readNumber(int maxNumber) throws IOException {
        Scanner reader = new Scanner(System.in);
        System.out.print("Insert the index: ");
        String number = reader.nextLine();

        while (Integer.parseInt(number) >= maxNumber && Integer.parseInt(number) >= 0){
            System.out.println("The index is incorrect.");
            System.out.print("Insert the index: ");
            number = reader.nextLine();
        }

        numberToReturn = Integer.parseInt(number);
    }
}

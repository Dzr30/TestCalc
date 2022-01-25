package Calc;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String calculatedString = new Scanner(System.in).nextLine();
        System.out.println(Calculate.calc(calculatedString));
    }

}

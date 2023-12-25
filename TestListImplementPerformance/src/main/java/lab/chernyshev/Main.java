package lab.chernyshev;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество операций:");
        int numOperations=scanner.nextInt();
        PerformanceComparison.runTests(numOperations);
    }
}

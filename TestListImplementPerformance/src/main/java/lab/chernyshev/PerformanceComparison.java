package lab.chernyshev;
import java.util.ArrayList;
import java.util.LinkedList;


public class PerformanceComparison {
    public static void runTests(int numOperations) {
        System.out.println("Method\t\t\t\t\tTimes Executed\t\tExecution Time (ms)");
        System.out.println("-------------------------------------------------------------");

        testAdd(numOperations);
        testDelete(numOperations);
        testGet(numOperations);
    }

    public static void testAdd(int numOperations) {
        long startTime, endTime, executionTime;

        ArrayList<Integer> arrayList = new ArrayList<>();
        startTime = System.nanoTime();
        for (int i = 0; i < numOperations; i++) {
            arrayList.add(i);
        }
        endTime = System.nanoTime();
        executionTime = (endTime - startTime) / 1_000_000;
        System.out.println("ArrayList.add()\t\t\t" + numOperations + "\t\t\t\t" + executionTime);

        LinkedList<Integer> linkedList = new LinkedList<>();
        startTime = System.nanoTime();
        for (int i = 0; i < numOperations; i++) {
            linkedList.add(i);
        }
        endTime = System.nanoTime();
        executionTime = (endTime - startTime) / 1_000_000;
        System.out.println("LinkedList.add()\t\t" + numOperations + "\t\t\t\t" + executionTime);
    }

    public static void testDelete(int numOperations) {
        long startTime, endTime, executionTime;

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < numOperations; i++) {
            arrayList.add(i);
        }
        startTime = System.nanoTime();
        for (int i = numOperations - 1; i >= 0; i--) {
            arrayList.remove(i);
        }
        endTime = System.nanoTime();
        executionTime = (endTime - startTime) / 1_000_000;
        System.out.println("ArrayList.delete()\t\t" + numOperations + "\t\t\t\t" + executionTime);

        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < numOperations; i++) {
            linkedList.add(i);
        }
        startTime = System.nanoTime();
        for (int i = numOperations - 1; i >= 0; i--) {
            linkedList.remove(i);
        }
        endTime = System.nanoTime();
        executionTime = (endTime - startTime) / 1_000_000;
        System.out.println("LinkedList.delete()\t\t" + numOperations + "\t\t\t\t" + executionTime);
    }

    public static void testGet(int numOperations) {
        long startTime, endTime, executionTime;

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < numOperations; i++) {
            arrayList.add(i);
        }
        startTime = System.nanoTime();
        for (int i = 0; i < numOperations; i++) {
            arrayList.get(i);
        }
        endTime = System.nanoTime();
        executionTime = (endTime - startTime) / 1_000_000;
        System.out.println("ArrayList.get()\t\t\t" + numOperations + "\t\t\t\t" + executionTime);

        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < numOperations; i++) {
            linkedList.add(i);
        }
        startTime = System.nanoTime();
        for (int i = 0; i < numOperations; i++) {
            linkedList.get(i);
        }
        endTime = System.nanoTime();
        executionTime = (endTime - startTime) / 1_000_000;
        System.out.println("LinkedList.get()\t\t" + numOperations + "\t\t\t\t" + executionTime);
    }
}

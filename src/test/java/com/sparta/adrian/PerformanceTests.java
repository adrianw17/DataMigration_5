package com.sparta.adrian;

import com.sparta.adrian.controller.Starter;
import com.sparta.adrian.view.Printer;
import org.testng.annotations.Test;
import org.junit.jupiter.api.DisplayName;

public class PerformanceTests {
    private static String smallFilePath = "resources/employees.csv";
    private static String largeFilePath = "resources/EmployeeRecordsLarge.csv";

    @Test
    @DisplayName("Small CSV, 0 Threads, not batched")
    public void timeTaken1(){
        long start = System.nanoTime();
        Starter.migration(smallFilePath, 0, false);
        long end = System.nanoTime();
        double totalTime = (double) (end - start) / 1000000000.0;
        Printer.printMessage("Small CSV, 0 Threads, not batched Time taken: " + totalTime);
    }

    @Test
    @DisplayName("Small CSV, 50 Threads, not batched")
    public void timeTaken2(){
        long start = System.nanoTime();
        Starter.migration(smallFilePath, 100, false);
        long end = System.nanoTime();
        double totalTime = (double) (end - start) / 1000000000.0;
        Printer.printMessage("Small CSV, 50 Threads, not batched Time taken: " + totalTime);
    }

    @Test
    @DisplayName("Small CSV, 50 Threads, batched")
    public void timeTaken3(){
        long start = System.nanoTime();
        Starter.migration(smallFilePath, 100, true);
        long end = System.nanoTime();
        double totalTime = (double) (end - start) / 1000000000.0;
        Printer.printMessage("Small CSV, 50 Threads, batched Time taken: " + totalTime);
    }

    @Test
    @DisplayName("Small CSV, 100 Threads, not batched")
    public void timeTaken4(){
        long start = System.nanoTime();
        Starter.migration(smallFilePath, 100, false);
        long end = System.nanoTime();
        double totalTime = (double) (end - start) / 1000000000.0;
        Printer.printMessage("Small CSV, 100 Threads, not batched Time taken: " + totalTime);
    }

    @Test
    @DisplayName("Small CSV, 100 Threads, batched")
    public void timeTaken5(){
        long start = System.nanoTime();
        Starter.migration(smallFilePath, 100, true);
        long end = System.nanoTime();
        double totalTime = (double) (end - start) / 1000000000.0;
        Printer.printMessage("Small CSV, 100 Threads, batched Time taken: " + totalTime);
    }

    @Test
    @DisplayName("Large CSV, 50 Threads, not batched")
    public void timeTaken6(){
        long start = System.nanoTime();
        Starter.migration(largeFilePath, 100, false);
        long end = System.nanoTime();
        double totalTime = (double) (end - start) / 1000000000.0;
        Printer.printMessage("Large CSV, 50 Threads, not batched Time taken: " + totalTime);
    }

    @Test
    @DisplayName("Large CSV, 50 Threads, batched")
    public void timeTaken7(){
        long start = System.nanoTime();
        Starter.migration(largeFilePath, 100, true);
        long end = System.nanoTime();
        double totalTime = (double) (end - start) / 1000000000.0;
        Printer.printMessage("Large CSV, 50 Threads, batched Time taken: " + totalTime);
    }

    @Test
    @DisplayName("Large CSV, 100 Threads, not batched")
    public void timeTaken8(){
        long start = System.nanoTime();
        Starter.migration(largeFilePath, 100, false);
        long end = System.nanoTime();
        double totalTime = (double) (end - start) / 1000000000.0;
        Printer.printMessage("Large CSV, 100 Threads, not batched Time taken: " + totalTime);
    }

    @Test
    @DisplayName("Large CSV, 100 Threads, batched")
    public void timeTaken9(){
        long start = System.nanoTime();
        Starter.migration(smallFilePath, 100, true);
        long end = System.nanoTime();
        double totalTime = (double) (end - start) / 1000000000.0;
        Printer.printMessage("Large CSV, 100 Threads, batched Time taken: " + totalTime);
    }
}

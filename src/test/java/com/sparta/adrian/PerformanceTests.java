package com.sparta.adrian;

import com.sparta.adrian.controller.Starter;
import com.sparta.adrian.view.Printer;
import org.testng.annotations.Test;
import org.junit.jupiter.api.DisplayName;

public class PerformanceTests {
    private static String smallFilePath = "resources/employees.csv";
    private static String largeFilePath = "resources/EmployeeRecordsLarge.csv";


    @Test
    @DisplayName("Small CSV, 50 Threads")
    public void timeTaken3(){
        long start = System.nanoTime();
        Starter.migration(smallFilePath, 100);
        long end = System.nanoTime();
        double totalTime = (double) (end - start) / 1000000000.0;
        Printer.printMessage("Small CSV, 50 Threads Time taken: " + totalTime);
    }


    @Test
    @DisplayName("Small CSV, 100 Threads")
    public void timeTaken5(){
        long start = System.nanoTime();
        Starter.migration(smallFilePath, 100);
        long end = System.nanoTime();
        double totalTime = (double) (end - start) / 1000000000.0;
        Printer.printMessage("Small CSV, 100 Threads Time taken: " + totalTime);
    }

    @Test
    @DisplayName("Large CSV, 50 Threads")
    public void timeTaken7(){
        long start = System.nanoTime();
        Starter.migration(largeFilePath, 100);
        long end = System.nanoTime();
        double totalTime = (double) (end - start) / 1000000000.0;
        Printer.printMessage("Large CSV, 50 Threads Time taken: " + totalTime);
    }

    @Test
    @DisplayName("Large CSV, 100 Threads")
    public void timeTaken9(){
        long start = System.nanoTime();
        Starter.migration(smallFilePath, 100);
        long end = System.nanoTime();
        double totalTime = (double) (end - start) / 1000000000.0;
        Printer.printMessage("Large CSV, 100 Threads Time taken: " + totalTime);
    }
}

package com.sparta.adrian.controller;

import com.sparta.adrian.model.EmployeeDAO;
import com.sparta.adrian.model.EmployeeDTO;
import com.sparta.adrian.view.Logging;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {
    private static final EmployeeDAO employeeDAO = new EmployeeDAO();
    private static final List<Thread> threads = new ArrayList<>();
    private static final Map<String, EmployeeDTO> employeeRecords = new HashMap<>();
    private static final Map<String, EmployeeDTO> employeeRecordsBatch = new HashMap<>();
    private static final List<EmployeeDTO> employeeRecordsDuplicates = new ArrayList<>();

    public static void read(String filePath, int numThreads, boolean isBatched) {
        String row;
        boolean isFirstLine = true;
        boolean isThreaded = numThreads >= 1;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            long numRows = Files.lines(Paths.get(filePath)).count() - 1;

            while ((row = bufferedReader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                addToEmployeeRecords(row);

                if (isThreaded) {
                    if (employeeRecordsBatch.size() > numRows / numThreads) {
                       sendBatch(isBatched);
                        while (threads.size() > 0) {
                            threads.removeIf(thread -> !thread.isAlive());
                        }
                    }
               }
            }
            if (isThreaded) {
                sendBatch(isBatched);
                while (threads.size() > 0) {
                    threads.removeIf(thread -> !thread.isAlive());
                }
          } else {
              employeeDAO.insertIntoTable(employeeRecords, isBatched);
            }
            bufferedReader.close();
            Logging.traceLog("File read successful");
        } catch (IOException e) {
            e.printStackTrace();
            Logging.errorLog("File read unsuccessful");
        }
    }

    private static void sendBatch(boolean isBatched) {
        Object object = new Object();
        Runnable runnable = () -> {
            synchronized (object) {
                employeeDAO.insertIntoTable(employeeRecordsBatch, isBatched);
                employeeRecordsBatch.clear();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        threads.add(thread);
    }

    private static void addToEmployeeRecords(String row) {
        String[] employeeRow = row.split(",");
        EmployeeDTO employeeDTO = new EmployeeDTO(employeeRow);

        EmployeeDTO hasBeenPut = employeeRecords.putIfAbsent(employeeRow[0], employeeDTO);
        if (hasBeenPut == null) {
            employeeRecordsBatch.put(employeeRow[0], employeeDTO);
        } else {
            employeeRecordsDuplicates.add(employeeDTO);
            Logging.traceLog("empId " + employeeRow[0] + " added to duplicates");
        }
    }
}

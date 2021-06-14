package com.sparta.adrian.controller;

import com.sparta.adrian.model.EmployeeDAO;

public class Starter {
    public static void start() {
        Starter.migration("resources/employees.csv", 100, false);
        //Starter.migration("resources/EmployeeRecordsLarge.csv", 100, false);
    }

    public static void migration(String filePath, int noOfThreads, boolean isBatched) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.createEmployeesTable();
        CSVReader.read(filePath, noOfThreads, isBatched);
    }
}

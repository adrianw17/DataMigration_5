package com.sparta.adrian.model;

import com.sparta.adrian.view.Logging;
import java.io.FileReader;
import java.sql.*;
import java.util.Map;
import java.util.Properties;

public class EmployeeDAO {
    private final String URL = "jdbc:mysql://localhost:3306/mylocal?serverTimezone=GMT";
    private static Properties properties = new Properties();
    private Connection connection = null;
    private Map<String, EmployeeDTO> employeeRecords = null;
    private boolean isBatched = false;
    private static final String CREATE_TABLE = "CREATE TABLE employees (" +
            "empId VARCHAR(10) NOT NULL PRIMARY KEY,\n" +
            "namePrefix VARCHAR(5),\n" +
            "firstName VARCHAR(25),\n" +
            "middleInitial CHAR(1),\n" +
            "lastName VARCHAR(25),\n" +
            "gender CHAR(1),\n" +
            "email VARCHAR(50),\n" +
            "dateOfBirth DATE,\n" +
            "dateOfJoin DATE,\n" +
            "salary INTEGER" +
            ");";
    private static final String INSERT_INTO = "INSERT INTO employees " +
            "(empId, namePrefix, firstName, middleInitial, lastName, gender, email, dateOfBirth, dateOfJoin, salary) " +
            "VALUES(?,?,?,?,?,?,?,?,?,?);";
    private static final String TRUNCATE_TABLE = "TRUNCATE employees";


    private Connection connectToDatabase() {
        try {
            properties.load(new FileReader("resources/login.properties"));
            connection = DriverManager.getConnection(URL, properties.getProperty("username"), properties.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Logging.errorLog("Connection unsuccessful");
            e.printStackTrace();
        }
        return connection;
    }

    private void disconnectFromDatabase() {
        try {
            connection.close();
        } catch (SQLException e) {
            Logging.errorLog("Disconnection unsuccessful");
            e.printStackTrace();
        }
    }

    public void createEmployeesTable() {
        try {
            if (connection == null || connection.isClosed()) {
                connectToDatabase();
            }
            if (employeesTableExists()) {
                truncateTable();
                Logging.traceLog("Table already exists");
            } else {
                createTable();
                Logging.traceLog("Table created");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logging.errorLog("Table creation unsuccessful");
        } finally {
            disconnectFromDatabase();
        }
    }

    private boolean employeesTableExists() {
        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, null, "employees", null);
            if (resultSet.next()) {
                Logging.traceLog("Table already exists");
                return true;
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Logging.errorLog("Database failure");
        }
        return false;
    }

    private void createTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeLargeUpdate(CREATE_TABLE);
        statement.close();
    }

    private void truncateTable() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(TRUNCATE_TABLE);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void insertIntoTable(Map<String, EmployeeDTO> employeeRecords) {
        this.employeeRecords = employeeRecords;
        try {
            if (connection == null || connection.isClosed()) {
                connectToDatabase();
            }
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO);
            for (EmployeeDTO employee : this.employeeRecords.values()) {
                insertIntoQuery(employee, preparedStatement);
            }
            preparedStatement.executeBatch();
            connection.commit();

            preparedStatement.close();
            Logging.traceLog("Data successfully inserted into table");
        } catch (SQLException e) {
            e.printStackTrace();
            Logging.errorLog("Data insertion unsuccessful");
        } finally {
            disconnectFromDatabase();
        }
    }

    private void insertIntoQuery(EmployeeDTO employee, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, employee.getEmpId());
        preparedStatement.setString(2, employee.getNamePrefix());
        preparedStatement.setString(3, employee.getFirstName());
        preparedStatement.setString(4, employee.getMiddleInitial());
        preparedStatement.setString(5, employee.getLastName());
        preparedStatement.setString(6, String.valueOf(employee.getGender()));
        preparedStatement.setString(7, employee.getEmail());
        preparedStatement.setDate(8, employee.getDateOfBirth());
        preparedStatement.setDate(9, employee.getDateOfJoining());
        preparedStatement.setInt(10, employee.getSalary());


        preparedStatement.addBatch();
        //preparedStatement.executeUpdate();

    }
}

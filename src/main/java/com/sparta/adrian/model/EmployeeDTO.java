package com.sparta.adrian.model;

import static com.sparta.adrian.model.DateFormat.formatDate;
import static com.sparta.adrian.model.DateFormat.parseDate;

public class EmployeeDTO {
    private String empId;
    private String namePrefix;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private char gender;
    private String email;
    private java.sql.Date dateOfBirth;
    private java.sql.Date dateOfJoining;
    private int salary;

    public EmployeeDTO(String[] columns) {
        this.empId = columns[0];
        this.namePrefix = columns[1];
        this.firstName = columns[2];
        this.middleInitial = columns[3];
        this.lastName = columns[4];
        this.gender = columns[5].charAt(0);
        this.email = columns[6];
        this.dateOfBirth = parseDate(columns[7]);
        this.dateOfJoining = parseDate(columns[8]);
        this.salary = Integer.parseInt(columns[9]);
    }

    public String getEmpId() {
        return empId;
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public char getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public java.sql.Date getDateOfBirth() {
        return dateOfBirth;
    }

    public java.sql.Date getDateOfJoining() {
        return dateOfJoining;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "" +
                "Employee{" +
                "empId='" + empId + '\'' +
                ", namePrefix='" + namePrefix + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleInitial='" + middleInitial + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", dob='" +  formatDate(dateOfBirth) + '\'' +
                ", joinDate='" + formatDate(dateOfJoining) + '\'' +
                ", salary=£" + salary +
                '}';
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.sampleapp.controller;

import com.leapfrog.sampleapp.entity.Employee;
import com.leapfrog.sampleapp.entity.EmployeeSalary;
import com.leapfrog.sampleapp.entity.dao.EmployeeDAO;
import com.leapfrog.sampleapp.entity.dao.EmployeeSalaryDAO;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author HP B&O
 */
public class EmployeeController {

    private EmployeeDAO employeeDAO;
    private EmployeeSalaryDAO employeeSalaryDAO;
    private Scanner input;

    public EmployeeController(EmployeeDAO employeeDAO, Scanner input) {
        this.employeeDAO = employeeDAO;
        this.input = input;
    }

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public void menu() {
        System.out.println("1. Add Employee");
        System.out.println("2. Show ALl Employees");
        System.out.println("3. Ad Salary");
        System.out.println("4. Report");
        System.out.println("5. Salary Sheet by Employee Code");
        System.out.println("6. Export Sales Report");
        System.out.println("8. Exit");
        System.out.println("Enter your choice:");
    }

    public void addView() {
        while (true) {
            Employee emp = new Employee();
            emp.setId(employeeDAO.insertId());
            System.out.println("Enter code:");
            emp.setCode(input.next());
            System.out.println("Enter first name:");
            emp.setFirstName(input.next());
            System.out.println("Enter last name:");
            emp.setLastName(input.next());
            System.out.println("Enter Email:");
            emp.setEmail(input.next());
            System.out.println("Enter Contact No:");
            emp.setContactNo(input.next());
            System.out.println("Enter Salary:");
            emp.setSalary(input.nextInt());
            System.out.println("Enter Status:");
            emp.setStatus(true);

            employeeDAO.insert(emp);
            System.out.println("Do you want to add more?[y/n]:");
            if (input.next().equalsIgnoreCase("n")) {
                break;
            }
        }
    }

    public void showEmployeeView() {
        System.out.println("---------------------");
        System.out.println("Employees");
        System.out.println("---------------------");
        for (Employee e : employeeDAO.getAll()) {
            System.out.println("Id: " + e.getId());
            System.out.println("Code: " + e.getCode());
            System.out.println("Name: " + e.getFullName());
            System.out.println("Email: " + e.getEmail());
            System.out.println("Contact No: " + e.getContactNo());
            System.out.println("Status: " + e.isStatus());
            System.out.println("---------------------");
        }
    }

    public void addSalaryView() {
        while (true) {
            System.out.println("Enter employee code:");
            Employee emp = employeeDAO.getByCode(input.next());
            if (emp == null) {
                System.out.println("Employee code not found.");
            } else {
                System.out.println("Code:" + emp.getCode());
                System.out.println("Name:" + emp.getFullName());
                System.out.println("Salary:" + emp.getSalary());
                System.out.println("Do you want to add salary?[Y/N]");
                if (input.next().equalsIgnoreCase("Y")) {
                    EmployeeSalary empSalary = new EmployeeSalary();
                    empSalary.setEmployee(emp);
                    empSalary.setSalary(emp.getSalary());
                    empSalary.setSalaryDate(new Date());
                    employeeSalaryDAO.insert(empSalary);
                }
            }//if emp==null
            System.out.println("Do you want to add another?[y/n]:");
            if (input.next().equalsIgnoreCase("n")) {
                break;
            }
        }//end while
    }

    public void salaryReportView() {
        System.out.println("--------------------");
        System.out.println("        Salary Report");
        for (EmployeeSalary es : employeeSalaryDAO.getAll()) {
            Employee e = es.getEmployee();
            System.out.print(e.getCode() + "\t");
            System.out.println(e.getFullName() + "\t");
            System.out.print(es.getSalaryDate() + "\t");
            System.out.println(es.getSalary());
            System.out.println("---------------------F");
        }
    }

    public void exportSalaryView() {
        try {
            System.out.println("Enter file name:");
            String file = input.next();
            FileWriter writer = new FileWriter(file);
            System.out.println("Exporting into file " + file);
            StringBuilder content = new StringBuilder();
            for (EmployeeSalary es : employeeSalaryDAO.getAll()) {
                Employee e = es.getEmployee();
                content.append(e.getCode()).append(",");
                content.append(e.getFullName()).append(",");
                content.append(es.getSalary()).append(",");
                content.append(es.getSalaryDate()).append("\r\n");
            }
            writer.write(content.toString());
            writer.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public void process() {
        menu();
        switch (input.nextInt()) {
            case 1:
                addView();
                break;
            case 2:
                showEmployeeView();
                break;
            case 3:
                addSalaryView();
                break;
            case 4:
                salaryReportView();
                break;
            case 6:
                exportSalaryView();
                break;
            case 8:
                System.exit(0);
                break;
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.sampleapp;

import com.leapfrog.sampleapp.controller.EmployeeController;
import com.leapfrog.sampleapp.entity.dao.impl.EmployeeDAOImpl;
import java.util.Scanner;

/**
 *
 * @author HP B&O
 */
public class Pogram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        EmployeeController ec = new EmployeeController(new EmployeeDAOImpl(), input);
        ec.setEmployeeDAO(new EmployeeDAOImpl());
        while (true) {
            ec.process();
        }
        
    }
    
}

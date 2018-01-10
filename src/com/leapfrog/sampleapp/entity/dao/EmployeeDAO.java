/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.sampleapp.entity.dao;

import com.leapfrog.sampleapp.entity.Employee;
import java.util.List;

/**
 *
 * @author HP B&O
 */
public interface EmployeeDAO {

    List<Employee> getAll();

    Employee getByCode(String code);

    Employee getById(int id);

    boolean insert(Employee emp);

    int insertId();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.sampleapp.entity.dao.impl;

import com.leapfrog.sampleapp.entity.Employee;
import com.leapfrog.sampleapp.entity.dao.EmployeeDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP B&O
 */
public class EmployeeDAOImpl implements EmployeeDAO {

    private List<Employee> empList = new ArrayList<>();

    @Override
    public List<Employee> getAll() {
        return empList;
    }

    @Override
    public Employee getByCode(String code) {
        for (Employee e : empList) {
            if (e.getCode().equalsIgnoreCase(code)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public Employee getById(int id) {
        for (Employee e : empList) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    @Override
    public boolean insert(Employee emp) {
        return empList.add(emp);
    }

    @Override
    public int insertId() {
        int count = empList.size();
        return (count == 0) ? 1 : empList.get(count - 1).getId() + 1;
    }

}

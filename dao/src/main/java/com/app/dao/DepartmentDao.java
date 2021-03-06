package com.app.dao;

import com.app.model.Department;
import com.app.model.Employee;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by andrei on 15.09.16.
 */

public interface DepartmentDao {

    public void addDepartment(Department department, String username);

    public String deleteDepartmentById(long id, String username);

    public void editDepartment(Department department, String username);

    public List<Department> getAllDepartments();

    public Department getDepartmentById(long id);

    public List<Employee> getEmployeesBySelectedDepartment(long id);
}

package com.enjoy.controller;

import com.enjoy.dao.DepartmentDao;
import com.enjoy.dao.EmployeeDao;
import com.enjoy.pojo.Department;
import com.enjoy.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    //列举所有的员工
    @RequestMapping("emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";

    }
    @GetMapping("/emp")
    public String toaddpage(Model model){
        Collection<Department> department = departmentDao.getDepartment();
        model.addAttribute("department",department);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id") Integer id,Model model){
        Employee employeeById = employeeDao.getEmployeeById(id);
        Collection<Department> department = departmentDao.getDepartment();
        model.addAttribute("department",department);
        //数据返回给前端
        model.addAttribute("emp",employeeById);
        return "emp/update";
    }
    @RequestMapping("/updateEmp")
    public String updateEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    //删除员工
    @GetMapping("/deleteEmp/{id}")
    public String deleteEmp(@PathVariable("id") int id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}

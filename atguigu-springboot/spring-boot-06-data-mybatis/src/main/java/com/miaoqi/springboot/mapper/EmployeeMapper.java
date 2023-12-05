package com.miaoqi.springboot.mapper;

import com.miaoqi.springboot.bean.Employee;

// @Mapper 或者 MapperScan 的方式扫描到接口装配到容器中
public interface EmployeeMapper {

    public Employee getEmpById(Integer id);

    public void insertEmp(Employee employee);

}

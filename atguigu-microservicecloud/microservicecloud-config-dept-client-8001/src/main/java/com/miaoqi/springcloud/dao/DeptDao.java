package com.miaoqi.springcloud.dao;

import com.miaoqi.springcloud.entities.Dept;

import java.util.List;

public interface DeptDao {
    public boolean addDept(Dept dept);

    public Dept findById(Long id);

    public List<Dept> findAll();
}

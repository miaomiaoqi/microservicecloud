package com.miaoqi.cache.mapper;

import org.apache.ibatis.annotations.Select;

import com.miaoqi.cache.bean.Department;

public interface DepartmentMapper {

    @Select("SELECT * FROM department WHERE id = #{id}")
    Department getDeptById(Integer id);
}

package com.atguigu.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crud.bean.Department;
import com.atguigu.crud.dao.DepartmentMapper;

@Service
public class DepartmentService {
	
	
	@Autowired
	DepartmentMapper departmentMapper;
	
	

	public List<Department> getDepts() {
		// TODO Auto-generated method stub
		return  departmentMapper.selectByExample(null);
	}

}

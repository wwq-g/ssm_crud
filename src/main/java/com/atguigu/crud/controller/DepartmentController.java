package com.atguigu.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crud.bean.Department;
import com.atguigu.crud.bean.Msg;
import com.atguigu.crud.service.DepartmentService;

@Controller
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;
	
	
	@RequestMapping("/depts")
	@ResponseBody
	public Msg getDepts() {
		
		List<Department> depts=departmentService.getDepts();
		
		return Msg.success().add("depts", depts);
		
	}

}

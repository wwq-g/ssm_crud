package com.atguigu.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.bean.EmployeeExample;
import com.atguigu.crud.bean.EmployeeExample.Criteria;
import com.atguigu.crud.dao.EmployeeMapper;

@Service
public class EmployeeService {

	@Autowired
	EmployeeMapper employeeMapper;

	/*
	 * ��ѯ����Ա��
	 */
	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return employeeMapper.selectByExampleWithDept(null);
	}

	/**
	 * ��������
	 * 
	 * @param employee
	 */
	public void saveEmp(Employee employee) {
		// TODO Auto-generated method stub
		employeeMapper.insertSelective(employee);

	}

	/**
	 * ��֤�û��Ƿ��ظ�
	 * 
	 * @param empName
	 * @return 0 true, !0 false
	 */

	public boolean checkUser(String empName) {
		// TODO Auto-generated method stub
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count = employeeMapper.countByExample(example);
		return count == 0;
	}

	/**
	 * ����Ա��id��ѯԱ��
	 * 
	 * @param id
	 * @return
	 */
	public Employee getEmp(Integer id) {
		// TODO Auto-generated method stub
		return employeeMapper.selectByPrimaryKey(id);
	}

	public void updateEmp(Employee employee) {
		// TODO Auto-generated method stub
		employeeMapper.updateByPrimaryKeySelective(employee);

	}

	/**
	 * ����ɾ��
	 * 
	 * @param id
	 */
	public void deleteEmp(Integer id) {
		// TODO Auto-generated method stub
		employeeMapper.deleteByPrimaryKey(id);
	}

	/**
	 * ����ɾ��
	 * 
	 * @param ids
	 */
	public void delteBatch(List<Integer> ids) {
		EmployeeExample example = new EmployeeExample();
		Criteria creatia = example.createCriteria();
		// delete from xx where emp_id in (1,2,3)
		creatia.andEmpIdIn(ids);
		employeeMapper.deleteByExample(example);

	}

}

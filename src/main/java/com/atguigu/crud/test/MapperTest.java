package com.atguigu.crud.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atguigu.crud.bean.Department;
import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.dao.DepartmentMapper;
import com.atguigu.crud.dao.EmployeeMapper;

/*
 * ����dao��Ĺ���
 * 
 * �Ƽ�Spring����Ŀ�Ϳ���ʹ��Spring�ĵ�Ԫ���ԣ������Զ�ע��������Ҫ�����
 * 1.����SpringTestģ��
 * 2.@ContextConfigurationָ��Spring�����ļ���λ��
 * 3.ֱ��autowiredҪʹ�õ��������
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {

	@Autowired
	DepartmentMapper departmentMapper;
	
	@Autowired
	EmployeeMapper emloyeeMapper;
	
	@Autowired
	SqlSession sqlSession;
	/*
	 * ����DepartmentMapper
	 */
	@Test
	public void testCRUD() {
		//1. ����SpringIOC����
//		ApplicationContext ioc =new ClassPathXmlApplicationContext("applicationContext.xml");
//		//2.�������л�ȡmapper
//		DepartmentMapper bean = ioc.getBean(DepartmentMapper.class);
		
		System.out.println(departmentMapper);
		
		
		
		//1.���뼸������
		departmentMapper.insertSelective(new Department(null,"������"));
		departmentMapper.insertSelective(new Department(null,"���Բ�"));
		
		
		//2.����Ա�����ݣ�����Ա������
		emloyeeMapper.insertSelective(new Employee(null,"wwq","M","123@qq.com",3));
		emloyeeMapper.insertSelective(new Employee(null,"ww","M","123@qq.com",4));
		
		
		//�����������Ա������������ִ������������SqlSession
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		for(int i= 0;i<500;i++) {
			String uid = UUID.randomUUID().toString().substring(0,5)+i;
			mapper.insertSelective(new Employee(null,uid,"M",uid+"@123.com",1));
		}
		System.out.print("������ɣ�");
	}
	
}

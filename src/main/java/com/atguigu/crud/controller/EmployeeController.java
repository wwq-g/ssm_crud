package com.atguigu.crud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.bean.Msg;
import com.atguigu.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 处理员工CRUD请求
 * 
 * @author Administrator
 *
 */
@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		PageHelper.startPage(pn, 5);
		// startPage后面紧跟的这个查询就是一个分页查询
		List<Employee> emps = employeeService.getAll();
		// 使用pageInfo包装查询后的结果，只需要将PageInfo交给页面就行了
		// 封装了详细的分页信息，包括有为我们查询出来的数据,传入连续显示的页数

		PageInfo pageInfo = new PageInfo(emps, 5);
		return Msg.success().add("pageInfo", pageInfo);
	}

	/**
	 * 保存员工
	 * 
	 * @return
	 */
	@RequestMapping(value = "/emp", method = RequestMethod.POST)
	@ResponseBody
	public Msg savaEmp(@Valid Employee employee, BindingResult result) {
		if (result.hasErrors()) {
			// 检验失败，返回失败，在模态框中显示检证错误信息
			Map<String, Object> map = new HashMap<>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
				System.out.println("错误字段名" + fieldError.getField());
				System.out.println("错误信息" + fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		} else {
			employeeService.saveEmp(employee);
			return Msg.success();

		}

	}

	/**
	 * 检查用户名是否可用
	 * 
	 * @param empName
	 * @return
	 */
	@RequestMapping(value = "/checkuser")
	@ResponseBody
	public Msg checkuser(@RequestParam("empName") String empName) {
		String regx = "(^[A-Za-z0-9]{3,10}$)|(^[\\u2E80-\\u9FFF]{2,5}$)";
		if (!empName.matches(regx)) {
			return Msg.fail().add("va_msg", "名字必须是2-5个中文或者3-16位英文数字组合！！！");
		}
		boolean b = employeeService.checkUser(empName);

		if (b) {
			return Msg.success();
		} else {
			return Msg.fail().add("va_msg", "用户名不可用");
		}

	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id") Integer id) {

		Employee employee = employeeService.getEmp(id);

		return Msg.success().add("emp", employee);
	}

	@ResponseBody
	@RequestMapping(value = "/emp/{empId}", method = RequestMethod.PUT)
	public Msg updateEmp(Employee employee) {
		employeeService.updateEmp(employee);
		return Msg.success();
	}

	@ResponseBody
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	public Msg deleteEmp(@PathVariable("ids")String ids) {
		//批量删除
		if(ids.contains("-")) {
			List<Integer> del_ids = new ArrayList<>();
			String[] str_id = ids.split("-");
			for (String string : str_id) {
				del_ids.add(Integer.parseInt(string));
			}
			employeeService.delteBatch(del_ids);
		}else {
			//单个删除
			Integer id = Integer.parseInt(ids);
			employeeService.deleteEmp(id);
		}
				
		return Msg.success();
	}

	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 查询员工数据（分页查询）
	 * 
	 * @return
	 */

//	@RequestMapping("/emps")
//	public String getEmps(@RequestParam(value="pn",defaultValue = "1")Integer pn,Model model) {
//		//这不是一个分页查询，
//		//引入PageHelper分页插件
//		//在查询之前只需要调用,传入页码，以及每页的大小
//		PageHelper.startPage(pn, 5);
//		//startPage后面紧跟的这个查询就是一个分页查询
//		List<Employee> emps = employeeService.getAll();
//		//使用pageInfo包装查询后的结果，只需要将PageInfo交给页面就行了
//		//封装了详细的分页信息，包括有为我们查询出来的数据,传入连续显示的页数
//		
//		PageInfo pageInfo = new PageInfo(emps,5);
//		model.addAttribute("pageInfo",pageInfo);
//		
//		return "list"; 
//	}
}

package com.example.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.domain.Employee;
import com.example.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	/**
	 * ======================= DI ========================
	 */
	@Autowired
	EmployeeService employeeService;

	/**
	 * ======================== 実行メソッド ========================
	 */

	// 社員情報全件表示
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView viewEmployees() {
		List<Employee> employees = employeeService.findAll();
		ModelAndView mv = new ModelAndView("list");
		mv.addObject("employees", employees);
		return mv;
	}

	// 社員情報更新ページ
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public ModelAndView viewEmployee(@PathVariable int id, Model model) {
		Optional<Employee> employee = employeeService.findOne(id);
		
		ModelAndView mv = new ModelAndView("update");
		mv.addObject("employee", employee.get());
		return mv;
	}

	// 社員情報更新
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateEmployee(@ModelAttribute Employee employee) {
		//System.out.println("updateEmployee : " + employee);
		employeeService.update(employee);
		return "redirect:/employees/list";
	}
	
	// 新規社員情報追加ページ
	@RequestMapping(value =  "/insert", method = RequestMethod.GET)
	public String viewEmployeeInsert() {
		return "insert";
	}

	// 新規社員情報追加
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertEmployee(@ModelAttribute Employee employee) {
		
		employee.setHiredate(LocalDateTime.now());
		employeeService.insert(employee);
		return "redirect:/employees/list";
	}
	
	// 社員情報削除
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delEmployee(HttpServletRequest request) {
		String id = request.getParameter("id");
		employeeService.delete(Integer.parseInt(id));
		return "redirect:/employees/list";
	}
}

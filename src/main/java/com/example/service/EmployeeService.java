package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	/**=======================
	 * DI
	 *========================*/
	@Autowired
	EmployeeRepository employeeRepository;
	
	/**========================
	 * メソッド
	 *========================*/
	// 社員情報全件取得
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}
	
	// 社員情報取得
	public Optional<Employee> findOne(int id) {
		return employeeRepository.findById(id);
	}
	
	// 新規社員情報追加
	public void insert(Employee employee) {
		System.out.println("insert : " + employee);
		employeeRepository.save(employee);
	}
	
	// 社員情報更新
	public void update(Employee employee) {
		employeeRepository.save(employee);
	}
	
	// 社員情報削除
	public void delete(int id) {
		employeeRepository.deleteById(id);
	}

	public Long getCount() {
		Long count = employeeRepository.count();
		return count;
	}

	
	

}

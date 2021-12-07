package com.employee.sample.project.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Page<Employee> getAllEmployees(Optional<Integer> page, Optional<Integer> offset) {
		return employeeRepository.findAll(PageRequest.of(page.orElse(0), offset.orElse(5)));
	}

	public List<Employee> getAllEmployeesByDepartment(Long departmentId) {
		return employeeRepository.findByDepartmentId(departmentId);
	}

	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Employee getEmployee(Long employeeId) {
		return employeeRepository.findById(employeeId).orElse(null);
	}

	public Employee getEmployee(Long employeeId , Long departmentId) {
		return employeeRepository.findByEmployeeIdAndDepartmentId(employeeId, departmentId);
	}

	public Employee updateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public void deleteEmployee(Long employeeId) {
		employeeRepository.deleteById(employeeId);
	}

}

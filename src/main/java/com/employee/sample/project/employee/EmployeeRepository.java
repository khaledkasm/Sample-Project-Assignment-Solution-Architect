package com.employee.sample.project.employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	public List<Employee> findByDepartmentId(Long departmentId);
	
	public Employee findByEmployeeIdAndDepartmentId(Long employeeId, Long departmentId);
}

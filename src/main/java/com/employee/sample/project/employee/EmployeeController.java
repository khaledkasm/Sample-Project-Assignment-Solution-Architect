package com.employee.sample.project.employee;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.sample.project.exception.ResourceNotFoundException;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Operation(description="List all employees")
	@GetMapping("/employees")
	public Page<Employee> getAllEmployees(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> offset) {
		return employeeService.getAllEmployees(page, offset);
	}
	
	@Operation(description="Get an employee by Id")
	@GetMapping("/employees/{empId}")
	public ResponseEntity<Employee> getEmployee(@PathVariable(name="empId") Long employeeId) throws ResourceNotFoundException {
		Employee employee = employeeService.getEmployee(employeeId);
		
		if(employee != null)
			return ResponseEntity.ok().body(employee);
		else 
			throw new ResourceNotFoundException("There is no Employee with Id: " + employeeId);
	}
	
	@Operation(description="Create a new employee")
	@PostMapping("/employees/")
	public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeService.addEmployee(employee), HttpStatus.CREATED);		
	}
	
	@Operation(description="Update an employee by Id")
	@PutMapping("/employees/{employeeId}")
	public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee employeeDetails, @PathVariable Long employeeId) throws ResourceNotFoundException {
		Employee employee = employeeService.getEmployee(employeeId);
		if(employee != null) {
			employee.setFirst_name(employeeDetails.getFirst_name());
			employee.setLast_name(employeeDetails.getLast_name());
			employee.setEmail(employeeDetails.getEmail());
			employee.setDepartmentId(employeeDetails.getDepartmentId());
			employee.setHire_date(employeeDetails.getHire_date());
			employee.setManager_id(employeeDetails.getManager_id());
			employee.setPhone_number(employeeDetails.getPhone_number());
			employee.setSalary(employeeDetails.getSalary());
			return new ResponseEntity<Employee>(employeeService.updateEmployee(employee), HttpStatus.OK);
		}
		else
			throw new ResourceNotFoundException("There is no employee with id: " + employeeId);
	}

	@Operation(description="Delete an employee by Id")
	@DeleteMapping("/employees/{employeeId}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeId) throws ResourceNotFoundException {
		Employee employee = employeeService.getEmployee(employeeId);
		
		if(employee != null) {
			employeeService.deleteEmployee(employeeId);
			return ResponseEntity.ok().build();
		}
		else
			throw new ResourceNotFoundException("There is no employee with id: " + employeeId);
	}
	
	/*
	@GetMapping("/departments/{departmentId}/employees/{employeeId}")
	public Employee getEmployee(@PathVariable Long departmentId, @PathVariable Long employeeId) {
		return employeeService.getEmployee(employeeId, departmentId);
	}
	
	@GetMapping("/departments/{depId}/employees/")
	public List<Employee> getAllEmployeesByDepartment(@PathVariable(name="depId") Long departmentId) {
		return employeeService.getAllEmployeesByDepartment(departmentId);
	}
	
	
	
	@PostMapping("/departments/{depId}/employees/")
	public Employee addEmployee(@RequestBody Employee employee, @PathVariable(name="depId") Long departmentId) {
		employee.setDepartmentId(departmentId);
		return employeeService.addEmployee(employee);
				
	}
	
		
	@PutMapping("/departments/{departmentId}/employees/{employeeId}")
	public Employee updateEmployee(@RequestBody Employee employeeDetails, @PathVariable Long departmentId, @PathVariable Long employeeId) throws ResourceNotFoundException {
		Employee employee = employeeService.getEmployee(employeeId, departmentId);
		if(employee != null) {
			employee.setFirst_name(employeeDetails.getFirst_name());
			employee.setLast_name(employeeDetails.getLast_name());
			employee.setEmail(employeeDetails.getEmail());
			employee.setDepartmentId(employeeDetails.getDepartmentId());
			employee.setHire_date(employeeDetails.getHire_date());
			employee.setManager_id(employeeDetails.getManager_id());
			employee.setPhone_number(employeeDetails.getPhone_number());
			employee.setSalary(employeeDetails.getSalary());
			return employeeService.updateEmployee(employee);
		}
		else
			throw new ResourceNotFoundException("");
	}
	*/
}

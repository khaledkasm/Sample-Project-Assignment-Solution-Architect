package com.employee.sample.project.department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.sample.project.exception.ResourceNotFoundException;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;
	
	@Operation(description="List all departments")
	@GetMapping("/departments")
	public List<Department> getAllDepartments() {
		return departmentService.getAllDepartments();
	}
	
	
	@Operation(description="Get department by Id")
	@GetMapping("/departments/{id}")
	public Department getDepartment(@PathVariable Long id) throws ResourceNotFoundException {
		Department department = departmentService.getDepartment(id);

		if(department != null)
			return department;
		else
			throw new ResourceNotFoundException("There is no department with id: " + id);
	}

	@Operation(description="Create a new department")
	@PostMapping("/departments")
	public Department addDepartment(@RequestBody Department department) {
		return departmentService.addDepartment(department);
	}
	
	@Operation(description="Update a department")
	@PutMapping("/departments/{id}")
	public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department departmentDetails) throws ResourceNotFoundException {
		
		Department department = departmentService.getDepartment(id);

		if(department != null) {
			department.setDepartment_name(departmentDetails.getDepartment_name());
			department.setManager_id(departmentDetails.getManager_id());
			return ResponseEntity.ok().body(departmentService.updateDepartment(department));
		}
		else
			throw new ResourceNotFoundException("There is no department with id: " + id);
	}
	
	@Operation(description="Delete a department by Id")
	@DeleteMapping("/departments/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Long id) throws ResourceNotFoundException {
		Department department = departmentService.getDepartment(id);

		if(department != null) {
			departmentService.deleteDepartment(id);
			return ResponseEntity.ok().build();
		}
		else
			throw new ResourceNotFoundException("There is no department with id: " + id);
		
	}
}

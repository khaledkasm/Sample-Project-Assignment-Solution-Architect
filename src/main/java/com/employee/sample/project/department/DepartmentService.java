package com.employee.sample.project.department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public List<Department> getAllDepartments(){
		return departmentRepository.findAll();
	}

	public Department addDepartment(Department department) {
		return departmentRepository.save(department);
	}

	public Department getDepartment(Long id) {
		return departmentRepository.findById(id).orElse(null);
	}

	public Department updateDepartment(Department department) {
		return departmentRepository.save(department);
	}
	
	public void deleteDepartment(Long id) {
		departmentRepository.deleteById(id);
	}
	
}

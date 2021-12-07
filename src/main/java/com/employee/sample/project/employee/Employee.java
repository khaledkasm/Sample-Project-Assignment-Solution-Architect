package com.employee.sample.project.employee;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.ReadOnlyProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="employee_id")
	@ReadOnlyProperty
	private Long employeeId;
	
	@Column(nullable=false)
	@NotEmpty
	@Size(min=2, message="First name should have at least 2 characters!")
	private String first_name;
	
	@Column(nullable=false)
	@NotEmpty
	@Size(min=2, message="Last name should have at least 2 characters!")
	private String last_name;
	
	@Email
	private String email;
	
	@Pattern(regexp="^[0-9-]*$", message="Phone number should contain only digits and dashs")
	private String phone_number;
	private Date hire_date;
	
	
	@Min(value=1, message="Minimum Salary is 1")
	private Long salary;
	private Long manager_id;
	@Column(name="department_id")
	private Long departmentId;
}

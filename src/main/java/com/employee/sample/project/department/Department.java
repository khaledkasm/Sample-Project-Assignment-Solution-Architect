package com.employee.sample.project.department;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Departments")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long department_id;
	private String department_name;
	private Long manager_id;
}

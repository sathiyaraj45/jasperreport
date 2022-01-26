/**
 * 
 */
package com.sathiya.jasperreport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sathiyaraj Ramamurthy Jan 26, 2022 11:09:50 AM
 *
 */
@Entity
@Table(name = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
	@Column(name = "emp_name")
	private String employeeName;
	
	@Column(name = "emp_first_name")
	private String employeeFirstName;
	
	@Column(name = "emp_last_name")
	private String employeeLastName;
	
	@Transient
	private String fullName;
	
	@Column(name = "emp_roll_number")
	private String employeeRollNumber;
	
	@Column(name = "emp_email")
	private String employeeEmail;
	
	@Column(name = "emp_mobile")
	private String employeeMobile;

	public String getFullName() {
		return this.getEmployeeFirstName() +" "+this.getEmployeeLastName();
	}
}

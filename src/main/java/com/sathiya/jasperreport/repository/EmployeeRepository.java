/**
 * 
 */
package com.sathiya.jasperreport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sathiya.jasperreport.entity.Employee;

/**
 * @author Sathiyaraj Ramamurthy Jan 26, 2022 11:19:25 AM
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}

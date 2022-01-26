/**
 * 
 */
package com.sathiya.jasperreport.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sathiya.jasperreport.entity.Employee;
import com.sathiya.jasperreport.service.EmployeeService;

import net.sf.jasperreports.engine.JRException;

/**
 * @author Sathiyaraj Ramamurthy Jan 26, 2022 11:22:49 AM
 *
 */
@RestController
@RequestMapping("employee")
public class EmployeeControlller {
	
	@Autowired
	private EmployeeService empServ;
	
	@GetMapping("/")
	public @ResponseBody List<Employee> getAllEmployee() {
		List<Employee> list = empServ.getEmployeeList();
		return list;
	}
	
	@GetMapping(value= "/report/pdf", produces = {"application/pdf"})
	public @ResponseBody byte[] generateReportPDF() throws JRException, IOException {
		String rep = empServ.exportReport("pdf");
		File file = ResourceUtils.getFile(rep);
		InputStream in = new FileInputStream(file);
		return IOUtils.toByteArray(in);
	}

	@GetMapping(value= "/report/html", produces = {"application/json"})
	public @ResponseBody byte[] generateReportHTML() throws JRException, IOException {
		String rep = empServ.exportReport("html");
		File file = ResourceUtils.getFile(rep);
		InputStream in = new FileInputStream(file);
		return IOUtils.toByteArray(in);
	}
	
}

/**
 * 
 */
package com.sathiya.jasperreport.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.sathiya.jasperreport.entity.Employee;
import com.sathiya.jasperreport.repository.EmployeeRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Sathiyaraj Ramamurthy Jan 26, 2022 11:20:31 AM
 *
 */
@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	public List<Employee> getEmployeeList() {
		return empRepo.findAll();
	}
	
	public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
		List<Employee> empList = empRepo.findAll();
		File file = ResourceUtils.getFile("classpath:employee.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(empList);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Created By", "Sathiyaraj Ramamurthy");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, source);
		Path root = FileSystems.getDefault().getPath("").toAbsolutePath();
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, root.toString()+"/employee.html");
		} else if (reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, root.toString()+"/employee.pdf");
		}

		JRCsvExporter export = new JRCsvExporter();
		export.setExporterInput(new SimpleExporterInput(jasperPrint));
		export.setExporterOutput(new SimpleWriterExporterOutput(root.toString()+"/employee.cvs"));
		export.exportReport();
		return root.toString()+"/employee."+ reportFormat;
	}
}

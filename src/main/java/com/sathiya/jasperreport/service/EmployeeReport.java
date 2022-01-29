package com.sathiya.jasperreport.service;

import com.sathiya.jasperreport.entity.Employee;
import com.sathiya.jasperreport.repository.EmployeeRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Sathiyaraj created on 28-01-2022
 * <p>
 * TODO
 */

@Service
public class EmployeeReport extends JasperReportPreparation {

    @Autowired
    private EmployeeRepository empRepo;

    @Override
    public JasperPrint printingObject() throws FileNotFoundException, JRException {
        List<Employee> list = empRepo.findAll();
        File file = ResourceUtils.getFile("classpath:employee.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(list);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("Created By", "Sathiyaraj Ramamurthy");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, source);
        return jasperPrint;
    }
}

package com.sathiya.jasperreport.service;

import com.sathiya.jasperreport.entity.Employee;
import com.sathiya.jasperreport.repository.EmployeeRepository;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author Sathiyaraj created on 28-01-2022
 * <p>
 * TODO
 */


public class EmployeeReport extends JasperReportPreparation{

    @Autowired
    private EmployeeRepository empRepo;

    @Override
    public JasperPrint printingObject() {
        List<Employee> list = empRepo.findAll();

        return null;
    }
}

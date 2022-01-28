package com.sathiya.jasperreport.service;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;

import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * @Author Sathiyaraj created on 28-01-2022
 * <p>
 * TODO
 */


abstract public class JasperReportPreparation {
    JasperPrint jasperPrint = null;
    Path root = FileSystems.getDefault().getPath("").toAbsolutePath();
    abstract public JasperPrint printingObject();

    public String exportPDF(String fileName) {
        jasperPrint = printingObject();
        JRCsvExporter jrCsvExporter = new JRCsvExporter();
        jrCsvExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        String filePath = root+"/"+fileName+".pdf";
        jrCsvExporter.setExporterOutput(new SimpleWriterExporterOutput(filePath));
        return filePath;
    }
}

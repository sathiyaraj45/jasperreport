package com.sathiya.jasperreport.service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;

import java.io.FileNotFoundException;
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
    abstract public JasperPrint printingObject() throws FileNotFoundException, JRException;

    public String exportPDF(String fileName) throws JRException, FileNotFoundException {
        jasperPrint = printingObject();
        JRPdfExporter jrPdfExporter = new JRPdfExporter();
        jrPdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        String filePath = root.toString()+"/"+fileName+".pdf";
        jrPdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(filePath));
        jrPdfExporter.exportReport();
        return filePath;
    }

    public String exportCVS(String fileName) throws JRException, FileNotFoundException {
        jasperPrint = printingObject();
        JRCsvExporter jrCsvExporter = new JRCsvExporter();
        jrCsvExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        String filePath = root.toString() + "/"+fileName+".cvs";
        jrCsvExporter.setExporterOutput(new SimpleWriterExporterOutput(filePath));
        jrCsvExporter.exportReport();
        return filePath;
    }
}

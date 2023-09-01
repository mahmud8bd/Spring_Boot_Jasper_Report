package com.sazzad.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import com.sazzad.model.Product;
import com.sazzad.repo.ProductRepo;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class JasperReportService {
	
	@Autowired
	private ProductRepo productrepo;
	
	public void exportJasperReport(HttpServletResponse response) throws JRException, IOException {
        List<Product> product = productrepo.findAll();
        
        //Get file and compile it
        File file = ResourceUtils.getFile("classpath:Flower.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(product);
        
        
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("name", "Spring Boot Jasper Report");
        parameter.put("title", "Developed By Sazzad");
        parameter.put("h", product);
        
        
        //Fill Jasper report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, dataSource);
        //Export report
        JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream());
    }
}

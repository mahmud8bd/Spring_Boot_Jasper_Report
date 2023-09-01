package com.sazzad.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sazzad.service.JasperReportService;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

@RestController
public class JasperReportController {
	@Autowired
	private JasperReportService service;
	
	@GetMapping("/report")
    public void createPDF(HttpServletResponse response) throws IOException, JRException {
       response.setContentType("application/pdf");
       DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
       String currentDateTime = dateFormatter.format(new Date());
  
       String headerKey = "Content-Disposition";
       String headerValue = "attachment; filename=sazzad_" + currentDateTime + ".pdf";
       response.setHeader(headerKey, headerValue);
  
        service.exportJasperReport(response);
    }
}

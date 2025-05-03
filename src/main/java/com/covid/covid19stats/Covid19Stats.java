package com.covid.covid19stats;

import com.covid.covid19stats.model.Report;
import com.covid.covid19stats.service.ReportService;
import java.time.LocalDate;
import java.util.TreeMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableScheduling
public class Covid19Stats {
    public static void main(String[] args) {
        SpringApplication.run(Covid19Stats.class, args);
    }
}

@Component
class Covid19Runner implements CommandLineRunner {

    private static final Logger logger = LogManager.getLogger(Covid19Runner.class);

    @Value("${covid19stats.default-iso}")
    private String defaultIso;

    @Value("${covid.report.date}")
    private String reportDateString;

    private final ReportService reportService;

    public Covid19Runner(ReportService reportService) {
        this.reportService = reportService;
    }

    @Override
    public void run(String... args) throws Exception {
        LocalDate fecha = LocalDate.parse(reportDateString);
        
        // Verificar si el reporte ya existe
        if (reportService.reportAlreadyExists(defaultIso, fecha)) {
            logger.info("Report already exists for ISO={} and date={}. Process canceled.", defaultIso, fecha);
            return;
        }
        
        logger.info("\n===== Loading Reports for {} =====\n", defaultIso);
        
        try {
            // Obtener y mostrar reportes agrupados
            TreeMap<String, Report> groupedReports = reportService.getReportsByDateAndIso(fecha, defaultIso);
            
            // Exportar reporte agrupado
            String exportFilename = "grouped_report_" + defaultIso + "_" + fecha + ".txt";
            reportService.exportGroupedReports(fecha, defaultIso, exportFilename);
            
            logger.info("\n===== Successfully processed reports =====\n");
            logger.info("Grouped report exported to: {}", exportFilename);
            
        } catch (ReportService.ReportProcessingException e) {
            logger.error("\n===== ERROR PROCESSING REPORTS =====\n", e);
            throw e;
        }
    }
}
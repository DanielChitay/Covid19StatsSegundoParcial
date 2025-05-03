package com.covid.covid19stats.config;

import com.covid.covid19stats.model.Report;
import com.covid.covid19stats.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.TreeMap;

@Component
public class ReportGenerationTask implements Task {

    @Autowired private ReportService reportService;

    @Override
    public void execute(String countryIso, LocalDate date) {
        String filename = "auto_generated_report_" + countryIso + "_" + date + ".txt";
        TreeMap<String, Report> reports = reportService.getReportsByDateAndIso(date, countryIso);
        reportService.exportGroupedReports(date, countryIso, filename);
    }
}
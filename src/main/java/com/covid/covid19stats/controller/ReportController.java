/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.covid.covid19stats.controller;
import com.covid.covid19stats.model.Report;
import com.covid.covid19stats.service.ReportService;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author rodol
 */
@RestController
@RequestMapping("/api/reports")
public class ReportController {
    
    @Autowired
    private ReportService reportService;
    
    @GetMapping("/by-date-and-iso")
    public ResponseEntity<Map<String, Report>> getReportsByDateAndIso(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam String iso) {
        
        TreeMap<String, Report> reports = reportService.getReportsByDateAndIso(date, iso);
        return ResponseEntity.ok(reports);
    }
}
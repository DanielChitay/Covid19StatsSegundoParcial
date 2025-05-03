package com.covid.covid19stats.config;

import com.covid.covid19stats.service.ProvinceService;
import com.covid.covid19stats.service.RegionService;
import com.covid.covid19stats.service.ReportService;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataLoadingTask implements Task {

    @Autowired private RegionService regionService;
    @Autowired private ProvinceService provinceService;
    @Autowired private ReportService reportService;

    @Override
    public void execute(String countryIso, LocalDate date) {
        regionService.fetchAndSaveRegions();
        provinceService.fetchAndSaveProvinces(countryIso);
        reportService.fetchAndSaveReports(countryIso);
    }
}
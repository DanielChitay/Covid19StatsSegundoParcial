package com.covid.covid19stats.config;

import com.covid.covid19stats.model.ExecutedReport;
import com.covid.covid19stats.repository.ExecutedReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class ExecutionLogger {

    @Autowired private ExecutedReportRepository repository;

    public boolean hasBeenExecuted(LocalDate date, String countryIso) {
        return repository.findByExecutionDateAndCountryIso(date, countryIso).isPresent();
    }

    public void logExecution(LocalDate date, String countryIso, boolean success) {
        ExecutedReport executed = new ExecutedReport();
        executed.setExecutionDate(date);
        executed.setCountryIso(countryIso);
        executed.setSuccess(success);
        repository.save(executed);
    }
}
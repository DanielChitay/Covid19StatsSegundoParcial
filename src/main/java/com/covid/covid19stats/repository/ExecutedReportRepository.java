package com.covid.covid19stats.repository;

import com.covid.covid19stats.model.ExecutedReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ExecutedReportRepository extends JpaRepository<ExecutedReport, Long> {
    Optional<ExecutedReport> findByExecutionDateAndCountryIso(LocalDate date, String iso);
    boolean existsByExecutionDateAndCountryIso(LocalDate date, String iso);
}
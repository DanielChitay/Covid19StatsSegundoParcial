package com.covid.covid19stats.repository;

import com.covid.covid19stats.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

    List<Report> findByIso(String iso);
    List<Report> findByDateAndIso(LocalDate date, String iso);  // Cambiado de fecha a date
    boolean existsByDateAndIso(LocalDate date, String iso);     // Cambiado de fecha a date
}
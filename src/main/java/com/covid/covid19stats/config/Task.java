package com.covid.covid19stats.config;

import java.time.LocalDate;

public interface Task {
    void execute(String countryIso, LocalDate date) throws Exception;
}
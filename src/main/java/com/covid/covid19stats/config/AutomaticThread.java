package com.covid.covid19stats.config;

import com.covid.binarytree.TreeNode;
import com.covid.binarytree.TreePathFinder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class AutomaticThread {

    private static final Logger logger = LogManager.getLogger(AutomaticThread.class);

    @Value("${covid19stats.default-iso}") 
    private String defaultIso;

    @Value("${covid.report.date}") 
    private String reportDateString;

    @Autowired 
    private ExecutionLogger executionLogger;

    @Autowired 
    private List<Task> tasks; // Spring inyecta todas las implementaciones de Task

    @Scheduled(initialDelay = 15000, fixedDelay = Long.MAX_VALUE)
    public void autoRunCovidDataLoad() {
        LocalDate reportDate = LocalDate.parse(reportDateString);

        if (executionLogger.hasBeenExecuted(reportDate, defaultIso)) {
            logger.info("Execution already registered for {} on {}", defaultIso, reportDate);
            return;
        }

        try {
            tasks.forEach(task -> {
                try {
                    task.execute(defaultIso, reportDate);
                } catch (Exception e) {
                    logger.error("Error executing task {}: {}", task.getClass().getSimpleName(), e.getMessage());
                    throw new RuntimeException(e);
                }
            });
            executionLogger.logExecution(reportDate, defaultIso, true);
            logger.info("Execution completed successfully for {} on {}", defaultIso, reportDate);
        } catch (Exception e) {
            executionLogger.logExecution(reportDate, defaultIso, false);
            logger.error("Critical error during execution: {}", e.getMessage());
        }
        TreeNode root = new TreeNode(5, 
            new TreeNode(4, 
                new TreeNode(11, new TreeNode(7), new TreeNode(2)), 
                null),
            new TreeNode(8, 
                new TreeNode(13), 
                new TreeNode(4, new TreeNode(5), new TreeNode(1)))
        );
        
        List<List<Integer>> paths = new TreePathFinder().findPaths(root, 22);
        logger.info("Rutas del árbol encontradas: {}", paths);
    }
}
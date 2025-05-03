package com.covid.covid19stats.controller;

import com.covid.binarytree.TreeNode;
import com.covid.covid19stats.service.TreeAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TreeAnalysisController {
    
    @Autowired
    private TreeAnalysisService treeAnalysisService;
    
    @GetMapping("/api/tree/paths")
    public List<List<Integer>> findTreePaths(
            @RequestParam(defaultValue = "22") int targetSum) {
        
        TreeNode covidTree = treeAnalysisService.buildCovidDataTree();
        return treeAnalysisService.analyzeTreePaths(covidTree, targetSum);
    }
}
package com.covid.covid19stats.service;

import com.covid.binarytree.TreeNode;
import com.covid.binarytree.TreePathFinder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeAnalysisService {
    
    private final TreePathFinder treePathFinder;
    
    public TreeAnalysisService() {
        this.treePathFinder = new TreePathFinder();
    }
    
    public List<List<Integer>> analyzeTreePaths(TreeNode root, int targetSum) {
        return treePathFinder.findPaths(root, targetSum);
    }
    
    // Método de ejemplo para construir el árbol del COVID
    public TreeNode buildCovidDataTree() {
        // Aquí puedes construir un árbol basado en tus datos de COVID
        // Por ejemplo: valores podrían ser tasas de infección por región
        TreeNode root = new TreeNode(100); // Tasa nacional
        root.left = new TreeNode(80);      // Región A
        root.right = new TreeNode(120);    // Región B
        // ... añadir más nodos según tus datos
        return root;
    }
}
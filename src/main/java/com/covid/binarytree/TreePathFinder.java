package com.covid.binarytree;


import java.util.ArrayList;
import java.util.List;

public class TreePathFinder {
    
    public List<List<Integer>> findPaths(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        findPathsRecursive(root, targetSum, new ArrayList<>(), result);
        return result;
    }
    
    private void findPathsRecursive(TreeNode node, int remainingSum, 
                                  List<Integer> currentPath, List<List<Integer>> result) {
        if (node == null) return;
        
        currentPath.add(node.val);
        
        if (node.left == null && node.right == null && remainingSum == node.val) {
            result.add(new ArrayList<>(currentPath));
        } else {
            findPathsRecursive(node.left, remainingSum - node.val, currentPath, result);
            findPathsRecursive(node.right, remainingSum - node.val, currentPath, result);
        }
        
        currentPath.remove(currentPath.size() - 1);
    }
}
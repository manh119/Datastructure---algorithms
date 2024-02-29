import java.util.ArrayList;
import java.util.List;

class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        List<Integer> result = new ArrayList<>();
        result.

    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public static void main(String[] args) {
        // Input: root = [5,3,6,2,4,null,7], key = 3
        // Output: [5,4,6,2,null,null,7]
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);
        root = new Solution().deleteNode(root, 3);

    }
}
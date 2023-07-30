1.// Solution


public class UniqueBST {
    public int numUniqueBSTs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1; // Base case, there is one unique BST with no nodes.

        for (int i = 1; i <= n; i++) {
            // Calculate C(i) using the recurrence relation
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        UniqueBST uniqueBST = new UniqueBST();

        int n1 = 3;
        System.out.println(uniqueBST.numUniqueBSTs(n1)); // Output: 5

        int n2 = 1;
        System.out.println(uniqueBST.numUniqueBSTs(n2)); // Output: 1
    }
}



2.// Solution


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class ValidateBinarySearchTree {
    TreeNode prev; // To keep track of the previously visited node during inorder traversal

    public boolean isValidBST(TreeNode root) {
        prev = null;
        return isValidInorder(root);
    }

    private boolean isValidInorder(TreeNode node) {
        if (node == null) {
            return true;
        }

        // Check the left subtree
        if (!isValidInorder(node.left)) {
            return false;
        }

        // Check the current node (inorder property)
        if (prev != null && node.val <= prev.val) {
            return false;
        }
        prev = node;

        // Check the right subtree
        return isValidInorder(node.right);
    }

    public static void main(String[] args) {
        // Example 1
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(3);

        ValidateBinarySearchTree validateBST = new ValidateBinarySearchTree();
        System.out.println(validateBST.isValidBST(root1)); // Output: true

        // Example 2
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(4);
        root2.right.left = new TreeNode(3);
        root2.right.right = new TreeNode(6);

        System.out.println(validateBST.isValidBST(root2)); // Output: false
    }
}



3.// Solution


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class RecoverBinarySearchTree {
    private TreeNode firstMisplaced;
    private TreeNode secondMisplaced;
    private TreeNode prev;

    public void recoverTree(TreeNode root) {
        firstMisplaced = null;
        secondMisplaced = null;
        prev = null;

        // Find the misplaced nodes using inorder traversal
        inorderTraversal(root);

        // Swap the values of the misplaced nodes
        if (firstMisplaced != null && secondMisplaced != null) {
            int temp = firstMisplaced.val;
            firstMisplaced.val = secondMisplaced.val;
            secondMisplaced.val = temp;
        }
    }

    private void inorderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }

        inorderTraversal(node.left);

        // Check if the current node is misplaced (not in ascending order)
        if (prev != null && prev.val > node.val) {
            if (firstMisplaced == null) {
                firstMisplaced = prev;
            }
            secondMisplaced = node;
        }

        prev = node;

        inorderTraversal(node.right);
    }

    public static void main(String[] args) {
        // Example 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(3);
        root1.left.right = new TreeNode(2);

        RecoverBinarySearchTree recoverBST = new RecoverBinarySearchTree();
        recoverBST.recoverTree(root1);

        // Validate the recovered BST using inorder traversal
        inorderTraversal(root1);
        System.out.println(isValidBST(root1)); // Output: true

        // Example 2
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(4);
        root2.right.left = new TreeNode(2);

        recoverBST.recoverTree(root2);

        // Validate the recovered BST using inorder traversal
        inorderTraversal(root2);
        System.out.println(isValidBST(root2)); // Output: true
    }

    // Helper method to check if a binary tree is a valid binary search tree (BST)
    private static boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean isValidBSTHelper(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }

        if (node.val <= lower || node.val >= upper) {
            return false;
        }

        return isValidBSTHelper(node.left, lower, node.val) && isValidBSTHelper(node.right, node.val, upper);
    }
}



4.// Solution


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class SortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        return constructBST(nums, 0, nums.length - 1);
    }

    private TreeNode constructBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // Find the middle element and make it the root
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        // Recursively build the left and right subtrees
        root.left = constructBST(nums, left, mid - 1);
        root.right = constructBST(nums, mid + 1, right);

        return root;
    }

    // Helper method to print the tree in a pre-order traversal (for validation)
    private static void printTree(TreeNode root) {
        if (root == null) {
            System.out.print("null ");
            return;
        }
        System.out.print(root.val + " ");
        printTree(root.left);
        printTree(root.right);
    }

    public static void main(String[] args) {
        // Example 1
        int[] nums1 = {-10, -3, 0, 5, 9};
        SortedArrayToBST sortedArrayToBST = new SortedArrayToBST();
        TreeNode root1 = sortedArrayToBST.sortedArrayToBST(nums1);
        printTree(root1); // Output: [0, -3, 9, -10, null, 5]

        // Example 2
        int[] nums2 = {1, 3};
        TreeNode root2 = sortedArrayToBST.sortedArrayToBST(nums2);
        printTree(root2); // Output: [3, 1]
    }
}



5.// Solution


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class LowestCommonAncestorBST {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // If both nodes are smaller than the root, search in the left subtree
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        // If both nodes are larger than the root, search in the right subtree
        else if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        // If one node is smaller and the other is larger, the current root is the LCA
        else {
            return root;
        }
    }

    public static void main(String[] args) {
        // Example 1
        TreeNode root1 = new TreeNode(6);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(8);
        root1.left.left = new TreeNode(0);
        root1.left.right = new TreeNode(4);
        root1.right.left = new TreeNode(7);
        root1.right.right = new TreeNode(9);
        root1.left.right.left = new TreeNode(3);
        root1.left.right.right = new TreeNode(5);

        LowestCommonAncestorBST lcaBST = new LowestCommonAncestorBST();
        TreeNode result1 = lcaBST.lowestCommonAncestor(root1, new TreeNode(2), new TreeNode(8));
        System.out.println(result1.val); // Output: 6

        // Example 2
        TreeNode root2 = new TreeNode(6);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(8);
        root2.left.left = new TreeNode(0);
        root2.left.right = new TreeNode(4);
        root2.right.left = new TreeNode(7);
        root2.right.right = new TreeNode(9);
        root2.left.right.left = new TreeNode(3);
        root2.left.right.right = new TreeNode(5);

        TreeNode result2 = lcaBST.lowestCommonAncestor(root2, new TreeNode(2), new TreeNode(4));
        System.out.println(result2.val); // Output: 2

        // Example 3
        TreeNode root3 = new TreeNode(2);
        root3.left = new TreeNode(1);

        TreeNode result3 = lcaBST.lowestCommonAncestor(root3, new TreeNode(2), new TreeNode(1));
        System.out.println(result3.val); // Output: 2
    }
}



6.// Solution


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class InsertIntoBST {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            // If the current position is empty, insert the new node here
            return new TreeNode(val);
        }

        if (val < root.val) {
            // If the value is smaller, insert into the left subtree
            root.left = insertIntoBST(root.left, val);
        } else {
            // If the value is larger, insert into the right subtree
            root.right = insertIntoBST(root.right, val);
        }

        return root;
    }

    public static void main(String[] args) {
        // Example 1
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(7);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(3);

        InsertIntoBST insertBST = new InsertIntoBST();
        TreeNode result1 = insertBST.insertIntoBST(root1, 5);
        printTree(result1); // Output: [4,2,7,1,3,5]

        // Example 2
        TreeNode root2 = new TreeNode(40);
        root2.left = new TreeNode(20);
        root2.right = new TreeNode(60);
        root2.left.left = new TreeNode(10);
        root2.left.right = new TreeNode(30);
        root2.right.left = new TreeNode(50);
        root2.right.right = new TreeNode(70);

        TreeNode result2 = insertBST.insertIntoBST(root2, 25);
        printTree(result2); // Output: [40,20,60,10,30,50,70,null,null,25]

        // Example 3
        TreeNode root3 = new TreeNode(4);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(7);
        root3.left.left = new TreeNode(1);
        root3.left.right = new TreeNode(3);

        TreeNode result3 = insertBST.insertIntoBST(root3, 5);
        printTree(result3); // Output: [4,2,7,1,3,5]
    }

    // Helper method to print the tree in a pre-order traversal (for validation)
    private static void printTree(TreeNode root) {
        if (root == null) {
            System.out.print("null ");
            return;
        }
        System.out.print(root.val + " ");
        printTree(root.left);
        printTree(root.right);
    }
}



7.// Solution


import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class SameBSTOrder {
    private static final int MOD = 1_000_000_007;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        Map<TreeNode, Integer> structure = new HashMap<>();
        TreeNode root = buildBST(list, structure);

        int[] count = {1};
        countWays(root.left, structure, count);
        countWays(root.right, structure, count);

        return count[0] - 1;
    }

    private TreeNode buildBST(List<Integer> nums, Map<TreeNode, Integer> structure) {
        if (nums.isEmpty()) {
            return null;
        }

        int rootValue = nums.remove(0);
        TreeNode root = new TreeNode(rootValue);
        int leftSize = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) < rootValue) {
                leftSize++;
            } else {
                break;
            }
        }

        List<Integer> leftSubtree = nums.subList(0, leftSize);
        List<Integer> rightSubtree = nums.subList(leftSize, nums.size());

        root.left = buildBST(leftSubtree, structure);
        root.right = buildBST(rightSubtree, structure);

        int leftCount = getCount(root.left, structure);
        int rightCount = getCount(root.right, structure);
        structure.put(root, 1 + leftCount + rightCount);

        return root;
    }

    private void countWays(TreeNode root, Map<TreeNode, Integer> structure, int[] count) {
        if (root == null) {
            return;
        }

        int leftCount = getCount(root.left, structure);
        int rightCount = getCount(root.right, structure);

        long leftFactorial = factorial(leftCount);
        long rightFactorial = factorial(rightCount);

        long numWays = (leftFactorial * rightFactorial) % MOD;
        numWays = (numWays * count[0]) % MOD;

        count[0] = (int) numWays;

        countWays(root.left, structure, count);
        countWays(root.right, structure, count);
    }

    private int getCount(TreeNode root, Map<TreeNode, Integer> structure) {
        return structure.getOrDefault(root, 0);
    }

    private long factorial(int n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result = (result * i) % MOD;
        }
        return result;
    }

    public static void main(String[] args) {
        SameBSTOrder sbo = new SameBSTOrder();
        int[] nums1 = {2, 1, 3};
        System.out.println(sbo.numOfWays(nums1)); // Output: 1

        int[] nums2 = {3, 4, 5, 1, 2};
        System.out.println(sbo.numOfWays(nums2)); // Output: 5

        int[] nums3 = {1, 2, 3};
        System.out.println(sbo.numOfWays(nums3)); // Output: 0
    }
}



8.// Solution


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class MinimumAbsoluteDifferenceBST {
    private int minDiff;
    private Integer prev;

    public int getMinimumDifference(TreeNode root) {
        minDiff = Integer.MAX_VALUE;
        prev = null;
        inorderTraversal(root);
        return minDiff;
    }

    private void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        inorderTraversal(root.left);

        if (prev != null) {
            minDiff = Math.min(minDiff, root.val - prev);
        }

        prev = root.val;

        inorderTraversal(root.right);
    }

    public static void main(String[] args) {
        // Example 1
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(6);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(3);

        MinimumAbsoluteDifferenceBST minAbsDiffBST = new MinimumAbsoluteDifferenceBST();
        int result1 = minAbsDiffBST.getMinimumDifference(root1);
        System.out.println(result1); // Output: 1

        // Example 2
        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(48);
        root2.right.left = new TreeNode(12);
        root2.right.right = new TreeNode(49);

        int result2 = minAbsDiffBST.getMinimumDifference(root2);
        System.out.println(result2); // Output: 1
    }
}

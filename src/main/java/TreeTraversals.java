import java.util.*;

class Node {
    int val;
    Node left;
    Node right;

    public Node(int val) {
        this.val = val;
        left = null;
        right = null;
    }
}

class Tree {
    Node root;

    public Tree(Node root) {
        this.root = root;
    }

    public void preorderRecursive(Node root) {
        if (root != null) {
            System.out.print("\t" + root.val);
            preorderRecursive(root.left);
            preorderRecursive(root.right);
        }
    }

    public void preorderNonRecursive() {
        Node runner = root;
        Stack<Node> stack = new Stack<>();
        stack.push(runner);
        while (!stack.isEmpty()) {
            runner = stack.pop();
            System.out.print("\t" + runner.val);
            if (runner.right != null) {
                stack.push(runner.right);
            }
            if (runner.left != null) {
                stack.push(runner.left);
            }
        }
    }

    public void inorderRecursive(Node root) {
        if (root != null) {
            inorderRecursive(root.left);
            System.out.print("\t" + root.val);
            inorderRecursive(root.right);
        }
    }

    public void inorderNonRecursive() {
        Node runner = root;
        Stack<Node> stack = new Stack<>();

        while (runner != null) {
            stack.push(runner);
            runner = runner.left;
        }

        while (!stack.isEmpty()) {
            runner = stack.pop();
            System.out.print("\t" + runner.val);
            if (runner.right != null) {
                runner = runner.right;
                while (runner != null) {
                    stack.push(runner);
                    runner = runner.left;
                }
            }
        }
    }

    public void postorderRecursive(Node root) {
        if (root != null) {
            postorderRecursive(root.left);
            postorderRecursive(root.right);
            System.out.print("\t" + root.val);
        }
    }

    public void postorderNonRecursive() {
        Node runner = root;
        Stack<Node> kidStack = new Stack<>();
        Stack<Node> parentStack = new Stack<>();
        kidStack.push(runner);
        while (!kidStack.isEmpty()) {
            runner = kidStack.pop();
            parentStack.push(runner);
            if (runner.left != null) {
                kidStack.push(runner.left);
            }
            if (runner.right != null) {
                kidStack.push(runner.right);
            }
        }

        while (!parentStack.isEmpty()) {
            System.out.print("\t" + parentStack.pop().val);
        }
    }

    public List<List<Integer>> levelOrderTraversal() {
        Node runner = root;
        List<List<Integer>> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(runner);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (!queue.isEmpty()) {
                    runner = queue.poll();
                    currentLevel.add(runner.val);
                    if (runner.left != null) {
                        queue.offer(runner.left);
                    }
                    if (runner.right != null) {
                        queue.offer(runner.right);
                    }
                }
            }
            result.add(currentLevel);
        }
        display(result);
        return result;
    }

    public void zigzagOrderTraversal() {
        boolean leftToRight = false;
        List<List<Integer>> result = new ArrayList<>();
        Stack<Node> currentLevel = new Stack<>();
        Stack<Node> nextLevel = new Stack<>();
        Node runner = root;
        currentLevel.push(runner);
        while (!currentLevel.isEmpty()) {
            System.out.print("\n");
            while (!currentLevel.isEmpty()) {
                runner = currentLevel.pop();
                System.out.print("\t" + runner.val);
                if (leftToRight) {
                    if (runner.right != null) {
                        nextLevel.push(runner.right);
                    }
                    if (runner.left != null) {
                        nextLevel.push(runner.left);
                    }
                } else {
                    if (runner.left != null) {
                        nextLevel.push(runner.left);
                    }
                    if (runner.right != null) {
                        nextLevel.push(runner.right);
                    }
                }
            }

            if (currentLevel.isEmpty()) {
                Stack<Node> temp = currentLevel;
                currentLevel = nextLevel;
                nextLevel = temp;
                leftToRight = !leftToRight;
            }
        }
    }

    public void verticalOrderTraversal() {
        Map<Integer, List<Node>> map = new TreeMap<>();
        traverse(root, 0, map);
        for (List<Node> list : map.values()) {
            System.out.print("\n");
            for (Node i : list) {
                System.out.print("\t" + i.val);
            }
        }
    }

    private void traverse(Node root, int level, Map<Integer, List<Node>> map) {
        if (root != null) {
            if (map.containsKey(level)) {
                map.get(level).add(root);
            } else {
                List<Node> levelList = new ArrayList<>();
                levelList.add(root);
                map.put(level, levelList);
            }
            traverse(root.left, level - 1, map);
            traverse(root.right, level + 1, map);
        }
    }

    public void display(List<List<Integer>> result) {
        for (List<Integer> list : result) {
            System.out.print("\n");
            for (int i : list) {
                System.out.print("\t" + i);
            }
        }
    }
}

public class TreeTraversals {
    public static void main(String[] args) {
        Node r = new Node(1);
        Tree tree = new Tree(r);
        r.left = new Node(2);
        r.right = new Node(6);
        r.left.left = new Node(3);
        r.left.right = new Node(4);
        r.right.left = new Node(5);
        r.right.right = new Node(8);
        r.left.right.left = new Node(10);
        r.left.right.left.left = new Node(11);

        System.out.println("Preorder traversal recursive:");
        tree.preorderRecursive(tree.root);
        System.out.println("\nPreorder traversal non-recursive:");
        tree.preorderNonRecursive();

        System.out.println("\nInorder traversal recursive:");
        tree.inorderRecursive(tree.root);
        System.out.println("\nInorder traversal non-recursive:");
        tree.inorderNonRecursive();

        System.out.println("\nPostorder traversal recursive:");
        tree.postorderRecursive(tree.root);
        System.out.println("\nPostorder traversal non-recursive:");
        tree.postorderNonRecursive();

        System.out.println("\n Level order traversal");
        tree.levelOrderTraversal();

        System.out.println("\n Zigzag order traversal");
        tree.zigzagOrderTraversal();

        System.out.println("\n Vertical order traversal");
        tree.verticalOrderTraversal();
    }
}

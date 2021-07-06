package datastructure.tree;

/**
 * @program: data-structure-and-algorithm
 * TODO
 * @author: Yejiaxin
 * @create: 2020-08-08 20:32
 */
public class BinarySearchTree {
    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    private Node root;

    public Node find(int data) {
        Node p = root;
        while (p != null) {
            if (p.data < data) {
                p = p.right;
            } else if (p.data > data) {
                p = p.left;
            } else {
                return p;
            }
        }
        return null;
    }

    public void insert(int data) {
        Node p = root;
        if (p == null) {
            root = new Node(data);
            return;
        }

        while (p != null) {
            if (p.data > data) {
                if (p.left == null) {
                    p.left = new Node(data);
                    return;
                }
                p = p.left;
            } else {
                if (p.right == null) {
                    p.right = new Node(data);
                    return;
                }
                p = p.right;
            }
        }
    }

    public void delete(int data) {
        Node pp = null;
        Node p = root;

        // 先找到要删除的节点和父节点
        while (p != null && p.data != data) {
            pp = p;
            if (p.data > data) {
                p = p.left;
            } else {
                p = p.right;
            }
        }

        // 左右子节点均不为空, 将右子树的最小节点(即右子树最左边的节点)的值替换到要删除的节点上，
        // 同时要删除的节点变为替换的节点
        if (p.left != null && p.right != null) {
            Node minP = p.right;
            Node minPP = p;

            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }

            p.data = minP.data;

            p = minP;
            pp = minPP;
        }

        // 左右子节点有一个为空
        Node child;
        if (p.left != null) {
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        } else {
            child = null;
        }

        // 要删除的节点为根节点
        if (pp == null) {
            root = child;
        } else if (pp.left == p) {
            pp.left = child;
        } else {
            pp.right = child;
        }

    }

    // 前序遍历
    void preOrder(Node root) {

    }

    // 中序遍历
    void inOrder() {

    }

    // 后序遍历
    void postOrder() {

    }
}

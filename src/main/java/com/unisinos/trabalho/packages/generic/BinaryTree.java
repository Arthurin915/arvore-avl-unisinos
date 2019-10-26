package com.unisinos.trabalho.packages.generic;

public class BinaryTree {

    private Node root;

    Node insert(Node node, int val) {
        if (node == null) {
            return new Node(val);
        } else if (node.getVal() < val) {
            node.setLNode(insert(node.getLNode(), val));
        } else if (node.getVal() > val) {
            node.setRNode(insert(node.getRNode(), val));
            // No Duplicates
        } else return node;

        // Set node Height
        node.setHeight(height(node));

        // Set node Balance
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && val < node.getLNode().getVal())
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && val > node.getRNode().getVal())
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && val > node.getLNode().getVal()) {
            node.setLNode(leftRotate(node.getLNode()));
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && val < node.getRNode().getVal()) {
            node.setRNode(rightRotate(node.getRNode()));
            return leftRotate(node);
        }

        return node;
    }

    // Get Balance factor of node N
    int getBalance(Node node) {
        if (node == null) return 0;
        return height(node.getLNode()) - height(node.getRNode());
    }

    Node rightRotate(Node node) {
        Node leftNode = node.getLNode();
        Node leftRightNode = leftNode.getRNode();

        // Perform rotation
        leftNode.setRNode(node);
        node.setLNode(leftRightNode);

        node.setHeight(height(node));
        leftNode.setHeight(height(node));

        // return Root
        return leftNode;
    }

    Node leftRotate(Node node) {
        Node rightNode = node.getRNode();
        Node rightLeftNode = rightNode.getLNode();

        // Perform rotation
        rightNode.setLNode(node);
        node.setRNode(rightLeftNode);

        node.setHeight(height(node));
        rightNode.setHeight(height(rightNode));

        // Return Root
        return rightNode;
    }

    boolean isBalanced() {
        if (root == null) return true;

        int heightL = height(root.getLNode());
        int heightR = height(root.getRNode());
        int heightDiff = heightL - heightR;

        return heightDiff <= 1 && heightDiff >= -1;
    }

    int height(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.getLNode()), height(node.getRNode()));
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}

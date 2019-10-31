package com.unisinos.trabalho.packages.generic;

public class Node {

    private int val;

    private int height;

    private int balance;

    private Node left;

    private Node right;

    public Node(int val) {
        this.val = val;
        left = null;
        right = null;
    }

    public Node(int val, int height, int balance, Node left, Node right) {
        this.val = val;
        this.height = height;
        this.balance = balance;
        this.left = left;
        this.right = right;
    }

    public Node copyNode() {
        return new Node(val, height, balance, left, right);
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node lNode) {
        this.left = lNode;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node rNode) {
        this.right = rNode;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

}

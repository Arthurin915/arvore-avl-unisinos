package com.unisinos.trabalho.packages.generic;

public class Node {

    private int val;

    private int height;

    private int balance;

    private Node lNode;

    private Node rNode;

    public Node(Integer val) {
        this.val = val;
        lNode = null;
        rNode = null;
    }

    public Node getLNode() {
        return lNode;
    }

    public void setLNode(Node lNode) {
        this.lNode = lNode;
    }

    public Node getRNode() {
        return rNode;
    }

    public void setRNode(Node rNode) {
        this.rNode = rNode;
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

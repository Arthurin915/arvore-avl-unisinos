package com.unisinos.trabalho.packages.generic;

public class Node {

    private INodeValue key;

    private int height;

    private int balance;

    private Node left;

    private Node right;

    public Node(INodeValue key) {
        this.key = key;
        left = null;
        right = null;
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

    public INodeValue getKey() {
        return key;
    }

    public void setKey(INodeValue key) {
        this.key = key;
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

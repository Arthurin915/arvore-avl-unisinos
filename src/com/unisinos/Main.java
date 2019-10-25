package com.unisinos;

import com.unisinos.tree.BinaryTree;
import com.unisinos.tree.Node;

public class Main {

    public static void main(String[] args) {
        tofazendoIssoaquiparauasJunit();
        // write your code here
    }

    public static void tofazendoIssoaquiparauasJunit() {
        BinaryTree binaryTree = new BinaryTree();
        Node node = createNode();
        binaryTree.setRoot(node);
        Node childNode = createNode();
        node.setLNode(childNode);
        Node childchildNode = createNode();
        childNode.setLNode(childchildNode);
        childchildNode.setLNode(createNode());
        node.setRNode(createNode());

        noop();
    }

    private static void noop() {
    }

    private static Node createNode() {
        return new Node("TANTO FAZ");
    }
}

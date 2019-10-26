package com.unisinos.trabalho.packages.generic;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {

	private Node root;

	public Node insert(int val) {
		if (this.root == null) {
			Node root = new Node(val);
			setRoot(root);
			return root;
		} else {
			Node root = insert(this.root, val);
			setRoot(root);
			return root;
		}
	}

	public List<Integer> inOrder() {
		List<Integer> inOrderList = new ArrayList<>();
		if (root == null) {
			return inOrderList;
		}
		inOrder(root, inOrderList);
		return inOrderList;
	}

	private void inOrder(Node node, List<Integer> inOrderList) {
		if (node != null) {
			inOrder(node.getLeft(), inOrderList);
			inOrderList.add(node.getVal());
			inOrder(node.getRight(), inOrderList);
		}
	}

	private Node insert(Node node, int val) {
		if (node == null) return new Node(val);

		if (val < node.getVal()) {
			node.setLeft(insert(node.getLeft(), val));
		} else if (val > node.getVal()) {
			node.setRight(insert(node.getRight(), val));
			// No Duplicates
		} else return node;

		// Set node Height
		node.setHeight(height(node));

		// Set node Balance
		int balance = getBalance(node);

		// If this node becomes unbalanced, then there
		// are 4 cases Left Left Case
		if (balance > 1 && val < node.getLeft().getVal())
			return rightRotate(node);

		// Right Right Case
		if (balance < -1 && val > node.getRight().getVal())
			return leftRotate(node);

		// Left Right Case
		if (balance > 1 && val > node.getLeft().getVal()) {
			node.setLeft(leftRotate(node.getLeft()));
			return rightRotate(node);
		}

		// Right Left Case
		if (balance < -1 && val < node.getRight().getVal()) {
			node.setRight(rightRotate(node.getRight()));
			return leftRotate(node);
		}

		return node;
	}

	// Get Balance factor of node N
	private int getBalance(Node node) {
		if (node == null) return 0;
		return height(node.getLeft()) - height(node.getRight());
	}

	private Node rightRotate(Node node) {
		Node leftNode = node.getLeft();
		Node leftRightNode = leftNode.getRight();

		// Perform rotation
		leftNode.setRight(node);
		node.setLeft(leftRightNode);

		node.setHeight(height(node));
		leftNode.setHeight(height(node));

		// return Root
		return leftNode;
	}

	private Node leftRotate(Node node) {
		Node rightNode = node.getRight();
		Node rightLeftNode = rightNode.getLeft();

		// Perform rotation
		rightNode.setLeft(node);
		node.setRight(rightLeftNode);

		node.setHeight(height(node));
		rightNode.setHeight(height(rightNode));

		// Return Root
		return rightNode;
	}

	boolean isBalanced() {
		if (root == null) return true;

		int heightL = height(root.getLeft());
		int heightR = height(root.getRight());
		int heightDiff = heightL - heightR;

		return heightDiff <= 1 && heightDiff >= -1;
	}

	private int height(Node node) {
		if (node == null) return 0;
		return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}
}

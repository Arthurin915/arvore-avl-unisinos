package com.unisinos.trabalho.packages.generic;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {

	@Test
	void insert() {
		// Arrange
		BinaryTree binaryTree = new BinaryTree();

		// Act
		binaryTree.insert(2);
		binaryTree.insert(3);
		binaryTree.insert(1);

		// Assert
		Node root = binaryTree.getRoot();
		Assert.notNull(root);
		Assert.isTrue(2 == root.getVal());
		Node left = root.getLeft();
		Assert.notNull(left);
		Assert.isTrue(1 == left.getVal());
		Node right = root.getRight();
		Assert.notNull(right);
		Assert.isTrue(3 == right.getVal());
	}

	@Test
	void isBalanced() {
		// Arrange
		BinaryTree binaryTree = new BinaryTree();

		// Act
		binaryTree.insert(2);
		binaryTree.insert(3);
		binaryTree.insert(1);

		// Assert
		Assert.isTrue(binaryTree.isBalanced());
	}

	@Test
	void isBalanced_rotating() {
		// Arrange
		BinaryTree binaryTree = new BinaryTree();

		// Act
		binaryTree.insert(1);
		binaryTree.insert(2);
		binaryTree.insert(3);
		binaryTree.insert(4);
		binaryTree.insert(5);
		binaryTree.insert(6);

		// Assert
		Assert.isTrue(binaryTree.isBalanced());

		Node root = binaryTree.getRoot();
		Assert.isTrue(root.getVal() == 4);
		Assert.isTrue(root.getLeft().getVal() == 2);
		Assert.isTrue(root.getLeft().getLeft().getVal() == 1);
		Assert.isTrue(root.getLeft().getRight().getVal() == 3);
		Assert.isTrue(root.getRight().getVal() == 5);
		Assert.isTrue(root.getRight().getRight().getVal() == 6);
	}
	@Test
	void inOrder() {
		// Arrange
		BinaryTree binaryTree = new BinaryTree();

		// Act
		binaryTree.insert(1);
		binaryTree.insert(2);
		binaryTree.insert(3);
		binaryTree.insert(4);
		binaryTree.insert(5);
		binaryTree.insert(6);
		List<Integer> integers = binaryTree.inOrder();

		// Assert
		Assert.isTrue(integers.get(0) == 1);
		Assert.isTrue(integers.get(1) == 2);
		Assert.isTrue(integers.get(2) == 3);
		Assert.isTrue(integers.get(3) == 4);
		Assert.isTrue(integers.get(4) == 5);
		Assert.isTrue(integers.get(5) == 6);
	}
}
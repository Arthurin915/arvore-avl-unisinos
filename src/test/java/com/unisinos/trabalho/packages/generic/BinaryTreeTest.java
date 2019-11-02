package com.unisinos.trabalho.packages.generic;

import com.unisinos.trabalho.packages.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

class BinaryTreeTest {

	@Test
	void insert() {
		// Arrange
		BinaryTree binaryTree = new BinaryTree();
		final StringKey stringKey1 = new StringKey("1");
		final StringKey stringKey2 = new StringKey("2");
		final StringKey stringKey3 = new StringKey("3");

		// Act
		binaryTree.insert(stringKey2);
		binaryTree.insert(stringKey3);
		binaryTree.insert(stringKey1);

		// Assert
		Node root = binaryTree.getRoot();
		Assert.notNull(root);
		Assert.isTrue(stringKey2 == root.getKey());
		Node left = root.getLeft();
		Assert.notNull(left);
		Assert.isTrue(stringKey1 == left.getKey());
		Node right = root.getRight();
		Assert.notNull(right);
		Assert.isTrue(stringKey3 == right.getKey());
	}

	@Test
	void isBalanced() {
		// Arrange
		BinaryTree binaryTree = new BinaryTree();

		// Act
		binaryTree.insert(new StringKey("2"));
		binaryTree.insert(new StringKey("3"));
		binaryTree.insert(new StringKey("1"));

		// Assert
		Assert.isTrue(binaryTree.isBalanced());
	}

	@Test
	void isBalanced_rotating() {
		// Arrange
		BinaryTree binaryTree = new BinaryTree();
		final StringKey stringKey1 = new StringKey("1");
		final StringKey stringKey2 = new StringKey("2");
		final StringKey stringKey3 = new StringKey("3");
		final StringKey stringKey4 = new StringKey("4");
		final StringKey stringKey5 = new StringKey("5");
		final StringKey stringKey6 = new StringKey("6");

		// Act
		binaryTree.insert(stringKey1);
		binaryTree.insert(stringKey2);
		binaryTree.insert(stringKey3);
		binaryTree.insert(stringKey4);
		binaryTree.insert(stringKey5);
		binaryTree.insert(stringKey6);

		// Assert
		Assert.isTrue(binaryTree.isBalanced());

		Node root = binaryTree.getRoot();
		Assert.isTrue(root.getKey() == stringKey4);
		Assert.isTrue(root.getLeft().getKey() == stringKey2);
		Assert.isTrue(root.getLeft().getLeft().getKey() == stringKey1);
		Assert.isTrue(root.getLeft().getRight().getKey() == stringKey3);
		Assert.isTrue(root.getRight().getKey() == stringKey5);
		Assert.isTrue(root.getRight().getRight().getKey() == stringKey6);
	}

	@Test
	void isBalanced_deleting_right() {
		// Arrange
		BinaryTree binaryTree = new BinaryTree();
		final StringKey stringKey6 = new StringKey("6");
		final StringKey stringKey7 = new StringKey("7");

		// Act
		binaryTree.insert(new StringKey("1"));
		binaryTree.insert(new StringKey("2"));
		binaryTree.insert(new StringKey("3"));
		binaryTree.insert(new StringKey("4"));
		binaryTree.insert(new StringKey("5"));
		binaryTree.insert(stringKey6);
		binaryTree.insert(stringKey7);

		// Assert
		Node rootBeforeDelete = binaryTree.getRoot();

		Assert.isTrue(rootBeforeDelete.getRight().getKey() == stringKey6);

		// Act
		binaryTree.delete(stringKey6);

		// Assert
		Assert.isTrue(binaryTree.isBalanced());

		Node rootAfterDelete = binaryTree.getRoot();
		Assert.isTrue(rootAfterDelete.getRight().getKey() == stringKey7);
	}

	@Test
	void isBalanced_deleting_left() {
		// Arrange
		BinaryTree binaryTree = new BinaryTree();

		// Act
		binaryTree.insert(new StringKey("1"));
		binaryTree.insert(new StringKey("2"));
		binaryTree.insert(new StringKey("3"));
		binaryTree.insert(new StringKey("4"));
		binaryTree.insert(new StringKey("5"));
		binaryTree.insert(new StringKey("6"));
		binaryTree.insert(new StringKey("7"));

		// Act
		binaryTree.delete(new StringKey("1"));
		binaryTree.delete(new StringKey("2"));
		binaryTree.delete(new StringKey("3"));

		// Assert
		Assert.isTrue(binaryTree.isBalanced());
		final Node root = binaryTree.getRoot();

		Assert.isTrue(root.getLeft() == null);
	}

	@Test
	void inOrder() {
		// Arrange
		BinaryTree binaryTree = new BinaryTree();
		final StringKey stringKey1 = new StringKey("1");
		final StringKey stringKey2 = new StringKey("2");
		final StringKey stringKey3 = new StringKey("3");
		final StringKey stringKey4 = new StringKey("4");
		final StringKey stringKey5 = new StringKey("5");
		final StringKey stringKey6 = new StringKey("6");

		// Act
		binaryTree.insert(stringKey1);
		binaryTree.insert(stringKey2);
		binaryTree.insert(stringKey3);
		binaryTree.insert(stringKey4);
		binaryTree.insert(stringKey5);
		binaryTree.insert(stringKey6);
		List<INodeValue> integers = binaryTree.inOrder();

		// Assert
		Assert.isTrue(integers.get(0) == stringKey1);
		Assert.isTrue(integers.get(1) == stringKey2);
		Assert.isTrue(integers.get(2) == stringKey3);
		Assert.isTrue(integers.get(3) == stringKey4);
		Assert.isTrue(integers.get(4) == stringKey5);
		Assert.isTrue(integers.get(5) == stringKey6);
	}
	
	@Test
	void startsWith() {
		// Arrange
		final BinaryTree binaryTree = new BinaryTree();

		final StringKey rafael = new StringKey("Rafael", 0);
		final StringKey ramon = new StringKey("Ramon", 1);
		final StringKey wesley = new StringKey("Wesley", 2);
		final StringKey arthut = new StringKey("Arthut", 3);
		final StringKey raco = new StringKey("Raco", 4);

		binaryTree.insert(rafael);
		binaryTree.insert(ramon);
		binaryTree.insert(wesley);
		binaryTree.insert(arthut);
		binaryTree.insert(raco);

		// Act
		final List<INodeValue> nodes = binaryTree.searchListBy(new NameSearchTerm("ra"));

		// Assert
		Assert.isTrue(nodes.size() == 3);
		Assert.isTrue(nodes.get(0) == ramon);
		Assert.isTrue(nodes.get(1) == raco);
		Assert.isTrue(nodes.get(2) == rafael);
	}

	@Test
	void isBetween() {
		// Arrange
		final BinaryTree binaryTree = new BinaryTree();

		final LocalDateKey seteOito1995 = new LocalDateKey(LocalDate.of(1995, 8, 7));
		final LocalDateKey dezSeis2000 = new LocalDateKey(LocalDate.of(2000, 6, 10));
		final LocalDateKey cincoQuatro1993 = new LocalDateKey(LocalDate.of(1993, 4, 5));
		final LocalDateKey dozeDoze2010 = new LocalDateKey(LocalDate.of(2010, 12, 12));
		final LocalDateKey tresNove2006 = new LocalDateKey(LocalDate.of(2006, 9, 3));
		final LocalDateKey umQuatro1889 = new LocalDateKey(LocalDate.of(1889, 4, 1));

		binaryTree.insert(seteOito1995);
		binaryTree.insert(dozeDoze2010);
		binaryTree.insert(umQuatro1889);
		binaryTree.insert(dezSeis2000);
		binaryTree.insert(tresNove2006);
		binaryTree.insert(cincoQuatro1993);

		final BirthdateSearchTerm birthdateSearchTerm = new BirthdateSearchTerm(cincoQuatro1993.getKey().minusDays(1), dezSeis2000.getValue().plusDays(1));

		// Act
		final List<INodeValue> nodes = binaryTree.searchListBy(birthdateSearchTerm);

		// Assert
		Assert.isTrue(nodes.size() == 3);
		Assert.isTrue(nodes.get(0) == seteOito1995);
		Assert.isTrue(nodes.get(1) == cincoQuatro1993);
		Assert.isTrue(nodes.get(2) == dezSeis2000);
	}

	@Test
	void isCpfEquals() {
		BinaryTree binaryTree = new BinaryTree();
		final StringKey stringKey1 = new StringKey("1");
		final StringKey stringKey2 = new StringKey("2");
		final StringKey stringKey3 = new StringKey("3");
		final StringKey stringKey4 = new StringKey("4");
		final StringKey stringKey5 = new StringKey("5");
		final StringKey stringKey6 = new StringKey("6");

		binaryTree.insert(stringKey1);
		binaryTree.insert(stringKey2);
		binaryTree.insert(stringKey3);
		binaryTree.insert(stringKey4);
		binaryTree.insert(stringKey5);
		binaryTree.insert(stringKey6);

		// Act
		final INodeValue node = binaryTree.searchBy(new CpfSearchTerm("5"));

		// Assert
		Assert.isTrue(node == stringKey5);
	}
}
package com.unisinos.trabalho.packages.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BinaryTree {

	private Node root;

	public Node getRoot() {
		return root;
	}

	public Node insert(INodeValue val) {
		if (this.root == null) {
			this.root = new Node(val);
			return root;
		} else {
			this.root = insert(this.root, val);
			return root;
		}
	}

	public Node delete(INodeValue val) {
		return delete(this.root, val);
	}

	public List<INodeValue> inOrder() {
		List<INodeValue> inOrderList = new ArrayList<>();
		if (root == null) {
			return inOrderList;
		}
		inOrder(root, inOrderList);
		return inOrderList;
	}

	public INodeValue searchBy(INodeSearchTerm searchTerm) {
		return searchBy(root, searchTerm);
	}

	public List<INodeValue> searchListBy(INodeSearchTerm searchTerm) {
		final ArrayList<INodeValue> foundNodes = new ArrayList<>();
		searchListBy(root, searchTerm, foundNodes);
		return foundNodes;
	}

	private INodeValue searchBy(Node node, INodeSearchTerm searchTerm) {
		if (node == null) return null;

		final INodeValue key = node.getKey();

		if (searchTerm.compareTo(key) < 0) {
			return searchBy(node.getLeft(), searchTerm);
		} else if (searchTerm.compareTo(key) > 0) {
			return searchBy(node.getRight(), searchTerm);
		}

		return key;
	}

	private void searchListBy(Node node, INodeSearchTerm searchTerm, ArrayList<INodeValue> foundNodes) {
		if (node == null) return;

		final INodeValue key = node.getKey();

		if (searchTerm.isValid(key)) {
			foundNodes.add(node.getKey());
			searchListBy(node.getLeft(), searchTerm, foundNodes);
			searchListBy(node.getRight(), searchTerm, foundNodes);
		} else if (searchTerm.compareTo(key) < 0) {
			searchListBy(node.getLeft(), searchTerm, foundNodes);
		} else if (searchTerm.compareTo(key) > 0) {
			searchListBy(node.getRight(), searchTerm, foundNodes);
		}
	}

	private void inOrder(Node node, List<INodeValue> inOrderList) {
		if (node != null) {
			inOrder(node.getLeft(), inOrderList);
			inOrderList.add(node.getKey());
			inOrder(node.getRight(), inOrderList);
		}
	}

	private Node insert(Node node, INodeValue val) {
		if (node == null) return new Node(val);

		if (node.getKey().compareTo(val) > 0) {
			node.setLeft(insert(node.getLeft(), val));
		} else if (node.getKey().compareTo(val) < 0) {
			node.setRight(insert(node.getRight(), val));
		} else return node;

		return checkTreeBalance(node, val);

	}

	private Node checkTreeBalance(Node node, INodeValue val) {
		// Seta a altura do nodo
		node.setHeight(height(node));

		// pega o balance do nodo
		int balance = getBalance(node);

		// Checa se é necessário balancear
		if (Math.abs(balance) > 1) {
			return balanceTree(node, val, balance);
		}

		return node;
	}

	private Node balanceTree(Node node, INodeValue val, int balance) {
		// Left Left Case
		if (balance > 1 && val.compareTo(node.getLeft().getKey()) < 0)
			return rightRotate(node);

		// Right Right Case
		if (balance < -1 && val.compareTo(node.getRight().getKey()) > 0)
			return leftRotate(node);

		// Left Right Case
		if (balance > 1 && val.compareTo(node.getLeft().getKey()) > 0) {
			node.setLeft(leftRotate(node.getLeft()));
			return rightRotate(node);
		}

		// Right Left Case
		if (balance  < -1 && val.compareTo(node.getRight().getKey()) < 0) {
			node.setRight(rightRotate(node.getRight()));
			return leftRotate(node);
		}

		return node;
	}

	private Node delete(Node node, INodeValue val) {
		if (node == null) return null;

		// Deleta à esquerda
		if (val.compareTo(node.getKey()) < 0) node.setLeft(delete(node.getLeft(), val));
		// Deleta à direita
		else if (val.compareTo(node.getKey()) > 0) node.setRight(delete(node.getRight(), val));
		// Nodo a ser deletado
		else {
			// Caso nodo só tem um ou nenhum filho
			if (node.getLeft() == null || node.getRight() == null) {
				// Seta o único filho ou null encima do nodo
				node = Optional.ofNullable(node.getLeft()).orElse(node.getRight());
			}
			// Tem dois filhos
			else {
				// Pega o menor valor da direita do nodo
				final Node leftMostNode = getLeftMostNode(node.getRight());

				// Copia o valor da referencia
				node.setKey(leftMostNode.getKey());

				// Deleta o valor original
				node.setRight(delete(node.getRight(), node.getKey()));
			}
		}

		// Se era o ultimo nodo
		if (node == null) return null;

		return checkTreeBalance(node, val);
	}

	private Node getLeftMostNode(Node node) {
		if (node.getLeft() != null) return getLeftMostNode(node);
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

		leftNode.setRight(node);
		node.setLeft(leftRightNode);

		node.setHeight(height(node));
		leftNode.setHeight(height(node));

		return leftNode;
	}

	private Node leftRotate(Node node) {
		Node rightNode = node.getRight();
		Node rightLeftNode = rightNode.getLeft();

		rightNode.setLeft(node);
		node.setRight(rightLeftNode);

		node.setHeight(height(node));
		rightNode.setHeight(height(rightNode));

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

}

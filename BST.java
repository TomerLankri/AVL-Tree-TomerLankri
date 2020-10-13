package oop.ex4.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;



/**
 * A class representing a binary tree
 */
class BST implements TreeInterface {
	/**
	 * a class represent a Node in a binary search tree
	 */
	class Node {

		/**
		 * Left and right node representing a right child and a left child
		 */
		private Node left,right = null;

		/**
		 * the relatve height of the node
		 */
		int height = 0;

		/**
		 * the value the node holds
		 */
		int value;

		/**
		 * constructor from a given value
		 */
		Node(int value) {
			this.value = value;
		}

		/**
		 * getter for the right child
		 * @return the right child of the node
		 */
		Node getRight() {
			return right;
		}

		/**
		 * setter for the right child
		 * @param right right child node
		 */
		void setRight(Node right) {
			this.right = right;
		}

		/**
		 * getter for left child
		 * @return left child node
		 */
		Node getLeft() {
			return left;
		}

		/**
		 * setter for the left child nod
		 * @param left left child node
		 */
		void setLeft(Node left) {
			this.left = left;
		}
	}


	/**
	 * constant for representing empty node
	 */
	private static final int EMPTY_NODE = -1;
	/**
	 * a constant for representing a value that didnt found
	 */
	private static final int VALUE_NOT_FOUND = -1;


	/**
	 * the root node of the tree
	 */
	private Node root = null;

	/**
	 * the total number of nodes in the tree
	 */
	private int size = 0;
	/**
	 * a boolean for the delete method, holds true if a value has deleted or false if not
	 */
	private static boolean isDeleted = false;
	/**
	 * a boolean for the add method, holds true if a value has been added or false if not
	 */
	private static boolean isAdded = false;

	/**
	 * depth counter for the 'contains' method
	 */
	private static int depthCounter = 0;


	/**
	 * constructor.
	 */
	BST() {
	}

	/**
	 * getter for the root node of this tree
	 * @return a tree root node
	 */
	Node getRoot() {
		return root;
	}

	/**
	 * A method that holds the node that contains the nearest value or equals a given value
	 * @param searchVal a given integer to search for
	 * @param node a given tree root
	 * @return the node with the closest value
	 */
	private Node findClosest(int searchVal, Node node) {
		if (!isNodeValid(node)) {
			return null;
		}
		int value = node.value;
		depthCounter = 0;
		while (value != searchVal) {
			depthCounter++;
			if (searchVal > value) {
				if (node.getRight() == null) {
					return node;
				}
				node = node.getRight();
			}
			if (searchVal < value) {
				if (node.getLeft() == null) {
					return node;
				}
				node = node.getLeft();
			}
			value = node.value;
		}
		return node;
	}


	/**
	 * Add a new node with key newValue into the tree.
	 *
	 * @param newValue new value to add to the tree.
	 * @return false iff newValue already exist in the tree
	 */
	public boolean add(int newValue){
		isAdded = false;
		root = addHelper(root, newValue);
		if (isAdded) {
			size++;
		}
		return isAdded;
	}

	/**
	 * a helper method for adding data recursively to the given root node
	 * @param node a root node
	 * @param addValue added value
	 * @return the root node of the tree thats contains the value
	 */
	private Node addHelper(Node node, int addValue) {
		if (node == null) { // reached a leafs child
			node = new Node(addValue);
			isAdded = true; // the value has been added
		} else if (addValue < node.value) {
			node.setLeft(addHelper(node.getLeft(), addValue));
			node = addedFeatures(node);
		} else if (addValue > node.value) {
			node.setRight(addHelper(node.getRight(), addValue));
			node = addedFeatures(node);
		}
		return node;
	}

	/**
	 * A method that contains additional actions required to perform the process of adding and deleting
	 * organs from the tree The method is designed to enable the expansion of the department while
	 * maintaining the addition and deletion of organs in the recursion.
	 * @param node A given Node
	 * @return the given Node after performing the operations performed by the method
	 */
	Node addedFeatures(Node node) {
		return node;
	}

	/**
	 * a getter for the child of a given node who have only one child/
	 * @param node the given node
	 * @return his left or right only child
	 */
	private Node getOnlyChild(Node node) {
		Node child = null;
		if (hasRightChild(node)) {
			child = node.getRight();
		}
		if (hasLeftChild(node)) {
			child = node.getLeft();
		}
		return child;
	}

	/**
	 * checks if a node is valid - not null or not containing empty value
	 * @param node a given node
	 * @return true if valid else false
	 */
	private boolean isNodeValid(Node node) {
		return !(node == null);
	}

	/**
	 * Does tree contain a given input value.
	 *
	 * @param searchVal value to search for
	 * @return if val is found in the tree, return the depth of its node (where 0 is the root). Otherwise
	 * -- return -1.
	 */
	public int contains(int searchVal) {
		if (root == null) {
			return VALUE_NOT_FOUND;
		}
		Node found = findClosest(searchVal, this.root);
		if (found != null && found.value == searchVal) {
			return depthCounter;
		}
		return VALUE_NOT_FOUND;
	}

	/**
	 * a helper method to delete the given value recursively from the given node of the tree, assuming at
	 * the beggining of the recursion it is a root node
	 * @param node a given node
	 * @param toDelete a given value to delete
	 * @return the tree root node after deletion of the given value to delete
	 */
	private Node delete(Node node, int toDelete) {

		if (node == null)   // a stop condition - the node is a child of a leaf or not valid
			return null;

		if (toDelete < node.value)  // value is smaller than nodes value - go left
			node.setLeft(delete(node.getLeft(), toDelete));

		else if (toDelete > node.value) // value is bigger than the nodes value - go right
			node.setRight(delete(node.getRight(), toDelete));

			// value is equal to the node so delete it
		else {
			if (hasTwoSons(node)) {

				//find the successor
				Node successor = findSuccessor(node.getRight());

				//set the successor
				node.value = successor.value;

				// Delete the successor
				node.setRight(delete(node.getRight(), successor.value));

			} else {
				// one child or no child case
				isDeleted = true;
				node = getOnlyChild(node);  // return null or Node
			}
		}

		// If the tree had only one node then return
		if (node == null) {
			return null;
		}
		return addedFeatures(node); // after deleting the node perform added features as we like to put in
		// addedFeatures(node) Method
	}

	/**
	 * find the successor value to the given node
	 * @param node given node
	 * @return succesor node
	 */
	private Node findSuccessor(Node node) {
		Node cur = node;
		while (cur.getLeft() != null) {
			cur = cur.getLeft();
		}
		return cur;
	}

	/**
	 * Remove a node from the tree, if it exists.
	 *
	 * @param toDelete value to delete
	 * @return true iff toDelete found and isDeleted
	 */
	public boolean delete(int toDelete) {

		isDeleted = false;
		root = delete(root, toDelete);
		if (isDeleted) {
			size--; // adjust size after delete
		}
		return isDeleted;
	}

	/**
	 * calculate the height of a given node
	 * @param node a given node
	 * @return the node's height if it isnt a null node, else returns -1
	 */
	int height(Node node) {
		if (node == null) {
			return EMPTY_NODE;
		}
		return node.height;
	}

	@Override
	public int size() {
		return size;
	}

	/**
	 * checks if a given node has two childs
	 * @param node a given node
	 * @return true iff has two childs
	 */
	private boolean hasTwoSons(Node node) {
		return (hasLeftChild(node) && hasRightChild(node));
	}

	/**
	 * checks if a given node has a right child
	 * @param node a given node
	 * @return true iff the node has right child
	 */
	private boolean hasRightChild(Node node) {
		return (node.getRight() != null);
	}
	/**
	 * checks if a given node has a left child
	 * @param node a given node
	 * @return true iff the node has left child
	 */
	private boolean hasLeftChild(Node node) {
		return (node.getLeft() != null);
	}

	/**
	 * a class thats represent iterator for the tree
	 */
	private static class InOrderBSTIterator implements Iterator<Integer> {
		/**
		 * a stack for holding the tree data
		 */
		Stack<Node> stack = new Stack<>();

		/**
		 * pushing all the nodes to the stack recursively.
		 * @param node a given node
		 */
		private void pushChild(Node node) {
			if (node == null)
				return;
			pushChild(node.getRight());
			stack.push(node);
			pushChild(node.getLeft());
		}

		/**
		 * a constructor for the iterator
		 * @param root a given tree root node
		 */
		InOrderBSTIterator(Node root) {
			pushChild(root);
		}

		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public Integer next() throws NoSuchElementException {
			if (!hasNext()) {
				throw new NoSuchElementException("All Nodes have been iterated");
			}

			Node nextNode = stack.pop();
			return nextNode.value;
		}

		@Override
		public void remove() throws UnsupportedOperationException{
			throw new UnsupportedOperationException("remove() is not supported.");
		}
	}


	@Override
	public java.util.Iterator<java.lang.Integer> iterator() {
		return new InOrderBSTIterator(root);
	}

}

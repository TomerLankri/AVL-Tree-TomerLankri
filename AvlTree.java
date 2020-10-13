package oop.ex4.data_structures;

import java.util.*;

/**
 * This class is the complete and tested implementation of an AVL-tree.
 */
public class AvlTree extends BST {


	/**
	 * Constants representing the required rotation directions
	 */
	private final int R = 1;
	private final int LR = 4;
	private final int RL = 2;
	private final int L = 3;


	/**
	 * A default constructor.
	 */
	public AvlTree() {
		super();
	}

	/**
	 * A constructor that builds the tree by adding the elements in the input array one-by-one If the same
	 * values appears twice (or more) in the list, it is ignored.
	 * @param data values to add to tree
	 */
	public AvlTree(int[] data) {
		super();
		ArrayList<Integer> temp = new ArrayList<>();
		if (data != null) {
			for (int i : data) {
				temp.add(i);
			}
		}
		Collections.sort(temp);
		buildTreeFromData(temp);
	}

	/**
	 * A copy-constructor that builds the tree from existing tree
	 * @param tree tree to be copied
	 */
	public AvlTree(AvlTree tree) {
		super();
		if (tree != null && tree.getRoot() != null) {
			for (int value: tree) {
				add(value);
			}
		}
	}

	/**
	 * calculating balance factor for a given node
	 * @return integer
	 */
	private int getBalance(Node node) {
		if (node == null) {
			return 0;
		}
		return height(node.getLeft()) - height(node.getRight());
	}

	/**
	 * updating the height of a given node
	 * @param node a given node
	 */
	private void updateHeight(Node node) {
		node.height = Math.max(height(node.getLeft()), height(node.getRight())) + 1;
	}

	/**
	 * The method performs a height adjustment to the given top while testing for a balance violation.
	 * Given that a breach has been found to balance the sub-tree whose root is at the top of the figure,
	 * the method activates the relational balancing operation according to the specific violation found.
	 * @param node a given node
	 * @return the given Node after performing the operations above
	 */
	Node addedFeatures(Node node) {
		updateHeight(node);
		if (getBalance(node) > 1)
			if (getBalance(node.getLeft()) >= 0) {
				node = rotate(node, R);
			} else {
				node = rotate(node,LR);
			}
		else if (getBalance(node) < -1)
			if (getBalance(node.getRight()) <= 0) {
				node = rotate(node, L);
			} else {
				node = rotate(node,RL);
			}
		return node;
	}

	/**
	 * The method performs the required rotation to correct the imbalance of the tree according to the
	 * type of violation.
	 * @param Y a given node to rotate
	 * @param direction a given direction coded by integers 1 to 4
	 * @return the root after the rotation
	 */
	private Node rotate(Node Y, int direction) {
		Node X = null;
		switch (direction) {
			case R:
				X = Y.getLeft();
				Y.setLeft(X.getRight());
				X.setRight(Y);
				break;
			case L:
				X = Y.getRight();
				Y.setRight(X.getLeft());
				X.setLeft(Y);
				break;
			case LR:
				Y.setLeft(rotate(Y.getLeft(), L));
				return rotate(Y, R);
			case RL:
				Y.setRight(rotate(Y.getRight(), R));
				return rotate(Y, L);
		}
		updateHeight(Y);
		updateHeight(X);
		return X;
	}

	/**
	 * A method helps construct recursively a tree from a given data list assuming that it contains
	 * non-negative integer integers
	 * @param list data list
	 */
	private void buildTreeFromData(List<Integer> list) {
		if (list.size() == 0) {
			return;
		}
		int mid = Math.floorDiv(list.size(), 2);
		add(list.get(mid));
		buildTreeFromData(list.subList(0, mid));
		buildTreeFromData(list.subList(mid+1, list.size()));
	}


	/**
	 * A method that calculates the minimum numbers of nodes in an AVL tree of height h,
	 *
	 * @param h     height of the tree (a non-negative number).
	 * @return minimum number of nodes of height h
	 */
	public static int findMinNodes(int h) {
		if (h<0){
			throw new NumberFormatException("a negative number is not valid for height of a tree");
		}
		return (int)(Math.round(((Math.sqrt(5)+2)/
				Math.sqrt(5))*Math.pow((1+
				Math.sqrt(5))/2,h)-1));
	}


	/**
	 * A method that calculates the maximum number of nodes in an AVL tree of height h,
	 *
	 * @param h height of the tree (a non-negative number).
	 * @return maximum number of nodes of height h
	 */
	public static int findMaxNodes(int h) {
		if (h<0){
			throw new NumberFormatException("a negative number is not valid for height of a tree");
		}return (int) Math.pow(2, (h + 1)) - 1;
	}


}
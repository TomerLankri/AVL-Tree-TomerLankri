package oop.ex4.data_structures;

/**
 * an interface for implementing tree data structure
 */
interface TreeInterface extends Iterable<Integer> {


	/**
	 * Add a new node with key newValue into the tree.
	 *
	 * @param newValue - new value to add to the tree.
	 * @return false iff newValue already exist in the tree
	 */
	boolean add(int newValue);


	/**
	 * Does tree contain a given input value.
	 *
	 * @param searchVal value to search for
	 * @return if val is found in the tree, return the depth of its node (where 0 is the root). Otherwise
	 * -- return -1.
	 */
	int contains(int searchVal);

	/**
	 * Remove a node from the tree, if it exists.
	 *
	 * @param toDelete value to delete
	 * @return true iff toDelete found and isDeleted
	 */
	boolean delete(int toDelete);

	/**
	 * @return number of nodes in the tree
	 */
	int size();

	/**
	 * Returns an iterator for the Avl Tree. The returned iterator iterates over the tree nodes in an
	 *  ascending order, and does NOT implement the remove() method.
	 * @return an iterator for the Avl Tree.
	 */
	java.util.Iterator<java.lang.Integer> iterator();
}
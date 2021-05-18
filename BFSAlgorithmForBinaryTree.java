import java.util.LinkedList;
import java.util.Scanner;
public class BFSAlgorithmForBinaryTree {
	Node rootNode = null;
	int numToFind;
	// Node object to hold the user input in the form of binary tree.
	public class Node {
		Node left;
		Node right;
		int val;
		public Node (int val) {
			this.val = val;
		}
	}
	
	private void addNodeToTree(int parent, int child) {
		// rootNode will be null initially so consider the first input from the user as rootNode.
		
		if (rootNode == null) {
			Node root = new Node(parent);
			root.val = parent;
			Node leftNode = new Node(child);
			leftNode.val = child;
			root.left = leftNode;
			rootNode = root;
			return;
		}
		insertNode(rootNode, parent, child);
	}
	
//This method recursively finds the parent node in the tree and adds the child under the parent.
	
	private void insertNode(Node node, int parent, int child) {
		if (node == null) {
		return;
		}
		if (node.val == parent) {
			if (node.left == null) {
				Node left = new Node(child);
				node.left = left;
			} else if (node.right == null) {
				Node right = new Node(child);
				node.right = right;
			} else {
				System.out.println("Cannot provide more than two child nodes.");
			}
			 ;
		}

	insertNode(node.left, parent, child);
		insertNode(node.right, parent, child);

	}

	/*
	 * This method performs dual operation depending on the @search flag
	 * true - search for the number
	 * false - perform only traversal
	 */

	private void BFSTraverseOrSearch(boolean search) {
		LinkedList<Node> queue = new LinkedList<>();
		queue.add(rootNode);
	while(!queue.isEmpty()) {
			Node node = queue.poll();
			if(search) {
				System.out.println("Is " + node.val + " = " + numToFind + "?");

			// If the number matches print that value is found and exit from the method.

				if(node.val == numToFind) {
					System.out.println("SUCCESS, Value found in the tree:" + numToFind);
				return;
				}
			}
			else {
				System.out.print(node.val + " ");
			}
			Node leftNode = node.left;
			Node rightNode = node.right;
			if(leftNode!=null) {
				queue.add(leftNode);
			}

			if(rightNode!=null) {
				queue.add(rightNode);
			}
		}

		if(search) {
			System.out.println("Fail, Value not found in the tree:" + numToFind);
		}
	}

	public static void main(String args[]) {

		BFSAlgorithmForBinaryTree bfs = new BFSAlgorithmForBinaryTree();	
		Scanner scan = new Scanner(System.in);
		boolean expectNumToFind = false;
		boolean numProvided = false;
		String input;
		System.out.println("Enter the edges:\n");

		/*
		 *  Take user input from console using scanner.
		 *  User provides tree data in form of parent - child edges.
		 *  Eg. 
		 *  6,2
		 *  6,8
		 *  2,1
		 *  2,4
		 *  User enter * to denote end of edge inputs
		 *  User enters the number to find in the tree
		 *  User enters # to stop the program from taking input and process the data
		 */

		while ((input = scan.nextLine()) != null) {
			if (input.equals("*")) {
				// Expect that next input to be the number to search.
				expectNumToFind = true;
			} else if (input.equals("#")) {
				System.out.print("\nBFS traversal of the tree is:");
				// Perform the tree traversal using BFS algorithm
				bfs.BFSTraverseOrSearch(false);
				System.out.print("\n");
				// Search for the number provided by the user earlier.
				bfs.BFSTraverseOrSearch(true);
				return;
			} else if (input.length() == 3 && input.contains(",")) {
				// Split the input using (,) as delimiter.
				int parent = Integer.valueOf(input.split(",")[0]);
				int child = Integer.valueOf(input.split(",")[1]);
				// Add the nodes to the binary tree.
				bfs.addNodeToTree(parent, child);
			} else if (expectNumToFind && !numProvided) {
				// Store the number to find
				bfs.numToFind = Integer.valueOf(input);
				numProvided = true;
			}
			else if(numProvided) {
				System.out.print("Number to find is already provided. Enter # to show result\n");
			}
			else {
				System.out.println("Unexpected input: " + input + "\n");
			}
		}
		scan.close();
	}
}
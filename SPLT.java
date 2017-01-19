package SPLT_A4;

public class SPLT implements SPLT_Interface {
	private BST_Node root;
	private int size;

	public SPLT() {
		this.size = 0;
		this.root = null;
	}

	public BST_Node getRoot() { // please keep this in here! I need your root
								// node to test your tree!
		return root;
	}

	public void insert(String s) {
		if (root == null){
			BST_Node newNode = new BST_Node(s);
			root = newNode;
			root.par = null;
			size = size + 1;
			root.justMade = false;
			//System.out.println("root created " + newNode.data);
			return;
		} else {
			BST_Node success;
			success = root.insertNode(s);
			root = success;
			if(success.justMade){
				size = size + 1;
				success.justMade = false;
				return;
			} else {
				return;
			}
		}

	}

	public void remove(String s) {
		if(root == null){
			return;
		} 
		boolean found = this.contains(s);
		if(found){
			if(root.right == null && root.left == null){
				//System.out.println("root " + root.data + " removed no children");
				root = null;
				size = 0;
				return;
			} else if(root.left != null && root.right == null){
				//System.out.println("root " + root.data + " removed left child");
				root = root.left;
				root.par = null;
				size = size - 1;
				return;		
			} else if(root.right != null && root.left == null){
				//System.out.println("root " + root.data + " removed right child");
				root = root.right;
				root.par = null;
				size = size - 1;
				return;
			} else if(root.right != null && root.left != null){
				BST_Node maxNode = root.left.findMax();
				maxNode.right = root.right;
				root.right.par = maxNode;
				root = maxNode;
				root.par = null;
				size = size - 1;
				return;
			}
		}
	}

	public String findMin() {
		if(root == null){
			return null;
		} else {
			BST_Node node = root.findMin();
			root = node;
			return root.data;
		}
	}

	public String findMax() {
		if(root == null){
			return null;
		} else {
			BST_Node node = root.findMax();
			root = node;
			return root.data;
		}
	}

	public boolean empty() {
		if(size == 0 || root == null){
			return true;
		} else {
			return false;
		}
	}

	public boolean contains(String s) {
		if(root == null){
			return false;
		} 
		BST_Node node = root.containsNode(s);
		root = node;
		if(s.compareTo(node.data) == 0){
			return true;
		}
		return false;
	}

	public int size() {
		return size;
	}

	public int height() {
		if(this.empty() == true){
			return -1;
		} else {
			return root.getHeight();
		}
		
	}
}
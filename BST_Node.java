package SPLT_A4;

public class BST_Node {
	String data;
	BST_Node left;
	BST_Node right;
	BST_Node par;
	boolean justMade;

	BST_Node(String data) {
		this.data = data;
		this.justMade = true;
		this.left = null;
		this.right = null;
		this.par = null;
	}

	BST_Node(String data, BST_Node left, BST_Node right, BST_Node par) { 
		this.data = data;
		this.left = left;
		this.right = right;
		this.par = par;
		this.justMade = true;
	}


	public String getData() {
		return data;
	}

	public BST_Node getLeft() {
		return left;
	}

	public BST_Node getRight() {
		return right;
	}

	public BST_Node containsNode(String s) {
		int compare = s.compareTo(this.data);
		  if (compare == 0){
			  //System.out.println( s + "found it ");
			  this.splay(this);
			  return this;  
		  } else if(compare < 0){
			  //going left
			  if(this.left == null){
				  this.splay(this);
				  return this;
			  } else {
				  return this.left.containsNode(s);
			  }
		  } else if (compare > 0){
			  //going right
			  if(this.right == null){
				  this.splay(this);
				  return this;
			  } else {
				  return this.right.containsNode(s);
			  }
		  } 
		  return this; 
	} 

	public BST_Node insertNode(String s) {
		//3 cases
		  int compare = s.compareTo(this.data);
		  if (compare == 0){
			  System.out.println( s + " already in tree ");
			  this.splay(this);
			  return this;  
		  } else if(compare < 0){
			  //going left
			  if(this.left == null){
				  BST_Node leftNode = new BST_Node(s);
				  leftNode.par = this;
				  this.left = leftNode;
				  //System.out.println(s + " insert left of " + this.data);
				  leftNode.splay(leftNode);
				  return leftNode;
			  } else {
				  return this.left.insertNode(s);
			  }
			  
		  } else if (compare > 0){
			  //going right
			  if(this.right == null){
				  BST_Node rightNode = new BST_Node(s);
				  rightNode.par = this;
				  this.right = rightNode;
				  //System.out.println(s + " insert right of " + this.data);
				  rightNode.splay(rightNode);
				  return rightNode;
			  } else {
				  return this.right.insertNode(s);
			  }
		  } 
		  return this;
	} // Really same logic as above note

	public boolean removeNode(String s) {
		  return false; 
	} 
	public BST_Node findMin() {
		if (this.left == null){
			 this.splay(this);
			  return this;
		  } else { 
			 return this.left.findMin();
		  }
		
	}

	public BST_Node findMax() {
		if(this.right == null){
			this.splay(this);
			  return this;
		  } else {
			  return this.right.findMax();
		  }
	}

	public int getHeight() {
		int leftHeight;
		  int rightHeight;
		  //height of left subtree
		  if(this.left == null){
			  leftHeight = -1;
		  } else {
			  leftHeight = this.left.getHeight();
		  }
		  //height of right subtree
		  if(this.right == null){
			  rightHeight = -1;
		  } else {
			  rightHeight = this.right.getHeight();
		  }
		  //compare subtrees
		  if(rightHeight >= leftHeight){
			  return rightHeight + 1;
		  } else {
			  return leftHeight + 1;
		  }
	}

	private void splay(BST_Node toSplay) {
		if(toSplay.par == null){
			//System.out.println("node is root");
		} else if(toSplay.par.par == null && toSplay.data.compareTo(toSplay.par.data) < 0){
			//System.out.println("Zig left " + toSplay.data);
			toSplay.rotateRight(toSplay);
		} else if(toSplay.par.par == null && toSplay.data.compareTo(toSplay.par.data) > 0){
			//System.out.println("Zig right " + toSplay.data);
			toSplay.rotateLeft(toSplay);
		} else if(toSplay.data.compareTo(toSplay.par.data) < 0 && toSplay.par.data.compareTo(toSplay.par.par.data) < 0){
			//System.out.println("Zig-Zig left " + toSplay.data);
			BST_Node top = toSplay.par.par.par;
			toSplay.rotateRight(toSplay.par);
			toSplay.rotateRight(toSplay);
			if(top != null){
				toSplay.splay(toSplay);
			}
		} else if(toSplay.data.compareTo(toSplay.par.data) > 0 && toSplay.par.data.compareTo(toSplay.par.par.data) > 0){
			//System.out.println("Zig-Zig right " + toSplay.data);
			BST_Node top = toSplay.par.par.par;
			toSplay.rotateLeft(toSplay.par);
			toSplay.rotateLeft(toSplay);
			if(top != null){
				toSplay.splay(toSplay);
			}
		} else if(toSplay.data.compareTo(toSplay.par.data) < 0 && toSplay.par.data.compareTo(toSplay.par.par.data) > 0){
			//System.out.println("Zig-Zag left right " + toSplay.data);
			BST_Node top = toSplay.par.par.par;
			toSplay.rotateRight(toSplay);
			toSplay.rotateLeft(toSplay);
			if(top != null){
				toSplay.splay(toSplay);
			}
		} else if(toSplay.data.compareTo(toSplay.par.data) > 0 && toSplay.par.data.compareTo(toSplay.par.par.data) < 0){
			//System.out.println("Zig-Zag right left " + toSplay.data);
			BST_Node top = toSplay.par.par.par;
			toSplay.rotateLeft(toSplay);
			toSplay.rotateRight(toSplay);
			if(top != null){
				toSplay.splay(toSplay);
			}
		} else {
			System.out.println("splat");
		}
		return;
	}
	
	private void rotateRight(BST_Node node){
		BST_Node top = node.par.par;
		node.par.par = node;
		node.par.left = node.right;
		if(node.right != null){
			node.right.par = node.par;
		}	
		node.right = node.par;
		if(top == null){
			node.par = null;
		} else if(node.data.compareTo(top.data) < 0){
			node.par = top;
			top.left = node;
		} else {
			node.par = top;
			top.right = node;
		}
	}
	
	private void rotateLeft(BST_Node node){
		BST_Node top = node.par.par;
		node.par.par = node;
		node.par.right = node.left;
		if(node.left != null){
			node.left.par = node.par;
		}	
		node.left = node.par;
		if(top == null){
			node.par = null;
		} else if(node.data.compareTo(top.data) < 0){
			node.par = top;
			top.left = node;
		} else {
			node.par = top;
			top.right = node;
		}
	}
}
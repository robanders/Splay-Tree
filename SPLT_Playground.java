package SPLT_A4;

public class SPLT_Playground {
  public static void main(String[] args){
    genTest();
  }
  
  public static void genTest(){
    SPLT tree= new SPLT();
    /*tree.insert("hello");
    tree.insert("world");
    tree.insert("my");
    tree.insert("name");
    tree.insert("is");
    tree.insert("blank");
    tree.remove("hello");
    */
    
    tree.insert("0");
    tree.insert("C");
    tree.insert("A");
    tree.insert("B");
    tree.insert("E");
    tree.insert("D");
    tree.remove("C");
    tree.remove("A");
    tree.remove("D");
    tree.insert("e");
    tree.insert("j");
    tree.insert("c");
    tree.insert("d");
    tree.insert("a");
    /*tree.insert("l");
    tree.insert("n");
    tree.insert("m");
    tree.insert("p"); */
    //tree.contains("h");
    //tree.contains("l");
   // tree.contains("m");
    //tree.contains("d");
    //tree.contains("e");
    //tree.contains("n");
    //tree.contains("d");
    //tree.findMin();
    //tree.remove("k");
    
    System.out.println("size is "+tree.size());
    
    printLevelOrder(tree);
  }

    static void printLevelOrder(SPLT tree){ 
    //will print your current tree in Level-Order...
    //https://en.wikipedia.org/wiki/Tree_traversal
      int h=tree.getRoot().getHeight();
      for(int i=0;i<=h;i++){
        System.out.print("Level "+i+":");
        printGivenLevel(tree.getRoot(), i);
        System.out.println();
      }
      
    }
    static void printGivenLevel(BST_Node root,int level){
      if(root==null)return;
      if(level==0)System.out.print(root.data+" ");
      else if(level>0){
        printGivenLevel(root.left,level-1);
        printGivenLevel(root.right,level-1);
      }
    }
  
}
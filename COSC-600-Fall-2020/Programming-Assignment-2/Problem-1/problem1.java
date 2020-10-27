import java.util.Random;

public class RandomSortTree {
   
      static TreeNode root;
   
      static class TreeNode {

          double item;
          TreeNode left;
          TreeNode right;
          TreeNode(double x) {
             item = x;
          }
      }
   
      static void treeInsert(double x) {
         if ( root == null ) {
             root = new TreeNode( x );
             return;
          }
          TreeNode runner;
          runner = root;
          while (true) {
             if ( x < runner.item ) {
                if ( runner.left == null ) {
                   runner.left = new TreeNode( x );
                   return;
                }
                else
                   runner = runner.left;
             }
             else if ( x > runner.item) {
                if ( runner.right == null ) {
                   runner.right = new TreeNode( x );
                   return;
                }
                else
                   runner = runner.right;
              }
              else {
                  return;
              }
          }
      }

      static int countLeaves(TreeNode node) {
          if (node == null)
             return 0;
          else if (node.left == null && node.right == null)
             return 1;
          else
             return countLeaves(node.left) + countLeaves(node.right);
      }
      
      static int sumOfLeafDepths( TreeNode node, int depth ) {
          if ( node == null ) {
             return 0;
          }
          else if ( node.left == null && node.right == null) {
             return depth;
          }
          else {
             return sumOfLeafDepths(node.left, depth + 1) 
                         + sumOfLeafDepths(node.right, depth + 1);
          }
        }
      
      static int maximumLeafDepth( TreeNode node, int depth ) {
          if ( node == null ) {
             return 0;
          }
          else if ( node.left == null && node.right == null) {
             return depth;
          }
          else {
             int leftMax = maximumLeafDepth(node.left, depth + 1);
             int rightMax =  maximumLeafDepth(node.right, depth + 1);
             if (leftMax > rightMax)
                return leftMax;
             else
                return rightMax;
          }
      }
      
      public static void main(String[] args) {
            
        root = null;   
        Random random = new Random();               
         
        // Insert 5000 random items.
         for (int i = 0; i < 5000; i++)
             treeInsert(random.nextInt(50000));          
             
         int leafCount = countLeaves(root);
         int depthSum = sumOfLeafDepths(root,0);
         int depthMax = maximumLeafDepth(root,0);
         double averageDepth = ((double)depthSum) / leafCount;
         
         System.out.println("Number of leaves:         " + leafCount);
         System.out.println("Average depth of leaves:  " + averageDepth);
         System.out.println("Maximum depth of leaves:  " + depthMax);
      }
   }
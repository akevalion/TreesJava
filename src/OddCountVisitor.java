import java.lang.reflect.*;
public class OddCountVisitor<T> implements TreeVisitor<T>{
    public Tree<T>[] oddNodes;
    private int index;
    public void visitTree(Tree<T> aTree){
        int size = this.countOddNodes(aTree, 0);
        oddNodes = new Tree[size];
        index = 0;
        this.addOddNodes(aTree, 0);
    }
    public int countOddNodes(Tree<T> aTree, int level){
        if(aTree == null)
            return 0;
        else{
            int sizeLeft = this.countOddNodes(aTree.left, level + 1);
            int sizeRight = this.countOddNodes(aTree.right, level + 1);
            int oddSize= sizeLeft + sizeRight;
            if(this.isOdd(level))
                oddSize++;
            return oddSize;
        }
            
    }
    public boolean isOdd(int aNumber){
        return aNumber % 2 == 1;
    }
    public void addOddNodes(Tree<T> aTree, int level){
        if(aTree == null) return;
        if(this.isOdd(level)){
            oddNodes[index] = aTree;
            index ++;
        }
        this.addOddNodes(aTree.left, level+1);
        this.addOddNodes(aTree.right, level+1);
    }
}

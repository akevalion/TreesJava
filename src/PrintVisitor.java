import java.util.*;
public class PrintVisitor<T> implements TreeVisitor<T>{
    public String result="";
    public int space=0;
    public void visitTree(Tree<T> aTree){
        if(aTree == null) return;
        PrintedTree<T> newTree = this.createTree(aTree, 0);
        ArrayList<PrintedTree<T>> list = new ArrayList<PrintedTree<T>>();
        list.add(newTree);
        this.print(list, 0, 0);
    }
    public PrintedTree<T> createTree(Tree<T> aTree, int level) {
        if(aTree == null) return null;
        PrintedTree<T> newTree = new PrintedTree<T>();
        
        newTree.left= this.createTree(aTree.left, level + 1);
        
        newTree.data = aTree.data;
        newTree.dataSize = aTree.data.toString().length();
        newTree.spaces = space;
        newTree.totalSpaces = newTree.spaces + newTree.dataSize;
        space += newTree.dataSize;
        newTree.level = level;
        
        newTree.right = this.createTree(aTree.right, level + 1);
        return newTree;
    }
    
    public void add(ArrayList<PrintedTree<T>> list, PrintedTree<T> aTree){
        if(aTree != null) list.add(aTree);
    }
    public void print(ArrayList<PrintedTree<T>> list, int prevLevel, int prevSpace){
        if (list.isEmpty()) return;
        PrintedTree<T> aTree = list.remove(0);
        int n = aTree.spaces;
        if(prevLevel != aTree.level) 
            result += "\n";
        else
            n -= prevSpace;
        while (n -- > 0){result += "-";}
        
        result += aTree.data.toString();
        this.add(list, aTree.left);
        this.add(list, aTree.right);
        this.print(list, aTree.level, aTree.totalSpaces);
    }
}
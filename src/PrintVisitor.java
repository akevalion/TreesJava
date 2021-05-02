import java.util.*;
public class PrintVisitor<T> implements TreeVisitor<T>{
    public String result="";
    public int space=0;
    public void visitTree(Tree<T> aTree){
        if(aTree == null) return;
        PrintedTree<T> newTree = this.createTree(aTree, 0);
        this.spaces(newTree, 0);
        ArrayList<PrintedTree<T>> list = new ArrayList();
        list.add(newTree);
        this.print(list, 0);
    }
    public PrintedTree<T> createTree(Tree<T> aTree, int level) {
        if(aTree == null) return null;
        PrintedTree<T> newTree = new PrintedTree();
        newTree.data = aTree.data;
        newTree.level = level;
        newTree.left= this.createTree(aTree.left, level + 1);
        newTree.right = this.createTree(aTree.right, level + 1);
        return newTree;
    }
    public int spaces(PrintedTree<T> aTree, int carry){
        if(aTree == null) return carry;
        if(aTree.size != 0) return aTree.size;
        aTree.size =this.spaces(aTree.left, carry );
        int dataSize = aTree.data.toString().length();
        int res = aTree.size + this.spaces(aTree.right, dataSize);
        return res;
    }
    public void add(ArrayList<PrintedTree<T>> list, PrintedTree<T> aTree){
        if(aTree != null) list.add(aTree);
    }
    public void print(ArrayList<PrintedTree<T>> list, int prev){
        if (list.isEmpty()) return;
        PrintedTree<T> aTree = list.remove(0);
        int n = aTree.size;
        if(prev != aTree.level) result += "\n";
        while (n -- > 0){result += "-";}
        
        result += aTree.data.toString();
        this.add(list, aTree.left);
        this.add(list, aTree.right);
        this.print(list, aTree.level);
    }
}
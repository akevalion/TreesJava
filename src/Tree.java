public class Tree<T>{
    public T data;
    public Tree<T> left, right;
    public Tree(T object){
        data = object;
    }
    public void accept(TreeVisitor<T> visitor){
        visitor.visitTree(this);
    }
}
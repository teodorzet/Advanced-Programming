import java.util.ConcurrentModificationException;


class Tree{
    protected int currentNode;
    private int maxChilds;


    public Tree(int currentNode,int maxChilds) {
        this.currentNode = currentNode;
        this.maxChilds = maxChilds;
    }

    public void printRandomTree(int level, int nextNrOfChilds){
        /*if (maxNodes<=0)
            return;*/

        int nextNodes= (int)(Math.random()*maxChilds);

        for(int i=0;i<nextNrOfChilds;i++){
            for(int j=0;j<level;j++){
                System.out.print("\t");
            }
            if (nextNodes==0)
            System.out.print("-node" + this.currentNode);
            else
            System.out.print("+node" + this.currentNode);

            System.out.println();
            this.currentNode++;
            printRandomTree(level+1,nextNodes);
        }
    }
}

public class Bonus {


    public static void main(String args[]){
        Tree tree= new Tree(0,3);
        tree.printRandomTree(0,1);

    }
}

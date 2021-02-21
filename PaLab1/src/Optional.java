import java.util.Scanner;
import java.lang.*;

class Graph {
    public int adjacencyMatrix[][], partialTreeAdjacencyMatrix[][];
    public int visitedNodes[];
    public int n;
    public long startTime;

    public Graph(){
        n=0;
    }
    public void createRandomGraph() {
        n = 0;
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        startTime=System.nanoTime();

        visitedNodes= new int [n];

        adjacencyMatrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                adjacencyMatrix[i][j] = 0;
                visitedNodes[i]=1;
                visitedNodes[j]=1;
                if (i != j) {
                    if (((int) (Math.random() * 10) < 5)) {
                        adjacencyMatrix[i][j] = 0;
                        adjacencyMatrix[j][i] = 0;
                    } else {
                        adjacencyMatrix[i][j] = 1;
                        adjacencyMatrix[j][i] = 1;
                    }
                }
            }
        }
    }
    public void printGraph(){
        System.out.println("Matricea de adiacenta a grafului: ");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print("[" + adjacencyMatrix[i][j] + "]");
            }
            System.out.print("\n");
        }
        System.out.println();
    }
    public void printTree(){
        System.out.println("Matricea de adiacenta a arborelui partial: ");
        for(int i=0;i<n;i++) {
            for (int j = 0; j < n; j++){
                System.out.print("[" + partialTreeAdjacencyMatrix[i][j] + "]");
            }
            System.out.println();
        }
    }
    public void dfsSearch(int node){
        if (visitedNodes[node]==1){
            visitedNodes[node]=0;
            if (n<10000)
                System.out.print(node + " ");
        }
        for(int i=0;i<n;i++){
            if (adjacencyMatrix[i][node]==1 && visitedNodes[i]==1){
                if (n<10000)
                    System.out.print(i + " ");
                visitedNodes[i]=0;

                dfsSearch(i);
            }
        }
    }
    public int findNode(){
        for(int i=0;i<n;i++)
            if (visitedNodes[i]==1){
                return i;
            }
        return -1;
    }
    public int allVisited(){
        for(int i=0;i<n;i++){
            if (visitedNodes[i]==1){
                return 0;
            }
        }
        return 1;
    }
    public void renewVisitedNodes(){
        for(int i=0;i<n;i++)
            visitedNodes[i]=1;
    }
    public int randomNode(){
        return (int) ((Math.random() * (n - 0)) + 0);
    }
    public void dfsPartialTree(int node){
        for(int i=0;i<n;i++){
            if (adjacencyMatrix[i][node]==1 && visitedNodes[i]==1){
                if (visitedNodes[node]==1)
                    visitedNodes[node]=0;
                visitedNodes[i]=0;
                partialTreeAdjacencyMatrix[i][node]=1;
                partialTreeAdjacencyMatrix[node][i]=1;

                dfsPartialTree(i);
            }
        }
    }
    public void connectedComponents(){
        int numberOfComponents=0;
        while(allVisited()==0){
            numberOfComponents++;
            //System.out.println("incepem din nodul " + findNode());
            dfsSearch(findNode());
            System.out.println();

        }
        renewVisitedNodes();
        System.out.println("\nSunt " + numberOfComponents + " componente conexe in graf. \n");

        if (numberOfComponents==1){
            partialTreeAdjacencyMatrix= new int[n][n];
            dfsPartialTree(randomNode());
            if (n<10000)
                printTree();

        }
    }
}

public class Optional {

    public static void main(String args[]){


        Graph graph = new Graph();

        graph.createRandomGraph();
        if (graph.n<10000)
            graph.printGraph();
        graph.connectedComponents();

        long endTime=System.nanoTime();
        System.out.println((endTime-graph.startTime) + " nanoseconds");
    }
}

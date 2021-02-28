package Bonus;

public class Problem {
    private int matrix[][];

    /**
     * constructor
     * @param matrix matricea costurilor
     */
    public Problem(int[][] matrix) {
        this.matrix = matrix;
    }
    public Problem() {

    }

    /**
     * setter pe matrix
     * @param matrix matricea de costuri
     */
    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
    /**
     * getter pe matrix
     * @return matricea de costuri
     */
    public int[][] getMatrix() {
        return matrix;
    }
    @Override
    public String toString() {
        return super.toString();
    }

    public static void main(String args[]) {
        long startTime = System.nanoTime();
        Source source1 = new Factory(300);
        Source source2 = new Warehouse(400);
        Source source3 = new Warehouse(500);
        Source source4 = new Factory(300);
        Source source5 = new Warehouse(400);
        Source source6 = new Warehouse(500);
        Source source7 = new Factory(300);
        Source source8 = new Warehouse(400);
        Source source9 = new Warehouse(500);

        Destination destination1 = new Destination("Mihai", "Popa", 250);
        Destination destination2 = new Destination("Tudor", "Lupu", 350);
        Destination destination3 = new Destination("Alex", "Toma", 400);
        Destination destination4 = new Destination("Stefan", "Chiriac", 200);
        Destination destination5 = new Destination("Mihai", "Popa", 250);
        Destination destination6 = new Destination("Tudor", "Lupu", 350);
        Destination destination7 = new Destination("Alex", "Toma", 400);
        Destination destination8 = new Destination("Stefan", "Chiriac", 200);
        Destination destination9 = new Destination("Mihai", "Popa", 250);
        Destination destination10 = new Destination("Tudor", "Lupu", 350);
        Destination destination11 = new Destination("Alex", "Toma", 400);
        Destination destination12 = new Destination("Stefan", "Chiriac", 200);

        int matrix[][] = new int[9][12];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 12; j++) {
                matrix[i][j] = ((int) (Math.random() * (10 - 1) + 1));
                System.out.print("[" + matrix[i][j] + "]");
            }
            System.out.println();
        }
        //int matrix[][] = {{3,1,7,4},{2,6,5,9},{8,3,3,2}};

        Problem problem1 = new Problem();
        problem1.setMatrix(matrix);

        Solution solution = new Solution(9,12);

        Source sources[] = {source1,source2,source3,source4,source5,source6,source7,source8,source9};
        Destination destinations[] = {destination1,destination2,destination3,destination4,destination5,destination6,destination7,destination8,destination9,destination10,destination11,destination12};

        solution.getSolution(problem1.getMatrix(),9,12, sources, destinations);
        long endTime = System.nanoTime();
        System.out.println((float)(endTime-startTime)/1_000_000_000 + " seconds running time.");
    }
}

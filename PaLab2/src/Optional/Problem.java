package Optional;

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
        Source source1 = new Factory(10);
        Source source2 = new Warehouse(35);
        Source source3 = new Warehouse(25);
        Destination destination1 = new Destination("Mihai", "Popa", 20);
        Destination destination2 = new Destination("Tudor", "Lupu", 25);
        Destination destination3 = new Destination("Alex", "Toma", 25);

        int matrix[][] = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = ((int) (Math.random() * 10));
                System.out.print("[" + matrix[i][j] + "]");
            }
            System.out.println();
        }

        Problem problem1 = new Problem();
        problem1.setMatrix(matrix);

        Solution solution = new Solution(3,3);

        Source sources[] = {source1,source2,source3};
        Destination destinations[] = {destination1,destination2,destination3};

        solution.getSolution(problem1.getMatrix(),3,3, sources, destinations);
    }
}


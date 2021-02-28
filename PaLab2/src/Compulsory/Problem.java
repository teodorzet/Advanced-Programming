package Compulsory;

public class Problem {
    private int matrix[][] = new int[10][10];

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public Problem(int[][] matrix) {
        this.matrix = matrix;
    }
    public Problem() {

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static void main(String args[]) {
        Source S1 = new Source(SourceType.FACTORY,10);
        Source S2 = new Source(SourceType.WAREHOUSE,35);
        Source S3 = new Source(SourceType.WAREHOUSE,25);

        Destination D1 = new Destination("Mihai","Popa", 20);
        Destination D2 = new Destination("Tudor","Lupu", 25);
        Destination D3 = new Destination("Alex","Toma", 25);

        int matrix[][]=new int[3][3];
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++){
                matrix[i][j]=((int)(Math.random()*50));
            }

        Problem problem1 = new Problem();
            problem1.setMatrix(matrix);

        System.out.println(S1.toString());
        System.out.println(S2.toString());
        System.out.println(S3.toString());
    }


}

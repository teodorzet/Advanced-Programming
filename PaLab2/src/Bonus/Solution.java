package Bonus;

import java.util.Arrays;

public class Solution {
    private int matrix[][];
    protected int columnDifferences[],rowDifferences[];
    /**
     * constructor
     * @param n numarul de surse
     * @param m numarul de destinatii
     */
    public Solution(int n,int m){
        this.matrix = new int[n][m];
    };
    /**
     * getter pe matrix
     * @param i linia
     * @param j coloana
     * @return valoare din matrix de pe pozitia [i][j]
     */
    public int getMatrixElement(int i, int j) {
        return matrix[i][j];
    }
    /**
     * setter pe matrix
     * @param i linia
     * @param j coloana
     * @param value valoarea
     */
    public void setMatrixElement(int i, int j, int value) {
        this.matrix[i][j] = value;
    }
    /**
     *
     * @param matrix matricea costurilor
     * @param n numarul de surse
     * @param m numarul de destinatii
     * @param supply sursele
     * @param demand destinatiile
     */
    public void setColumnDifferences(int matrix[][], int n, int m, Source supply[], Destination demand[]){
        /*
         * Functie ce completeaza vectorul columnDifferences cu absolutul diferentei dintre cele mai mici 2 valori de pe
         * fiecare coloana cu valoarile supplyului de pe liniile respective diferite de 0, sau -1 daca coloana are
         * valoarea in demand 0.
         */
        columnDifferences = new int[m];
        for(int j=0;j<m;j++) {
            int firstLeast=-1,secondLeast=-1;
            for (int i = 0; i < n; i++) {
                if (supply[i].getCapacity() != 0)
                if (firstLeast == -1)
                    firstLeast = matrix[i][j];
                else
                if (secondLeast == -1){
                    secondLeast = matrix[i][j];
                    if (secondLeast < firstLeast){
                        int aux = firstLeast;
                        firstLeast = secondLeast;
                        secondLeast = aux;
                    }
                } else if (matrix[i][j] < secondLeast){
                    secondLeast = matrix[i][j];
                    if (secondLeast < firstLeast){
                        int aux = firstLeast;
                        firstLeast = secondLeast;
                        secondLeast = aux;
                    }
                }
            }
            if (demand[j].getDemand()!=0)
            columnDifferences[j] = Math.abs(firstLeast - secondLeast);
            else
                columnDifferences[j] = -1;
        }
    }
    /**
     *
     * @param matrix matricea costurilor
     * @param n numarul de surse
     * @param m numarul de destinatii
     * @param supply sursele
     * @param demand destinatiile
     */
    public void setRowDifferences(int matrix[][], int n, int m, Source supply[], Destination demand[]){
        /*
        * Functie ce completeaza vectorul rowDifferences cu absolutul diferentei dintre cele mai mici 2 valori de pe
        * fiecare linie cu valoarile demandului de pe coloanele respective diferite de 0, sau -1 daca linia are valoarea
        * 0 in supply.
         */
        rowDifferences = new int[n];
        for(int i=0;i<n;i++) {
            int firstLeast=-1,secondLeast=-1;
            for (int j = 0; j < m; j++) {
                if (demand[j].getDemand() != 0)
                if (firstLeast == -1)
                    firstLeast = matrix[i][j];
                else
                if (secondLeast == -1){
                    secondLeast = matrix[i][j];
                    if (secondLeast < firstLeast){
                        int aux = firstLeast;
                        firstLeast = secondLeast;
                        secondLeast = aux;
                    }
                } else if (matrix[i][j] < secondLeast){
                    secondLeast = matrix[i][j];
                    if (secondLeast < firstLeast){
                        int aux = firstLeast;
                        firstLeast = secondLeast;
                        secondLeast = aux;
                    }
                }
            }
            if (supply[i].getCapacity() != 0)
            rowDifferences[i] = Math.abs(firstLeast - secondLeast);
            else
                rowDifferences[i] = -1;
        }
    }
    /**
     *
     * @param n numarul de surse
     * @param m numarul de destinatii
     * @param supply sursele
     * @param demand destinatiile
     * @return 1 daca exista o valoare diferita de 0 in surse sau destinatii sau 0 daca nu exista
     */
    public int whileCondition(int n, int m, Source supply[],Destination demand[]){
        /* Conditia algoritmului de oprire, cand nu exista supply si demand inca neredirectionat.
         */
        for(int i=0;i<n;i++){
            if (supply[i].getCapacity() != 0)
                return 1;
        }
        for(int j=0;j<m;j++){
            if (demand[j].getDemand() != 0)
                return 1;
        }
        return 0;
    }
    /**
     *
     * @return valorile din columnDifferences si rowDifferences
     */
    @Override
    public String toString() {
        return
                "columnDifferences=" + Arrays.toString(columnDifferences) +
                ", rowDifferences=" + Arrays.toString(rowDifferences) +
                '}';
    }
    /**
     *
     * @param matrix matricea costurilor
     * @param n numarul de surse
     * @param m numarul de destinatii
     * @param supply sursele
     * @param demand destinatiile
     */
    public void getSolution(int matrix[][], int n, int m, Source supply[], Destination demand[]) {
        /*
        * Algoritmul solutiei este "the Vogel's approximation method".
         */

        /*
        * Conditia de oprire a algoritmului este atunci cand toate valorile de demand si supply sunt 0. La fiecare pas
        * apelam doua functii care actualizeaza vectorii columnDifference si rowDifference, in care se afla diferenta
        * absoluta dintre cele mai mici 2 valori de pe fiecare linie, respectiv coloana. In cazul in care o linie sau o
        * coloana este anulata (demandul sau supplyul este egal cu 0), valoarea va fi -1.
         */
        while(whileCondition(n,m,supply,demand) != 0) {
        setColumnDifferences(matrix, n, m, supply, demand);
        setRowDifferences(matrix, n, m, supply, demand);
        /*
        * Cautam cea mai mare valoare din cei doi vectori rowDifference si columnDifference si o retinem, la fel ca si
        * daca face parte din rowDifference sau columnDifference, si pozitia din acel vector.
         */
        int rowOrColumn=0,value=rowDifferences[0],rowOrColumnNumber=0;
        for(int i=1;i<n;i++)
            if (rowDifferences[i] > value){
                value = rowDifferences[i];
                rowOrColumnNumber = i;
            }
        for(int i=0;i<m;i++)
            if (columnDifferences[i] > value){
                value = columnDifferences[i];
                rowOrColumnNumber = i;
                rowOrColumn = 1;
            }

        /*
        * In functie din ce linie sau coloana face parte aceasta valoare, cautam costul minim care nu face parte dintr-o
        * linie sau coloana anulata. Dupa ce am gasit-o, acordam supplyul maxim posibil in demand, adica scadem din
        * demand supplyul maxim posibil, respectiv din supply demandul ocupat si inseram in matricea suport din clasa
        * supplyul acordat in matrix[i][j], unde i este sursa si j este destinatia.
         */
            if (rowOrColumn == 0) {
                //System.out.println("Se afla in row difference nr " + value);
                int minValue = -1;
                int minValuePlace = 0;
                for (int j = 0; j < m; j++) {
                    if ((minValue == -1 && demand[j].getDemand() != 0) || (matrix[rowOrColumnNumber][j] < minValue && demand[j].getDemand() != 0)) {
                        minValue = matrix[rowOrColumnNumber][j];
                        minValuePlace = j;
                    }
                }
                if (supply[rowOrColumnNumber].getCapacity() <= demand[minValuePlace].getDemand()) {
                    setMatrixElement(rowOrColumnNumber, minValuePlace, supply[rowOrColumnNumber].getCapacity());
                    demand[minValuePlace].setDemand(demand[minValuePlace].getDemand() - supply[rowOrColumnNumber].getCapacity());
                    supply[rowOrColumnNumber].setCapacity(0);
                } else {
                    setMatrixElement(rowOrColumnNumber, minValuePlace, demand[minValuePlace].getDemand());
                    supply[rowOrColumnNumber].setCapacity(supply[rowOrColumnNumber].getCapacity() - demand[minValuePlace].getDemand());
                    demand[minValuePlace].setDemand(0);
                }
            } else {
                //System.out.println("Se afla in column difference nr " + value);
                int minValue = -1;
                int minValuePlace = 0;
                for (int i = 0; i < n; i++) {
                    if ((minValue == -1 && supply[i].getCapacity() != 0) || (matrix[i][rowOrColumnNumber] < minValue && supply[i].getCapacity() != 0)) {
                        minValue = matrix[i][rowOrColumnNumber];
                        minValuePlace = i;
                    }
                }
                if (demand[rowOrColumnNumber].getDemand() <= supply[minValuePlace].getCapacity()) {
                    setMatrixElement(minValuePlace, rowOrColumnNumber, demand[rowOrColumnNumber].getDemand());
                    supply[minValuePlace].setCapacity(supply[minValuePlace].getCapacity() - demand[rowOrColumnNumber].getDemand());
                    demand[rowOrColumnNumber].setDemand(0);
                } else {
                    setMatrixElement(minValuePlace, rowOrColumnNumber, supply[minValuePlace].getCapacity());
                    demand[rowOrColumnNumber].setDemand(demand[rowOrColumnNumber].getDemand() - supply[minValuePlace].getCapacity());
                    supply[minValuePlace].setCapacity(0);
                }
            }
        }
        int cost=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if (getMatrixElement(i,j)>0){
                    cost+=getMatrixElement(i,j)*matrix[i][j];
                    System.out.println("S"+ (i+1) + " -> D" + (j+1) + ": " + getMatrixElement(i,j) + "*" + matrix[i][j] + " = " + (getMatrixElement(i,j)*matrix[i][j]));
                }
            }
        }
        System.out.println("Total cost: " + cost);
    }
}

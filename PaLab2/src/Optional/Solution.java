package Optional;

public class Solution {
    private int matrix[][];
    /**
     * constructor
     * @param n numarul de surse
     * @param m numarul de destinatii
     */
    public Solution(int n,int m){
        this.matrix = new int[n][m];
    }
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
    public void getSolution(int matrix[][], int n, int m, Source supply[], Destination demand[]){
        /*
        * Algoritmul functiei este greedy, incepe de la prima sursa si completeaza supplyul maxim posibil incepind cu prima
        * destinatie pana la ultima destinatie, sau pana cand nu mai exista supply disponibil. In matricea clasei Solution este
        * completat pe pozitia [i][j] supplyul care este transmis la destinatia j din sursa i, iar la finalsunt afisate
        * fiecare transport dupa urmatorul model: sursa -> destinatie: unitati*cost = produsul rezultat si costul total
        * al problemei.
         */
        for(int i=0;i<n;i++) {
            for (int j = 0; j < m; j++) {
                /*
                * Parcurgem fiecare combinatie de sursa si destinatie pana gasim un supply diferit de 0
                 */
                if (supply[i].getCapacity()!=0)
                    /*
                    * Dupa ce am gasit un supply, verificam daca este mai mare decat demandul sau mai mic.
                    * Daca este mai mic, il folosim pe tot, daca nu folosim doar cat este nevoie si actualizam
                    * valorile din demand si supply si introducem in matricea clasei Solution supplyul acordat
                    * destinatiei.
                     */
                    if (demand[j].getDemand() >= supply[i].getCapacity()) {
                        setMatrixElement(i,j,supply[i].getCapacity());
                        demand[j].setDemand(demand[j].getDemand() - supply[i].getCapacity());
                        supply[i].setCapacity(0);
                    }
                    else
                        if (demand[j].getDemand() < supply[i].getCapacity() && demand[j].getDemand()!=0){
                            setMatrixElement(i,j,demand[j].getDemand());
                            supply[i].setCapacity(supply[i].getCapacity() - demand[j].getDemand());
                            demand[j].setDemand(0);
                        }
            }
        }
        /*
        * Afisez din matrice cum sunt redirectionate supplyurile si costul total
         */
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

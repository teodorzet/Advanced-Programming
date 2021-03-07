package Optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TravelPlan {
    City city;
    List<Location> visitingOrder;
    public int costMatrix[][];
    public int isVisited[];

    public TravelPlan(City city, List<Location> visitingOrder) {
        this.city = city;
        this.visitingOrder = visitingOrder;
    }
    /**
     * cel mai scurt drum intre doua locatii, dupa preferintele turistului
     * @param location1
     * @param location2
     */
    public void shortestPathByPreferences(Location location1, Location location2){
        /*
        * In functie de preferintele turistului, se realizeaza drumurile de cost minim de la locatia initiala, prin
        * fiecare locatie pe care el o prefera, dupa care in cea finala.
         */
        System.out.print("Turistul nostru doreste sa ajunga din " + location1.getName() + " in " + location2.getName());
        int i;
        int totalCost = 0;
        if(visitingOrder.size() > 0) {
            System.out.print(" prin ");
            for (i = 0; i < visitingOrder.size(); i++) {
                System.out.print(visitingOrder.get(i).getName());
                if (visitingOrder.size() - i > 1)
                    System.out.print(", ");
            }

            System.out.println();


            for (i = 0; i < visitingOrder.size(); i++) {
                System.out.print("Distanta minima intre " + location1.getName() + " si " + visitingOrder.get(i).getName());
                totalCost += shortestPath(location1, visitingOrder.get(i));
                location1 = visitingOrder.get(i);
            }
            System.out.print("Distanta minima intre " + location1.getName() + " si " + location2.getName());
            totalCost += shortestPath(visitingOrder.get(i - 1), location2);
            System.out.println("Tot traseul va dura " + totalCost + " minute.");
        } else {
            totalCost += shortestPath(location1, location2);
            System.out.println("Tot traseul va dura " + totalCost + " minute.");
        }
    }
    /**
     * cel mai scurt drum intre doua locatii
     * @param location1
     * @param location2
     * @return costul drumului
     */
    public int shortestPath(Location location1, Location location2){
        /*
        * Functia foloseste algoritmul lui Dijkstra. Initializam matricea de adiacenta astfel incat nodurile reprezinta
        * locatiile iar valoarea de la un nod la altul reprezinta costul deplasarii de la primul la al doilea. Matricea
        * costMatrix reprezinta matricea suport in care se calculeaza costurile partiale.
         */
        int adjacencyMatrix[][] = new int[city.getNodes().size()][city.getNodes().size()];
        costMatrix = new int[city.getNodes().size()][city.getNodes().size()];
        isVisited = new int[city.getNodes().size()];
        List<Location> nodes = city.getNodes();

        for(int i=0;i< nodes.size();i++){
            Map<Location, Integer> mapOfCosts = nodes.get(i).getCost();
            for ( Map.Entry<Location, Integer> entry : mapOfCosts.entrySet()) {
                Location location = entry.getKey();
                int cost = entry.getValue();

                adjacencyMatrix[nodes.get(i).getIndexOfLocation()][location.getIndexOfLocation()] = cost;
            }
        }

        for(int i=0;i< nodes.size();i++){
            for(int j=0;j< nodes.size();j++){
                costMatrix[i][j]=adjacencyMatrix[i][j];
                //System.out.print("[" + adjacencyMatrix[i][j] + "]");
            }
            //System.out.println();
        }
        //System.out.println();

        int fromLocation = location1.getIndexOfLocation();
        int toLocation = location2.getIndexOfLocation();
        isVisited[fromLocation]=1;
        isVisited[toLocation]=1;
        /*
        * Parcurgem dfs graful, de la locatia initiala pana ajungem la cea finala.
         */
        dfsSearch(fromLocation,toLocation,adjacencyMatrix,0);

        /*for(int i=0;i< nodes.size();i++){
            for(int j=0;j< nodes.size();j++){
                System.out.print("[" + costMatrix[i][j] + "]");
            }
            System.out.println();
        }/*
        /*
        * Dupa ce am completat matricea suport, reconstituim de la final la inceput drumul. Cautam cea mai mica valoare de pe
        * coloana destinatiei dintr un drum parcurs de functia dfs si cautam nodurile precedente.
         */

        int minCost=(int) Double.POSITIVE_INFINITY;
        int positionI = 0, positionJ = toLocation;
            for (int i = 0; i < nodes.size(); i++) {
                if (costMatrix[i][toLocation] < minCost && costMatrix[i][toLocation] != 0 && isVisited[i]==1) {
                    minCost = costMatrix[i][toLocation];
                    positionI = i;
                }
            }
            System.out.println(" este de " + minCost + " minute.");
        System.out.println();
        int copyOfMinCost = minCost;

        /*
        * Gasim nodurile precedente in functie de valoarea anterioara a costului minim minus valoarea in matricea de
        * adiacenta, si le introducem intr-o lista de nr intregi.
         */
        if(adjacencyMatrix[fromLocation][toLocation] == 0) {
            List<Integer> pathOfNodes = new ArrayList<>();
            while (minCost != 0) {
                pathOfNodes.add(positionJ + 1);
                minCost = minCost - adjacencyMatrix[positionI][positionJ];
                for (int i = 0; i < nodes.size(); i++) {
                    if (costMatrix[i][positionI] == minCost) {
                        positionJ = positionI;
                        positionI = i;
                    }
                }
            }
            pathOfNodes.add(fromLocation + 1);
        }
        /*for(int i=pathOfNodes.size()-1;i>=0;i--){
            System.out.print("-" + pathOfNodes.get(i) + "- ");
        }*/
        return copyOfMinCost;
    }
    /**
     * parcurgere dfs a grafului
     * @param currentNode nodul curent
     * @param finalNode nodul destinatie
     * @param adjacencyMatrix matricea de adiacenta
     * @param lastCost costul adunat pana in pasul apelului
     */
    public void dfsSearch(int currentNode, int finalNode, int adjacencyMatrix[][], int lastCost){
        /*
        * Parcurgere dfs a grafului, care completeaza in matricea costMatrix costul minim de la nodul initial pana la
        * nodul final. Daca se gaseste un nod "x" in care drumul din acest nod are costul mai mic decat oricare alt drum
        *  in nodul x, se suprapune valoarea mai mica, calculata ca si costul pana la nodul curent din nodul initial +
        * costul dintre nodul curent si nodul x
         */
        if (currentNode == finalNode)
            return;

        for(int i=0;i<city.getNodes().size();i++){
            if (adjacencyMatrix[currentNode][i] > 0){
                if (costMatrix[currentNode][i] == 0) {
                    costMatrix[currentNode][i] = adjacencyMatrix[currentNode][i];
                    dfsSearch(i,finalNode,adjacencyMatrix,costMatrix[currentNode][i]);
                }
                else {
                    if (adjacencyMatrix[currentNode][i] + lastCost < costMatrix[currentNode][i] || costMatrix[currentNode][i] == adjacencyMatrix[currentNode][i]){
                        costMatrix[currentNode][i] = adjacencyMatrix[currentNode][i] + lastCost;
                        isVisited[i] = 1;
                        dfsSearch(i,finalNode,adjacencyMatrix,adjacencyMatrix[currentNode][i] + lastCost);
                    }
                }
            }
        }
    }
}

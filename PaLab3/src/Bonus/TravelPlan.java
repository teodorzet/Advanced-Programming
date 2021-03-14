package Bonus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

public class TravelPlan {
    City city;
    List<Location> visitingOrder;
    public int costMatrix[][];
    public int isVisited[];
    public int adjacencyMatrix[][];
    private int numberOfDays;
    private int minutesAvalible;
    private int locationsVisited[];
    private int maximumLocationsVisitedInADay;
    private int costOfTheDay;
    private int locationVisitedInCurrentPlan[];
    private int mostLocationsVisitedInCurrentDay[];
    private List<Integer> locationVisitedInCurrentStep;
    private List<Integer> pathOfLocationsVisitedInThisDay;

    public TravelPlan(City city, List<Location> visitingOrder, int numberOfDays, int minutesAvalible) {
        this.city = city;
        this.visitingOrder = visitingOrder;
        this.numberOfDays = numberOfDays;
        this.minutesAvalible = minutesAvalible;

        this.adjacencyMatrix = new int[city.getNodes().size()][city.getNodes().size()];
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

    }
    /**
     * cel mai scurt drum intre doua locatii, dupa preferintele turistului
     * @param location1
     * @param location2
     */
    public int shortestPathByPreferences(Location location1, Location location2){
        /*
        * In functie de preferintele turistului, se realizeaza drumurile de cost minim de la locatia initiala, prin
        * fiecare locatie pe care el o prefera, dupa care in cea finala.
         */
        //System.out.print("Turistul nostru doreste sa ajunga din " + location1.getName() + " in " + location2.getName());
        int i;
        int totalCost = 0;
        locationsVisited = new int[city.getNodes().size()];
        locationsVisited[location1.getIndexOfLocation()] = 1;
        locationsVisited[location2.getIndexOfLocation()] = 1;
        locationVisitedInCurrentStep = new ArrayList<>();

        if(visitingOrder.size() > 0) {

            /*System.out.print(" prin ");
            for (i = 0; i < visitingOrder.size(); i++) {
                System.out.print(visitingOrder.get(i).getName());
                if (visitingOrder.size() - i > 1)
                    System.out.print(", ");
            }

            System.out.println();*/


            for (i = 0; i < visitingOrder.size(); i++) {
                if (shortestPath(location1, visitingOrder.get(i),false) == -1){
                //System.out.println("Nu exista un drum");
                return -1;
            }
                locationsVisited[visitingOrder.get(i).getIndexOfLocation()] = 1;
                //System.out.print("Distanta minima intre " + location1.getName() + " si " + visitingOrder.get(i).getName());
                totalCost += shortestPath(location1, visitingOrder.get(i),true);
                location1 = visitingOrder.get(i);
            }
            if (shortestPath(visitingOrder.get(i-1),location2,false) == -1){
                //System.out.println("Nu exista un drum");
                return -1;
            }
            //System.out.print("Distanta minima intre " + location1.getName() + " si " + location2.getName());
            totalCost += shortestPath(visitingOrder.get(i - 1), location2,true);
            //System.out.println("Tot traseul va dura " + totalCost + " minute.");
        } else {
            totalCost += shortestPath(location1, location2,true);
            //System.out.println("Tot traseul va dura " + totalCost + " minute.");
        }
        return totalCost;
    }
    /**
     * cel mai scurt drum intre doua locatii
     * @param location1
     * @param location2
     * @return costul drumului
     */
    public int shortestPath(Location location1, Location location2, boolean addPath) {
        /*
         * Functia foloseste algoritmul lui Dijkstra. Initializam matricea de adiacenta astfel incat nodurile reprezinta
         * locatiile iar valoarea de la un nod la altul reprezinta costul deplasarii de la primul la al doilea. Matricea
         * costMatrix reprezinta matricea suport in care se calculeaza costurile partiale.
         */
        if (location1 == location2)
            return 0;

        int adjacencyMatrix[][] = new int[city.getNodes().size()][city.getNodes().size()];
        costMatrix = new int[city.getNodes().size()][city.getNodes().size()];
        isVisited = new int[city.getNodes().size()];
        List<Location> nodes = city.getNodes();

        for (int i = 0; i < nodes.size(); i++) {
            Map<Location, Integer> mapOfCosts = nodes.get(i).getCost();
            for (Map.Entry<Location, Integer> entry : mapOfCosts.entrySet()) {
                Location location = entry.getKey();
                int cost = entry.getValue();

                adjacencyMatrix[nodes.get(i).getIndexOfLocation()][location.getIndexOfLocation()] = cost;
            }
        }

        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                costMatrix[i][j] = adjacencyMatrix[i][j];
                //System.out.print("[" + adjacencyMatrix[i][j] + "]");
            }
            //System.out.println();
        }
        //System.out.println();

        int fromLocation = location1.getIndexOfLocation();
        int toLocation = location2.getIndexOfLocation();
        isVisited[fromLocation] = 1;
        isVisited[toLocation] = 1;
        /*
         * Parcurgem dfs graful, de la locatia initiala pana ajungem la cea finala.
         */
        int visitedNodes[] = new int[city.getNodes().size()];

        dfsSearch(fromLocation, fromLocation, toLocation, adjacencyMatrix, 0, visitedNodes, 0);
        /*for(int i=0;i< nodes.size();i++){
            for(int j=0;j< nodes.size();j++){
                System.out.print("[" + costMatrix[i][j] + "]");
            }
            System.out.println();
        }*/
        /*
         * Dupa ce am completat matricea suport, reconstituim de la final la inceput drumul. Cautam cea mai mica valoare de pe
         * coloana destinatiei dintr un drum parcurs de functia dfs si cautam nodurile precedente.
         */

        int minCost = (int) Double.POSITIVE_INFINITY;
        int positionI = 0, positionJ = toLocation;
        for (int i = 0; i < nodes.size(); i++) {
            if (costMatrix[i][toLocation] < minCost && costMatrix[i][toLocation] != 0 && isVisited[i] == 1) {
                minCost = costMatrix[i][toLocation];
                positionI = i;
            }
        }
        //System.out.println(" este de " + minCost + " minute.");
        //System.out.println();
        int copyOfMinCost = minCost;

        /*
         * Gasim nodurile precedente in functie de valoarea anterioara a costului minim minus valoarea in matricea de
         * adiacenta, si le introducem intr-o lista de nr intregi.
         */
        if (minCost != (int) Double.POSITIVE_INFINITY) {
            List<Integer> pathOfNodes = null;
            if (adjacencyMatrix[fromLocation][toLocation] != copyOfMinCost) {
                pathOfNodes = new ArrayList<>();
                while (minCost != 0) {
                    pathOfNodes.add(positionJ + 1);
                    minCost = minCost - adjacencyMatrix[positionI][positionJ];
                    for (int i = 0; i < nodes.size(); i++) {
                        if (costMatrix[i][positionI] == minCost) {
                            positionJ = positionI;
                            positionI = i;
                            break;
                        }
                    }
                }
                pathOfNodes.add(fromLocation + 1);
            }
            if (pathOfNodes != null)
                for (int i = pathOfNodes.size() - 1; i >= 0; i--) {

                    if (addPath == true) {
                        //System.out.print("-" + pathOfNodes.get(i) + "- ");
                        locationVisitedInCurrentStep.add(pathOfNodes.get(i));
                    }
                }
            if (copyOfMinCost == adjacencyMatrix[fromLocation][toLocation]) {

                if (addPath == true) {
                    //System.out.print("-" + (fromLocation+1) + "- " + "-" + (toLocation+1) + "-");
                    locationVisitedInCurrentStep.add(location1.getIndexOfLocation() + 1);
                    locationVisitedInCurrentStep.add(location2.getIndexOfLocation() + 1);
                }
            }
            //System.out.println();
            return copyOfMinCost;
        }
        else return -1;
    }
    /**
     * parcurgere dfs a grafului
     * @param currentNode nodul curent
     * @param startNode nodul de la care se porneste
     * @param finalNode nodul destinatie
     * @param adjacencyMatrix matricea de adiacenta
     * @param lastCost costul adunat pana in pasul apelului
     */
    public void dfsSearch(int currentNode,int startNode, int finalNode, int adjacencyMatrix[][], int lastCost, int visitedNodes[],int pas){
        /*
        * Parcurgere dfs a grafului, care completeaza in matricea costMatrix costul minim de la nodul initial pana la
        * nodul final. Daca se gaseste un nod "x" in care drumul din acest nod are costul mai mic decat oricare alt drum
        *  in nodul x, se suprapune valoarea mai mica, calculata ca si costul pana la nodul curent din nodul initial +
        * costul dintre nodul curent si nodul x
         */
        int lastCostCopy = lastCost;
        if (currentNode == finalNode)
            return;

        for(int i=0;i<city.getNodes().size();i++){
            //////////////////////////////////////////////////////// de verificat
            if (adjacencyMatrix[currentNode][i] > 0){
                if (lastCost == 0){
                    //System.out.println("Inlocuim " + costMatrix[currentNode][i] + " de pe " + (currentNode+1) + " " + (i+1) + " cu " +(adjacencyMatrix[currentNode][i] + lastCost));
                    isVisited[i] = 1;
                    dfsSearch(i,startNode,finalNode,adjacencyMatrix,adjacencyMatrix[currentNode][i] + lastCost,visitedNodes,pas+1);
                }
                else
                    if ((adjacencyMatrix[currentNode][i] + lastCost < costMatrix[currentNode][i]) || (costMatrix[currentNode][i] == adjacencyMatrix[currentNode][i] && currentNode != startNode)) {
                        //System.out.println("Inlocuim " + costMatrix[currentNode][i] + " de pe " + (currentNode+1) + " " + (i+1) + " cu " +(adjacencyMatrix[currentNode][i] + lastCost));
                        costMatrix[currentNode][i] = adjacencyMatrix[currentNode][i] + lastCost;
                        isVisited[i] = 1;
                        dfsSearch(i,startNode,finalNode,adjacencyMatrix,adjacencyMatrix[currentNode][i] + lastCost,visitedNodes,pas+1);
                    }
            }
        }
    }
    /**
     * afiseaza planul prin care turistul viziteaza cat de multe locatii este posibil in timpul si zilele introduse
     * @param startLocation locatia de unde porneste si se intoarce turistul
     */
    public void showPlanForEachDay(Location startLocation){
        /*
        * Cand am creat un trip pentru fiecare zi, am terminat problema. Initializam un vector in care retinem locatiile
        * care au fost deja vizitate
        */
        locationVisitedInCurrentPlan = new int[city.getNodes().size()];
        int currentDay = 1;
        /*
        * Pentru fiecare zi, este afisat planul in care sunt vizitate cele mai multe locatii posibile, nevizitate in
        * zilele anterioare. Daca nu mai exista locatii noi vizitabile la care se poate ajunge, se va afisa numarul de
        * zile libere ramase.
         */
        while(numberOfDays > 0) {

            maximumLocationsVisitedInADay = 0;
            costOfTheDay = 0;
            mostLocationsVisitedInCurrentDay = new int[city.getNodes().size()];
            pathOfLocationsVisitedInThisDay = new ArrayList<>();

            List<Location> list = new ArrayList<>();

            findBestCombination(startLocation, list);
            /*
            * Dupa ce a fost memorat drumul care acopera cele mai multe locatii nevizitate, este afisat drumul, se mar-
            * cheaza locatiile noi vizitate in vectorul locationVisitedInCurrentPlan pentru a sti ce locatii au ramas
            * nevizitate, se afiseaza numarul de locatii noi vizitate si costul in minute al zilei.
             */

            if (maximumLocationsVisitedInADay != 0) {
                System.out.println("Planul pe ziua " + currentDay++);
                for (int i = 0; i < mostLocationsVisitedInCurrentDay.length; i++) {
                    if (mostLocationsVisitedInCurrentDay[i] == 1) {
                        locationVisitedInCurrentPlan[i] = 1;
                        //System.out.print((i) + " ");
                    }
                }
                System.out.println();
                for (int i = 0; i < pathOfLocationsVisitedInThisDay.size()-1; i++) {
                    if (pathOfLocationsVisitedInThisDay.get(i+1) != pathOfLocationsVisitedInThisDay.get(i))
                    System.out.print(city.getNodes().get(pathOfLocationsVisitedInThisDay.get(i)-1).getName() + " - ");
                }
                System.out.print(city.getNodes().get(pathOfLocationsVisitedInThisDay.get(pathOfLocationsVisitedInThisDay.size()-1)-1).getName() + " ");
                System.out.println();
                System.out.println(maximumLocationsVisitedInADay + " locatii noi vor fi vizitate si costul tirpului va fi de " + costOfTheDay + " de minute.");
                //System.out.println("Maximul de locatii: " + maximumLocationsVisitedInADay);
                numberOfDays--;
                System.out.println();
            }
            else {
                System.out.println("Deja ai vizitat toate locatiile posibile in timpul disponibil. Iti raman " + numberOfDays + " zile sa mergi unde vrei.");
                return;
            }
        }
    }
    /**
     * gaseste drumul care acopera cele mai multe locatii nevizitate inca, in timpul disponibil
     * @param startLocation locatia de unde se pleaca si se intoarce
     * @param locationsToBeAdded lista locatiilor pe care sa le acopere la fiecare pas
     */
    public void findBestCombination(Location startLocation, List<Location> locationsToBeAdded) {
        /*
        * Functie recursiva, ce incearca toate combinatiile de locatii prin care sa treaca din locatia start, spre
        * locatia start. Cand este gasita o combinatie care are costul mai mic sau egal cu cel disponibil pe zi,
        * verifica cate locatii noi au fost parcurse in acest drum. Cele mai multe locatii sunt memorate dupa fiecare
        * pas, la fel si drumul exact.
         */
        for (int i = 0; i < city.getNodes().size(); i++) {
            if (!locationsToBeAdded.contains(city.getNodes().get(i)) && startLocation != city.getNodes().get(i)) {
                locationsToBeAdded.add(city.getNodes().get(i));

               /*System.out.print(startLocation.getIndexOfLocation() + " ");
                for(int j=0;j<locationsToBeAdded.size();j++)
                    System.out.print(locationsToBeAdded.get(j).getIndexOfLocation() + " ");
                System.out.println();*/

                int locationsVisitedInThisStep = 0;
                int locationsVerifiedInThisStep[] = new int[city.getNodes().size()];



                visitingOrder = locationsToBeAdded;
                if (shortestPathByPreferences(startLocation,startLocation) <= minutesAvalible && shortestPathByPreferences(startLocation,startLocation) != -1){
                    for(int j=0;j<locationVisitedInCurrentStep.size();j++){
                        if (locationVisitedInCurrentPlan[locationVisitedInCurrentStep.get(j)-1] == 0 && locationsVerifiedInThisStep[locationVisitedInCurrentStep.get(j)-1] == 0){
                            locationsVisitedInThisStep++;
                            locationsVerifiedInThisStep[locationVisitedInCurrentStep.get(j)-1] = 1;
                        }
                    }
                    //System.out.println(shortestPathByPreferences(startLocation,startLocation));
                }

                if (locationsVisitedInThisStep > maximumLocationsVisitedInADay){
                    maximumLocationsVisitedInADay = locationsVisitedInThisStep;
                    costOfTheDay = shortestPathByPreferences(startLocation,startLocation);
                    pathOfLocationsVisitedInThisDay = locationVisitedInCurrentStep;
                    for(int j=0;j<locationVisitedInCurrentStep.size();j++){
                        mostLocationsVisitedInCurrentDay[locationVisitedInCurrentStep.get(j)-1] = 1;
                    }
                }




                findBestCombination(startLocation, locationsToBeAdded);
                locationsToBeAdded.remove(city.getNodes().get(i));
            }
        }
    }

    /*public Location findLocationWithLowestCost(Location startLocation){
        int minimumCost= (int) Double.POSITIVE_INFINITY;
        Location locationWithMinimumCost = null;
        for (int i=0;i<city.getNodes().size();i++){
            if (startLocation != city.getNodes().get(i) && adjacencyMatrix[startLocation.getIndexOfLocation()][i] < minimumCost &&
                    adjacencyMatrix[startLocation.getIndexOfLocation()][i] != 0 && locationsVisited[i] == 0){
                minimumCost= adjacencyMatrix[startLocation.getIndexOfLocation()][i];
                locationWithMinimumCost = city.getNodes().get(i);
            }
        }
        //System.out.println("Costul: " + minimumCost);
        if (minimumCost == (int) Double.POSITIVE_INFINITY)
            return null;
        return locationWithMinimumCost;
    }*/
}

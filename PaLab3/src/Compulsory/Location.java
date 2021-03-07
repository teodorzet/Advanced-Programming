package Compulsory;

import java.util.HashMap;
import java.util.Map;

public abstract class Location implements Comparable<Location>{
    private String name;
    private Map<Location, Integer> cost = new HashMap<>();

    /**
     * constructor
     * @param name numele locatiei
     * @param cost mapa costurilor catre fiecare locatie
     */
    public Location(String name, Map<Location, Integer> cost) {
        this.name = name;
        this.cost = cost;
    }

    /**
     * getter pe name
     * @return numele locatiei
     */
    public String getName() {
        return name;
    }
    /**
     * setter pe nume
     * @param name numele locatiei
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * getter pe mapa
     * @return mapa costurilor
     */
    public Map<Location, Integer> getCost() {
        return cost;
    }
    /**
     * setter pe mapa
     * @param cost mapa costurilor
     */
    public void setCostMap(Map<Location, Integer> cost) {
        this.cost = cost;
    }
    /**
     * adaugarea unui nou cost spre o locatie
     * @param node locatia
     * @param value costul
     */
    public void setCost(Location node, int value){
        if (compareTo(node) != 0)
        cost.put(node, value);
    }
    @Override
    public String toString() {
        return super.toString();
    }
    /**
     * comparatia intre doua Locatii
     * @param o locatia cu care se compara
     * @return 0 daca locatia nu are nume sau nu are acelasi nume, 1 daca are
     */
    @Override
    public int compareTo(Location o) {
        if(o.name == null)
            return 0;
        return this.name.compareTo(o.name);
    }
}

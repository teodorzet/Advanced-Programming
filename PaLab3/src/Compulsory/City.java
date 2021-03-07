package Compulsory;

import java.util.ArrayList;
import java.util.List;

public class City {
    private List<Location> nodes = new ArrayList<>();


    private String name = new String();

    /**
     * getter pe numele orasului
     * @return
     */
    public String getName() {
        return name;
    }
    /**
     * constructor
     *
     * @param nodes locatiile
     */
    public City(List<Location> nodes, String name) {
        this.nodes = nodes;
        this.name = name;
    }
    /**
     * getter pe lista de locatii
     *
     * @return lista de locatii
     */
    public List<Location> getNodes() {
        return nodes;
    }
    /**
     * setter pe lista de locatii
     *
     * @param nodes lista de locatii
     */
    public void setNodes(List<Location> nodes) {
        this.nodes = nodes;
    }
    /**
     * adaugarea unei locatii
     *
     * @param node locatia
     */
    public void addLocation(Location node) {
        nodes.add(node);
    }
    /*@Override
    public String toString() {
        return super.toString();
    }*/
    public void print() {
        List<Location> nodes = getNodes();
        int i = 0;
        System.out.println("Orasul " + getName() + " are urmatoarele locatii: ");
        while (i != nodes.size()) {
            System.out.println(nodes.get(i).getName());
            i++;
        }
    }
}

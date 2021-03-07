package Optional;

import java.time.LocalTime;
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
    @Override
    public String toString() {
        return super.toString();
    }
    public void print() {
        List<Location> nodes = getNodes();
        int i = 0;
        System.out.println("Orasul " + getName() + " are urmatoarele locatii: ");
        while (i != nodes.size()) {
            if (nodes.get(i) instanceof Church)
                System.out.print("Biserica ");
            else if (nodes.get(i) instanceof Hotel)
                System.out.print("Hotelul ");
            else if (nodes.get(i) instanceof Museum)
                System.out.print("Muzeul ");
            else if (nodes.get(i) instanceof Restaurant)
                System.out.print("Restaurantul ");
            System.out.println(nodes.get(i).getName());
            i++;
        }
        System.out.println();
    }

    /**
     * afisarea locatiilor care sunt vizitabile si nu platibile, in ordinea orelor de deschidere
     */
    public void showVisitableAndNotPayable(){
        System.out.println("Locatiile din " + getName() + " care sunt vizitabile fara a plati sunt:");
        List<Location> nodes = getNodes();
        int i=0;
        LocalTime time[] = new LocalTime[nodes.size()];
        int positions[] = new int[nodes.size()];
        int count = 0;

        while(i != nodes.size()){
            if (nodes.get(i) instanceof Visitable && !(nodes.get(i) instanceof Payable)){
                time[count] = ((Visitable) nodes.get(i)).getOpeningTime();
                positions[count] = i;
                count++;
                //System.out.println(nodes.get(i).getName());
            }
            i++;
        }
        for(i=0;i<count-1;i++){
            for(int j=i+1;j<count;j++){
                if (time[i].compareTo(time[j]) > 0){
                    LocalTime aux1 = time[i];
                    time[i]= time[j];
                    time[j] = aux1;
                    int aux2 = positions[i];
                    positions[i] = positions[j];
                    positions[j] = aux2;
                }
            }
        }
        for(i=0;i<count;i++){
            if (nodes.get(positions[i]) instanceof Church)
                System.out.print("Biserica ");
            else if (nodes.get(positions[i]) instanceof Hotel)
                System.out.print("Hotelul ");
            else if (nodes.get(positions[i]) instanceof Museum)
                System.out.print("Muzeul ");
            else if (nodes.get(positions[i]) instanceof Restaurant)
                System.out.print("Restaurantul ");
            System.out.println(nodes.get(positions[i]).getName() + " incepand cu ora " + ((Visitable) nodes.get(positions[i])).getOpeningTime());
        }
    }
}

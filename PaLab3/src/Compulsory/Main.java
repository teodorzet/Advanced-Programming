package Compulsory;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String arggs[]) {
        Location location1 = new Hotel("Traian",new HashMap<>());
        Location location2 = new Museum("Unirii", new HashMap<>());
        Location location3 = new Museum("Mihail Sadoveanu", new HashMap<>());
        Location location4 = new Church("Sfintii Trei Ierarhi", new HashMap<>());
        Location location5 = new Church("Cetatuia", new HashMap<>());
        Location location6 = new Restaurant("Panoramic", new HashMap<>());

        location1.setCost(location2,10);
        location1.setCost(location3,50);
        location2.setCost(location3,20);
        location3.setCost(location2,20);
        location2.setCost(location4,20);
        location2.setCost(location5,10);
        location3.setCost(location4,20);
        location4.setCost(location5,30);
        location5.setCost(location4,30);
        location4.setCost(location6,10);
        location5.setCost(location6,20);

        List<Location> listOfLocationsInIasi = Arrays.asList(location1, location2, location3,location4,location5,location6);
        City city1 = new City(listOfLocationsInIasi,"Iasi");
        city1.print();
    }
}

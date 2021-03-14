package Bonus;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        Location location1 = new Hotel("Traian",new HashMap<>(),0);
        Location location2 = new Museum("Unirii", new HashMap<>(),1);
        Location location3 = new Museum("Mihail Sadoveanu", new HashMap<>(),2);
        Location location4 = new Church("Sfintii Trei Ierarhi", new HashMap<>(),3);
        Location location5 = new Church("Cetatuia", new HashMap<>(),4);
        Location location6 = new Restaurant("Panoramic", new HashMap<>(),5);

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
        location3.setCost(location1,20);
        location4.setCost(location1,10);
        location6.setCost(location1,20);

        ((Church) location4).timeSet();
        ((Church) location4).setOpeningTime(LocalTime.of(9,30));

        ((Church) location5).setOpeningTime(LocalTime.of(9,00));

        List<Location> listOfLocationsInIasi = Arrays.asList(location1, location2, location3,location4,location5,location6);
        City city1 = new City(listOfLocationsInIasi,"Iasi");
        //city1.print();
        //city1.showVisitableAndNotPayable();
        System.out.println();
        //System.out.println(((Church) location4).getVisitingDuration().getOpeningDuration());
        List<Location> visitingOrder = Arrays.asList(location2,location3,location4);
        TravelPlan iasiTravelPlan = new TravelPlan(city1,visitingOrder,3,60);
        //System.out.println(iasiTravelPlan.shortestPathByPreferences(location1,location1));
        //System.out.println(iasiTravelPlan.shortestPath(location1,location6));

        iasiTravelPlan.showPlanForEachDay(location1);
    }
}

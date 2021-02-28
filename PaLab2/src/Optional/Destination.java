package Optional;

import java.util.Objects;

public class Destination {
    private String firstName;
    private String lastName;
    private int demand;


    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public int getDemand() {
        return demand;
    }
    public void setDemand(int demand) {
        this.demand = demand;
    }

    public Destination(String firstName, String lastName, int demand) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.demand = demand;
    }
    public Destination(){}

    @Override
    public String toString() {
        return "Destination{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", demand=" + demand +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Destination that = (Destination) o;
        return demand == that.demand && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, demand);
    }
}

package objs.zones;

import java.util.Objects;

public class City extends Zone implements Comparable<City> {
    private int numOfSuperchargers;

    private final int DEFAULTNUMOFSUPERCHARGERS = 0;

    public City(String name) {
        super(name);
        numOfSuperchargers = DEFAULTNUMOFSUPERCHARGERS;
    }

    public City(String name, int numOfSuperchargers) {
        super(name);
        this.numOfSuperchargers = numOfSuperchargers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return numOfSuperchargers == city.numOfSuperchargers;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numOfSuperchargers);
    }

    public String toString() {
        return String.format("\t%s: %d\n", super.getName(), numOfSuperchargers);
    }

    public int getNumOfSuperchargers() {
        return numOfSuperchargers;
    }

    public void incrementNumOfChargers() {
        numOfSuperchargers++;
    }

    @Override
    public int compareTo(City otherCity) {
        return super.getName().compareTo(otherCity.getName());
    }
}
